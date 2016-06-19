package physics;

import gameobjects.GameMap;

import java.awt.geom.Point2D;

/**
 * Created by piotr on 21.05.2016.
 *
 */
public class Coordinates extends Point2D.Double {

    public Coordinates() {
        super();
    }

    public Coordinates(double x, double y) {
        super(x,y);
    }

    public Coordinates(Coordinates coordinates) {
        super(coordinates.getX(), coordinates.getY());
    }


    public boolean areValid() {
        if(this.getX() <= GameMap.X_RESOLUTION && this.getX() >= 0)
            if(this.getY() <= GameMap.Y_RESOLUTION && this.getY() >= 0)
                return true;
        return false;
    }

    public void update(VelocityVector vector) {
        double x = this.getX() + vector.getX();
        double y = this.getY() + vector.getY();
        this.setLocation(x, y);
    }
}
