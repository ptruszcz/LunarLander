package gui;

import gameobjects.HUD;
import gameobjects.Spaceship;
import gameobjects.Map;
import physics.VelocityVector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by piotr on 19.05.2016.
 *
 */
public class GamePanel extends JPanel implements Runnable, ActionListener {

    private Map map = new Map();
    private Spaceship spaceship;
    private HUD hud = new HUD();

    private static int i = 0;

    private Thread thread = new Thread(this);
    private VelocityVector vector = new VelocityVector();

    public GamePanel() {
        spaceship = new Spaceship(Map.X_RESOLUTION/2, Map.Y_RESOLUTION*0.1);
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            vector.descend();


            spaceship.update(vector);
            hud.update(spaceship);
            vector.reset();
            repaint();
            try { Thread.sleep(10); } catch (Exception e) {}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        e.getActionCommand();
    }

    @Override
    public void paintComponent(Graphics g) {

        /** przechowuje aktualną skalę okna w poziomie */
        double scaleX = getWidth() / (double) Map.X_RESOLUTION;
        /** przechowuje aktualną skalę okna w pionie */
        double scaleY = getHeight() / (double) Map.Y_RESOLUTION;

        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        g2d.scale(scaleX, scaleY);
        g2d.setColor(Color.lightGray);

        map.draw(g2d);
        spaceship.draw(g2d);
        hud.draw(g2d);
    }


}
