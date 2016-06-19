package gameobjects;

import java.awt.*;

/**
 * prędkość, wysokość itp.
 * Created by piotr on 20.05.2016.
 */
public class HUD implements Drawable {

    Spaceship spaceship;

    public HUD(Spaceship ship) {
        this.spaceship = ship;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);

        if (!spaceship.getVelocity().isYVelocityLegal())
            g.setColor(Color.red);
        g.drawString("Verical velocity: " + String.format("%.2f", spaceship.getVelocity().getY()), 1, 20);
        g.setColor(Color.green);

        if (!spaceship.getVelocity().isXVelocityLegal())
            g.setColor(Color.red);
        g.drawString("Horizontal velocity: " + String.format("%.2f", spaceship.getVelocity().getX()), 1, 35);
        g.setColor(Color.green);

        g.drawString("Position: " + String.format("%.2f %.2f", spaceship.getCoordinates().getX(), spaceship.getCoordinates().getY()), 1, 50);
        g.drawString("Fuel left: " + spaceship.getFuelLeft(), 1, 65);

        switch (spaceship.getState()) {
            case IN_AIR:
                break;

            case PAUSED:
                g.drawString("Press SPACE to unpause game", GameMap.X_RESOLUTION / 2, GameMap.Y_RESOLUTION / 2);
                break;

            case CRASHED:
                g.drawString("CRASHED!", 200, 200);
                break;

            case LANDED:
                g.drawString("LANDED!", 200, 200);
                break;
        }
    }
}
