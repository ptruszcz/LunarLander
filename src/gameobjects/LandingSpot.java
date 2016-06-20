package gameobjects;

import physics.Coordinates;
import parsers.Parser;

import java.awt.*;

/**
 * Klasa odpowiadająca lądowisku i zawierająca jego położenie i punktację
 */
public class LandingSpot implements Drawable {

    /** położenie lądowiska */
    Coordinates coordinate;
    /** rozmiar lądowiska */
    Coordinates size;
    /** wartość punktowa lądowiska */
    int value;

    /**
     * konstruktor tworzący lądowisko na podstawie danych pobranych z pliku konfiguracyjnego
     * @param level poziom dla którego ma być wczytane lądowisko
     * @param num numer ladowiska (są po dwa na każdym poziomie)
     */
    public LandingSpot(String level, String num) {
        coordinate = new Coordinates(Parser.getSpotcoords(level, num)[0], Parser.getSpotcoords(level, num)[1]);
        size = new Coordinates(Parser.getSpotsize(level, num), 5);
        value = Parser.getSpotpoints(level, num);
    }

    /**
     * getter położenia lądowiska
     * @return położenie lądowiska
     */
    public Coordinates getCoordinate() {
        return coordinate;
    }

    /**
     * getter rozmiaru lądowiska
     * @return rozmiar lądowiska
     */
    public Coordinates getSize() {
        return size;
    }

    /**
     * metoda sprawdzająca czy obserwowany statek znajduje się nad lądowiskiem
     * @param leftLowerCorner należy podać współrzędne lewego dolnego rogu statku
     * @return czy statek znajduje się nad lądowiskiem
     */
    public boolean isOverLandingSpot(Coordinates leftLowerCorner) {
        return coordinate.getX() <= leftLowerCorner.getX() && (coordinate.getX() + size.getX()) >= leftLowerCorner.getX() + Spaceship.SHIP_SIZE;
    }

    /**
     * metoda rysująca lądowiska w różnych kolorach, w zależności od punktacji
     * @param g kontekst graficzny
     */
    @Override
    public void draw(Graphics g) {
        if(value <= 40)
            g.setColor(Color.red);
        else
            g.setColor(Color.green);

        g.fillRect((int)coordinate.getX(), (int)coordinate.getY(), (int)size.getX(), (int)size.getY());
    }

}
