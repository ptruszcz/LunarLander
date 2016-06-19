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

    public void initialize() {
        setTitle("Lunar Lander");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLayout(cardLayout);
        setPanels();
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
                gamePanel.startGame();
                break;
            case "MEDIUM":
                cardLayout.show(cardsContainer, "Game");
                gamePanel.startGame();
                break;
            case "HARD":
                cardLayout.show(cardsContainer, "Game");
                gamePanel.startGame();
                break;
            case "GAME_OVER":
                cardLayout.show(cardsContainer, "GameOver");
                break;
            case "SUBMIT!":

                break;

        }
    }
}
