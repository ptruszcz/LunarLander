package gameobjects;

import physics.Coordinates;
import physics.VelocityVector;

import java.awt.*;

/**
 * prędkość, wysokość itp.
 * Created by piotr on 20.05.2016.
 */
public class HUD implements Drawable {

    Coordinates coordinates;
    VelocityVector velocity;

    public void update(Spaceship spaceship) {
        this.coordinates = spaceship.getCoordinates();
        this.velocity = spaceship.getVelocity();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawString("Verical velocity: " + String.format("%.2f", velocity.getY()), 1, 20);
        g.drawString("Pozycja: " + String.format("%.2f %.2f", coordinates.getX(), coordinates.getY()), 1, 35);
    }
}
