package gui;

import gameobjects.HUD;
import gameobjects.Spaceship;
import gameobjects.GameMap;
import parsers.HsParser;
import parsers.Parser;
import physics.VelocityVector;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

/**
 * Klasa panelu gry. Na nim rysowane są wszystkie elementy i wyświetlany jest interfejs.
 */
public class GamePanel extends JPanel {

    /** stała określająca co ile ms na być odświeżana gra */
    private static final int SPEED;
    /** liczba poziomów */
    private static final int LAST_LEVEL;
    /** początkowa liczba żyć */
    private static final int STARTING_LIVES;

    /** KeyStroke dla wciśnięcia strzałki w górę */
    private static final KeyStroke PRESSED_UP = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
    /** KeyStroke dla pusczenia strzałki w górę */
    private static final KeyStroke RELEASED_UP = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
    /** KeyStroke dla wciśnięcia strzałki w lewo */
    private static final KeyStroke PRESSED_LEFT = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
    /** KeyStroke dla puszczenia strzałki w lewo */
    private static final KeyStroke RELEASED_LEFT = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
    /** KeyStroke dla wciśnięcia strzałki w prawo */
    private static final KeyStroke PRESSED_RIGHT = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
    /** KeyStroke dla puszczenia strzałki w prawo */
    private static final KeyStroke RELEASED_RIGHT = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
    /** KeyStroke dla wciśnięcia spacji */
    private static final KeyStroke PRESSED_SPACE = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false);
    /** KeyStroke dla wciśnięcia resetu (klawisz r, funkcja deweloperska) */
    private static final KeyStroke PRESSED_RST = KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false);

    static {
        SPEED = Parser.getSpeed();
        LAST_LEVEL = Parser.getLastlevel();
        STARTING_LIVES = Parser.getLives();
    }

    /** kolekcja przechowująca listę aktualnie wciśniętych klawiszy */
    private HashSet<String> pressedKeysList = new HashSet<>();

    /** timer zarządzający wykonywaniem pętli gry */
    private Timer timer = null;
    /** element nasłuchujący na wydarzenia w panelu. W tym przypadku będzie nim okno główne */
    private ActionListener context = null;

    /** zmienna przechowująca aktualny poziom */
    public static int currentLevel=1;
    /** zmienna przechowująca pozostałą ilość żyć */
    public static int livesLeft;

    /** zmienna przechowująca wynik zdobyty na danym poziomie */
    public static int levelScore;

    /** pole przechowujące instancję aktualnej planszy */
    private GameMap gameMap = new GameMap();
    /** pole przechowujące instancję statku */
    private Spaceship spaceship = null;
    /** pole przechowujące instancję interfejsu użytkownika */
    private HUD hud = null;

    /** zmienna przechowująca instancję pętli programu, która jest zadaniem dla Timera */
    private ActionLoop actionLoop = null;
    /** wektor prędkości który w każdym wykonaniu pętli zostanie dodany do statku */
    private VelocityVector vector = new VelocityVector();

    /**
     * konstruktor inicjujący wszystkie obiekty i zmienne oraz przypisujący klawisze do akcji
     * @param context okno główne programu nasłuchujące na zdarzenia
     */
    public GamePanel(ActionListener context) {
        spaceship = new Spaceship(GameMap.X_RESOLUTION/2, GameMap.Y_RESOLUTION*0.1);
        hud = new HUD(spaceship);
        timer = new Timer();

        currentLevel = 1;
        resetLives();

        this.context = context;
        bindKeys();
        actionLoop = new ActionLoop();
    }


    /**
     * metoda przypisująca przyciski do akcji
     */
    private void bindKeys() {
        int whenIFW= JComponent.WHEN_IN_FOCUSED_WINDOW;

        InputMap inputMap = getInputMap(whenIFW);
        ActionMap actionMap = getActionMap();

        inputMap.put(PRESSED_UP, "P_UP");
        inputMap.put(RELEASED_UP, "R_UP");

        inputMap.put(PRESSED_LEFT, "P_LEFT");
        inputMap.put(RELEASED_LEFT, "R_LEFT");

        inputMap.put(PRESSED_RIGHT, "P_RIGHT");
        inputMap.put(RELEASED_RIGHT, "R_RIGHT");

        inputMap.put(PRESSED_SPACE, "P_SPACE");
        inputMap.put(PRESSED_RST, "P_RST");

        actionMap.put("P_UP", new Movement("UP", "P"));
        actionMap.put("R_UP", new Movement("UP", "R"));

        actionMap.put("P_LEFT", new Movement("LEFT", "P"));
        actionMap.put("R_LEFT", new Movement("LEFT", "R"));

        actionMap.put("P_RIGHT", new Movement("RIGHT", "P"));
        actionMap.put("R_RIGHT", new Movement("RIGHT", "R"));

        actionMap.put("P_SPACE", new Movement("SPACE", "SPACE"));
        //actionMap.put("P_RST", new Movement("RST", "RST")); //opcja deweloperska
    }

    /**
     * klasa decydująca o akcji wykonanej przez statek na podstawie wciśnięcia przycisków
     */
    private class Movement extends AbstractAction {

        /** zmienna decydująca o tym jaki ruch wykonać */
        private String move = null;
        /** zmienna mówiąca o tym, czy przycisk został wciśnięty czy puszczony */
        private String pressedOrReleased = null;

        /**
         * konstruktor akcji
         * @param move jaki ruch wykonać
         * @param pressedOrReleased czy przycisk został wciśnięty czy puszczony
         */
        public Movement(String move, String pressedOrReleased) {
            this.move = move;
            this.pressedOrReleased = pressedOrReleased;
        }

        /**
         * metoda pauzująca grę lub dodająca i uwuwająca przycisk z listy aktywnych klawiszy
         * @param e zdarzenie wciśnięcia lub puszczenia przycisku
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(pressedOrReleased) {
                case "P":
                    pressedKeysList.add(move);
                    break;
                case "R":
                    pressedKeysList.remove(move);
                    break;
                case "SPACE":
                    spaceship.togglePaused();
                    break;
                case "RST":
                    restart();
                    break;
            }
        }
    }

    /**
     * zadanie Timera, główna pętla programu której częstotliwość jest zarządzana przez timer
     */
    private class ActionLoop extends TimerTask {
        /**
         * główna pętla programu
         */
        @Override
        public void run() {
            checkGameState();

            vector.descend();
            pressedKeysList.forEach(this::selectAction);
            spaceship.checkForCollisions(gameMap);

            if (spaceship.isInAir()) {
                spaceship.update(vector);
                toggleAreEnginesActive();
            }

            vector.reset();
            repaint();
        }

        /**
         * metoda decydująca o ruchu statku na podstawie wykonanej akcji
         * @param action wykonana akcja
         */
        private void selectAction(String action) {
            if (spaceship.getFuelLeft() != 0) {
                switch (action) {
                    case "UP":
                        vector.up();
                        break;
                    case "RIGHT":
                        vector.right();
                        break;
                    case "LEFT":
                        vector.left();
                        break;
                }
            }
        }

        /**
         * metoda włączająca i wyłączająca silniki na podstawie tego, czy przyciski są wciśnięte
         */
        private void toggleAreEnginesActive() {
            if(pressedKeysList.isEmpty() || spaceship.getFuelLeft() == 0)
                spaceship.setAreEnginesWorking(false);
            else
                spaceship.setAreEnginesWorking(true);
        }
    }

    /**
     * rozpoczyna grę inicjując wszystkie elementy i kolejkując zadanie
     */
    public void startGame() {
        spaceship = new Spaceship(GameMap.X_RESOLUTION/2, GameMap.Y_RESOLUTION*0.1);
        timer = new Timer();
        actionLoop = new ActionLoop();
        hud = new HUD(spaceship);
        timer.scheduleAtFixedRate(actionLoop, 0, SPEED);
    }

    /**
     * anuluje wykonywanie pętli programu i czyści listę aktywnych przycisków
     */
    public void stopGame() {
        timer.cancel();
        pressedKeysList.clear();
    }

    /**
     * zatrzymuje program i uruchamia go ponownie
     */
    private void restart() {
        stopGame();
        startGame();
    }

    /**
     * sprawdza aktualny stan gry i podejmuje odpowiednie decyzje na tej podstawie. Jeśli gra ma się zakończyć inicjuje to.
     */
    private void checkGameState() {
        if(spaceship.isCrashed()) {
            stopGame();
            if (getLivesLeft() > 1) {
                loseLife();
                restart();
            }
            else {
                checkIfRecord(MainFrame.getPlayerScore());
            }
        }
        else if(spaceship.isLanded()) {
            stopGame();
            saveScore();
            currentLevel++;
            if (currentLevel == LAST_LEVEL+1)
                checkIfRecord(MainFrame.getPlayerScore());
            else
                getToTheNextLevel();
                startGame();
        }
    }

    /**
     * sprawdza czy został pobity rekord
     * @param score wynik do sprawdzenia
     */
    private void checkIfRecord(int score) {
        String x;
        switch(MainFrame.getDifficulty()) {
            case 1:
                x = "EASY";
                break;
            case 2:
                x = "MEDIUM";
                break;
            case 3:
                x = "HARD";
                break;
            default:
                x = "EASY";

        }
        if (score > Integer.parseInt(HsParser.getRecord(x, "4")[1])){
            context.actionPerformed(new ActionEvent(this, 0, "NEW_RECORD"));
            for(int n=0; n<4; n++){
                int record = Integer.parseInt(HsParser.getRecord(Integer.toString(MainFrame.getDifficulty()), Integer.toString(n))[1]);
                if (score > record){
                    String i = Integer.toString(n);
                    HsParser.setScore(i, MainFrame.getPlayerName(), Integer.toString(score));
                }
            }
        }
        else{
            context.actionPerformed(new ActionEvent(this, 0, "GAME_OVER"));
        }
    }

    /** przywraca liczbę żyć do początkowego stanu */
    public static void resetLives() {
        setLivesLeft(STARTING_LIVES);
    }

    /** wywołuje wczytanie kolejnego poziomu */
    private void getToTheNextLevel() {
        gameMap.loadLevel(currentLevel);
    }

    /** zapisuje aktualny wynik z danego poziomu do wyniku ogólnego */
    private void saveScore() {
        levelScore = spaceship.getFuelLeft();
        levelScore *= getLivesLeft();
        System.out.println(levelScore);
        MainFrame.addScore(levelScore);
    }

    /**
     * getter pozostałej liczby żyć
     * @return pozostała liczba żyć
     */
    public static int getLivesLeft() {
        return livesLeft;
    }

    /**
     * setter pozostałej liczby żyć
     * @param livesLeft1 liczba żyć do ustawienia
     */
    public static void setLivesLeft(int livesLeft1) {
        livesLeft = livesLeft1;
    }

    /** metoda zmiejszająca liczbę żyć o 1 */
    public static void loseLife() {
        livesLeft--;
    }

    /**
     * metoda typu callback, rysująca wszystkie elementy interfejsu i gry
     * @param g kontekst graficzny
     */
    @Override
    public void paintComponent(Graphics g) {

        /** przechowuje aktualną skalę okna w poziomie */
        double scaleX = getWidth() / (double) GameMap.X_RESOLUTION;
        /** przechowuje aktualną skalę okna w pionie */
        double scaleY = getHeight() / (double) GameMap.Y_RESOLUTION;

        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        g2d.scale(scaleX, scaleY);
        g2d.setColor(Color.lightGray);

        gameMap.draw(g2d);
        spaceship.draw(g2d);
        hud.draw(g2d);
    }
}
