package gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Budowniczy i zarządca budowy menusów.
 */
public class MenuPanelBuilder {

    /** ActionListener nasłuchujący na aktywność, np. wciśnięcie klawiszy. W tym wypadku okno główne progarmu */
    public static ActionListener context;

    /** metoda ustawiająca nasłuchiwacza i przypisująca go do odpowiedniego pola */
    public static void passContext(ActionListener actionListener) {
       context = actionListener;
    }

    /**
     * metoda tworząca tytuł o określonych parametrach
     * @param text tekst tytułu
     * @param size rozmiar tytułu
     * @return utworzony tytuł
     */
    static private JLabel createTitle(String text, int size) {
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, size));
        title.setForeground(Color.white);
        return title;
    }

    /**
     * metoda tworząca przycisk o określonych parametrach i dodająca do niego ActionListenera
     * @param text tekst na przycisku
     * @return utworzony przycisk
     */
    static private JButton buildTextButton(String text) {
        JButton button = new JButton(text);

        button.setBackground(Color.black);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        button.setFont(new Font("Serif", Font.BOLD, 55));
        button.setForeground(Color.white);

        button.addActionListener(context);
        return button;
    }

    /**
     * metoda budująca menu główne
     * @return utworzone menu
     */
    static public JPanel buildMainMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.black);

        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.VERTICAL;
        gBC.ipady = 40;
        gBC.gridwidth = 3;

        gBC.gridy = 0;
        panel.add(createTitle("LUNAR LANDER", 90), gBC);

        gBC.gridy = 1;
        panel.add(buildTextButton("START"), gBC);

        gBC.gridy = 2;
        panel.add(buildTextButton("RECORDS"), gBC);

        gBC.gridy = 3;
        panel.add(buildTextButton("EXIT"), gBC);

        return panel;
    }

    /**
     * metoda budująca menu wyboru poziomu trudności
     * @return utworzone menu
     */
    static public JPanel buildDifficultyPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.black);

        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.VERTICAL;
        gBC.gridwidth = 3;

        gBC.gridy = 0;
        panel.add(createTitle("CHOOSE DIFFICULTY:", 40), gBC);

        gBC.gridy = 1;
        panel.add(buildTextButton("EASY"), gBC);

        gBC.gridy = 2;
        panel.add(buildTextButton("MEDIUM"), gBC);

        gBC.gridy = 3;
        panel.add(buildTextButton("HARD"), gBC);

        return panel;
    }

    /**
     * metoda budująca menu rekordów
     * @return utworzone menu
     */
    static public JPanel buildRecordsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);

        panel.add(createTitle("RECORDS", 60), BorderLayout.NORTH);

        BasicArrowButton leftButton = new BasicArrowButton(SwingConstants.WEST);
        panel.add(leftButton, BorderLayout.WEST);

        BasicArrowButton rightButton = new BasicArrowButton(SwingConstants.EAST);
        panel.add(rightButton, BorderLayout.EAST);

        JPanel backButtonPanel= new JPanel(new FlowLayout(FlowLayout.LEADING)); //rozwiązane w ten sposób, żeby był tam gdzie trzeba
        backButtonPanel.add(buildTextButton("BACK"));
        backButtonPanel.setBackground(Color.black);
        panel.add(backButtonPanel,  BorderLayout.SOUTH);

        JLabel cos = new JLabel("TABELA", SwingConstants.CENTER);
        panel.add(cos, BorderLayout.CENTER);

        return panel;
    }

    /**
     * metoda budująca ekran porażki
     * @return utworzony ekran
     */
    static public JPanel buildGameOverPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.black);
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.VERTICAL;
        gBC.gridwidth = 3;

        gBC.gridy = 0;
        panel.add(createTitle("GAME OVER", 80), gBC);
        gBC.gridy = 1;
        panel.add(createTitle("Your score: " + MainFrame.getPlayerScore(), 30), gBC);
        gBC.gridy = 2;
        panel.add(buildTextButton("BACK"), gBC);

        return panel;
    }

    /**
     * metoda budująca ekran pobicia rekordu
     * @return utworzony ekran
     */
    static public JPanel buildNewRecordPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.black);
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.VERTICAL;
        gBC.gridwidth = 3;

        JTextField textField = new JTextField("Your name...");
        textField.setBackground(Color.white);
        textField.addActionListener(context);

        JButton button = buildTextButton("SUBMIT!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You clicked the button");
                String name = textField.getText();
                MainFrame.setPlayerName(name);
            }
        });

        gBC.gridy = 0;
        panel.add(createTitle("NEW RECORD!", 80), gBC);
        gBC.gridy = 1;
        panel.add(createTitle("Your score: "+MainFrame.getPlayerScore(), 30), gBC);
        gBC.gridy = 2;
        panel.add(textField, gBC);
        gBC.gridy = 3;
        panel.add(button, gBC);

        return panel;
    }

}
