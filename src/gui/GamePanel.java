package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by piotr on 19.05.2016.
 *
 */
public class GamePanel extends JPanel implements Runnable, ActionListener {

    Thread thread = new Thread(this);

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        e.getActionCommand();
    }

    @Override
    public void paintComponent(Graphics g) {

    }


}
