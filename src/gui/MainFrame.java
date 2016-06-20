package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by piotr on 19.05.2016.
 *
 */
public class MainFrame extends JFrame implements ActionListener {

    private JPanel cardsContainer = null;
    private CardLayout cardLayout = new CardLayout();
    private GamePanel gamePanel = null;
    private static String playerName = null;
    private static int difficulty;
    private static int playerScore;



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

    public static void setPlayerName(String name) {
        playerName = name;
        System.out.println(playerName);
    }

    public static void setPlayerScore(int score) {
        playerScore = score;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void addScore(int score) {
        playerScore += score;
    }

    public static void resetScore() {
        playerScore = 0;
    }

    public static int getDifficulty() {
        return difficulty;
    }

}
