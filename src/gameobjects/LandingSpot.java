package gameobjects;

import physics.Coordinates;

import java.awt.*;

/**
 * Created by piotr on 20.05.2016.
 *
 */
public class LandingSpot implements Drawable {

    /** position of left endpoint of the landing spot */
    Coordinates coordinate = new Coordinates(200, 500);
    Coordinates size = new Coordinates(50, 5);

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)coordinate.getX(), (int)coordinate.getY(), (int)size.getX(), (int)size.getY());
    }
}
