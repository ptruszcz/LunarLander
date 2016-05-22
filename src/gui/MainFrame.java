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
    private CardLayout cardLayout = null;
    private GamePanel gamePanel = null;

    public void initialize() {
        setTitle("Lunar Lander");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        this.setPanels();
        getContentPane().add(cardsContainer, BorderLayout.CENTER);

        pack();
        this.setVisible(true);
    }

    private void setPanels() {
        MenuPanelBuilder.passContext(this);

        cardLayout = new CardLayout();
        cardsContainer = new JPanel(cardLayout);

        gamePanel = new GamePanel();
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
        String buttonName = e.getActionCommand();
        switch (buttonName) {
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
        }
    }
}
