package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Główne okno aplikacji.
 */
public class MainFrame extends JFrame implements ActionListener {

    /** panel przechowujący wszystkie menusy */
    private JPanel cardsContainer = null;
    /** layout pozwalający na naprzemienne pokazywanie menusów */
    private CardLayout cardLayout = new CardLayout();
    /** panel gry */
    private GamePanel gamePanel = null;
    /** nazwa gracza */
    private static String playerName = null;
    /** aktualny poziom trudności */
    private static int difficulty;
    /** aktualny wynik gracza */
    private static int playerScore;

    /**
     * metoda tworząca okno gry, ustawiająca jego parametry i zarządcę layoutu
     */
    public void initialize() {
        setTitle("Lunar Lander");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLayout(cardLayout);
        setPanels();
        setMinimumSize(new Dimension(740, 500));
        add(cardsContainer, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    /**
     * metoda tworząca wszystkie menusy za pomocą budowniczego
     */
    private void setPanels() {
        MenuPanelBuilder.passContext(this);

        cardsContainer = new JPanel(cardLayout);

        gamePanel = new GamePanel(this);
        gamePanel.setPreferredSize(new Dimension(960,640));
        gamePanel.setBackground(Color.black);

        cardsContainer.add(MenuPanelBuilder.buildMainMenuPanel(), "MainMenu");
        cardsContainer.add(MenuPanelBuilder.buildDifficultyPanel(), "Difficulty");
        cardsContainer.add(MenuPanelBuilder.buildRecordsPanel(), "Records");
        cardsContainer.add(MenuPanelBuilder.buildGameOverPanel(), "GameOver");
        cardsContainer.add(MenuPanelBuilder.buildNewRecordPanel(), "NewRecord");
        cardsContainer.add(gamePanel, "Game");
    }

    /**
     * metoda decydująca o tym, które menu pokazać, reagująca na wciśnięcia klawiszy lub zdarzenia z panelu gry
     * @param e zdarzenie (wciśnięcie przyciska, koniec gry)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "START":
                cardLayout.show(cardsContainer, "Difficulty");
                break;
            case "RECORDS":
                cardLayout.show(cardsContainer, "Records");
                break;
            case "BACK":
                cardLayout.show(cardsContainer, "MainMenu");
                break;
            case "EXIT":
                System.exit(0);
                break;
            case "EASY":
                cardLayout.show(cardsContainer, "Game");
                difficulty = 1;
                GamePanel.resetLives();
                resetScore();
                gamePanel.startGame();
                break;
            case "MEDIUM":
                cardLayout.show(cardsContainer, "Game");
                difficulty = 2;
                GamePanel.resetLives();
                resetScore();
                gamePanel.startGame();
                break;
            case "HARD":
                cardLayout.show(cardsContainer, "Game");
                difficulty = 3;
                GamePanel.resetLives();
                resetScore();
                gamePanel.startGame();
                break;
            case "GAME_OVER":
                cardsContainer.add(MenuPanelBuilder.buildGameOverPanel(), "GameOver");
                cardLayout.show(cardsContainer, "GameOver");
                break;
            case "NEW_RECORD":
                cardsContainer.add(MenuPanelBuilder.buildNewRecordPanel(), "NewRecord");
                cardLayout.show(cardsContainer, "NewRecord");
                break;
            case "SUBMIT!":
                cardLayout.show(cardsContainer, "Records");
                //write name to records
                break;

        }
    }

    /**
     * setter imienia gracza
     * @param name imię gracza
     */
    public static void setPlayerName(String name) {
        playerName = name;
    }

    /**
     * getter imienia gracza
     * @return imię gracza
     */
    public static String getPlayerName() {
        return playerName;
    }

    /**
     * setter wyniku gracza
     * @param score wynik do nadpisania
     */
    public static void setPlayerScore(int score) {
        playerScore = score;
    }

    /**
     * getter wyniku gracza
     * @return wynik gracza
     */
    public static int getPlayerScore() {
        return playerScore;
    }

    /**
     * metoda dodająca punkty do aktualnego wyniku
     * @param score punkty do dodania
     */
    public static void addScore(int score) {
        playerScore += score;
    }

    /**
     * metoda zerująca wynik gracza przed rozpocząciem kolejnej rozgrywki
     */
    public static void resetScore() {
        playerScore = 0;
    }

    /**
     * metoda zwracająca poziom trudności
     * @return poziom trudności na którym gra gracz
     */
    public static int getDifficulty() {
        return difficulty;
    }

}
