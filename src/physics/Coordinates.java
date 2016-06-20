package physics;

import java.awt.geom.Point2D;

/**
 * Klasa rozszerzająca Point2D, wykorzystywana jako wektor przesunięcia lub koordynaty
 */
public class Coordinates extends Point2D.Double {

    /**
     * kontruktor domyślny
     */
    public Coordinates() {
        super();
    }

    /**
     * konstruktor przyjmujacy współrzędne x i y
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public Coordinates(double x, double y) {
        super(x,y);
    }

    /**
     * konstruktor kopiujący
     * @param coordinates koordynaty do skopiowania
     */
    public Coordinates(Coordinates coordinates) {
        super(coordinates.getX(), coordinates.getY());
    }

    /**
     * metoda uaktualniająca koordynaty o określony wektor
     * @param vector wektor o który należy uaktualnić
     */
    public void update(VelocityVector vector) {
        double x = this.getX() + vector.getX();
        double y = this.getY() + vector.getY();
        this.setLocation(x, y);
    }
}
