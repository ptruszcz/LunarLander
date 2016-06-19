package gui;

import gameobjects.HUD;
import gameobjects.Spaceship;
import gameobjects.GameMap;
import parsers.ImageParser;
import physics.VelocityVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

/**
 * Created by piotr on 19.05.2016.
 *
 */
public class GamePanel extends JPanel {

    private static final int SPEED = 10;

    private static final KeyStroke PRESSED_UP = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
    private static final KeyStroke RELEASED_UP = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
    private static final KeyStroke PRESSED_LEFT = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
    private static final KeyStroke RELEASED_LEFT = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
    private static final KeyStroke PRESSED_RIGHT = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
    private static final KeyStroke RELEASED_RIGHT = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
    private static final KeyStroke PRESSED_SPACE = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false);

    private HashSet<String> pressedKeysList = new HashSet<>();

    private GameMap gameMap = new GameMap();
    private Spaceship spaceship = null;
    private HUD hud = null;

    private ActionLoop actionLoop = null;
    private VelocityVector vector = new VelocityVector();

    private Image pauseIcon = null;

    private JButton pauseButton = new JButton((Icon)pauseIcon);

    public GamePanel() {
        spaceship = new Spaceship(GameMap.X_RESOLUTION/2, GameMap.Y_RESOLUTION*0.1);
        hud = new HUD(spaceship);
        bindKeys();
        actionLoop = new ActionLoop();
        pauseIcon = ImageParser.loadImage("pauseButton.png");
        add(pauseButton, RIGHT_ALIGNMENT);

    }

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

        actionMap.put("P_UP", new Movement("UP", "P"));
        actionMap.put("R_UP", new Movement("UP", "R"));

        actionMap.put("P_LEFT", new Movement("LEFT", "P"));
        actionMap.put("R_LEFT", new Movement("LEFT", "R"));

        actionMap.put("P_RIGHT", new Movement("RIGHT", "P"));
        actionMap.put("R_RIGHT", new Movement("RIGHT", "R"));

        actionMap.put("P_SPACE", new Movement("SPACE", "SPACE"));
    }

    private class Movement extends AbstractAction {

        private String move = null;
        private String pressedOrReleased = null;

        public Movement(String move, String pressedOrReleased) {
            this.move = move;
            this.pressedOrReleased = pressedOrReleased;
        }

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
            }
        }
    }

    private class ActionLoop extends TimerTask {
        @Override
        public void run() {
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

        private void selectAction(String action) {
            switch(action) {
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

        private void toggleAreEnginesActive() {
            if(pressedKeysList.isEmpty())
                spaceship.setAreEnginesWorking(false);
            else
                spaceship.setAreEnginesWorking(true);
        }
    }

    public void startGame() {
        new Timer().scheduleAtFixedRate(actionLoop, 0, SPEED);
    }

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

        //g2d.drawImage(pauseIcon, GameMap.X_RESOLUTION - pauseIcon.getWidth(null) - 20, pauseIcon.getHeight(null), null);
    }
}
