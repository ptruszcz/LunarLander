package gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by piotr on 20.05.2016.
 *
 */
public class MenuPanelBuilder {

    public static ActionListener context;

    public static void passContext(ActionListener actionListener) {
       context = actionListener;
    }

    static private JLabel createTitle(String text, int size) {
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, size));
        title.setForeground(Color.white);
        return title;
    }

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

    static public JPanel buildGameOverPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.black);
        panel.add(createTitle("GAME OVER", 80), BorderLayout.CENTER);
        panel.add(buildTextButton("BACK"), BorderLayout.SOUTH);
        return panel;
    }

    static public JPanel buildNewRecordPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.black);
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.VERTICAL;
        gBC.gridwidth = 3;

        TextField textField = new TextField("Your name...");
        textField.setBackground(Color.white);
        textField.addActionListener(context);

        gBC.gridy = 0;
        panel.add(createTitle("NEW RECORD!", 80), gBC);
        gBC.gridy = 1;
        panel.add(textField, gBC);
        gBC.gridy = 2;
        panel.add(buildTextButton("SUBMIT!"), gBC);
        return panel;
    }
}
