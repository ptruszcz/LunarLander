package gameobjects;

import physics.Coordinates;
import parsers.Parser;

import java.awt.*;

/**
 * Created by piotr on 20.05.2016.
 *
 */
public class LandingSpot implements Drawable {

    Coordinates coordinate;
    Coordinates size;

    public LandingSpot(String level, String num) {
        coordinate = new Coordinates(Parser.getSpotcoords(level, num)[0], Parser.getSpotcoords(level, num)[1]);
        size = new Coordinates(Parser.getSpotsize(level, num), 5);
    }
    /** position of left endpoint of the landing spot */


    public Coordinates getCoordinate() {
        return coordinate;
    }

    public Coordinates getSize() {
        return size;
    }

    public boolean isOverLandingSpot(Coordinates leftLowerCorner) {
        return coordinate.getX() <= leftLowerCorner.getX() && (coordinate.getX() + size.getX()) >= leftLowerCorner.getX() + Spaceship.SHIP_SIZE;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)coordinate.getX(), (int)coordinate.getY(), (int)size.getX(), (int)size.getY());
    }

}
