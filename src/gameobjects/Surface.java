package gameobjects;

import parsers.Parser;
import physics.Coordinates;

import java.awt.*;
import java.util.ArrayList;

/**
 * Metoda reprezentująca powierzchnię planety
 */
public class Surface implements Drawable {

    /** liczba punktów do narysowania */
    private int numberOfPoints = Parser.getPoints();
    /** lista punktów z których składa się powierzchnia */
    private ArrayList<Coordinates> points;
    /** wielokąt który de facto jest powierzchnią planety */
    private Polygon polygon = new Polygon();

    /**
     *  konstruktor tworzący powierzchnię dla danego poziomu
     * @param lvl poziom
     */
    public Surface(String lvl) {
        points = new ArrayList<>(numberOfPoints+2); //żeby wielokąt nie połączył skrajnych punktów, tylko była ładna ziemia
        points.add(new Coordinates(0, GameMap.Y_RESOLUTION));
        this.fillArrayList(lvl);
        points.add(new Coordinates(GameMap.X_RESOLUTION, GameMap.Y_RESOLUTION));
        for(Coordinates c : points)
            polygon.addPoint((int)c.getX(), (int)c.getY());
    }

    /**
     * metoda wypełniająca tablicę punktów załadowanymi z pliku konfiguracyjnego
     * @param lvl
     */
    private void fillArrayList(String lvl) {
        double xcoord=0;
        double ycoord;
        double[] ycoords = Parser.getYval(lvl);
        for(int i = 0; i<numberOfPoints-1; i++) {
            ycoord = ycoords[i];
            this.points.add(new Coordinates(xcoord, ycoord));
            xcoord += GameMap.X_RESOLUTION/numberOfPoints;
        }
        xcoord += GameMap.X_RESOLUTION/numberOfPoints;
        this.points.add(new Coordinates(xcoord, ycoords[ycoords.length-1]));
    }

    /**
     * getter wielokątu-powierzchni
     * @return wielokąt który jest powierzchnią planety
     */
    public Polygon getPolygon() {
        return polygon;
    }

    /**
     * metoda rysujaca powierzchnię planety
     * @param g kontekst graficzny
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillPolygon(polygon);
    }

}
