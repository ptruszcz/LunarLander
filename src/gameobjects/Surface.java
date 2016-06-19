package gameobjects;

import parsers.Parser;
import physics.Coordinates;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by piotr on 20.05.2016.
 *
 */
public class Surface implements Drawable {

    private int numberOfPoints = Parser.getPoints(); //do zmiany
    private ArrayList<Coordinates> points;
    private Polygon polygon = new Polygon();

    public Surface() {
        points = new ArrayList<>(numberOfPoints+2); //żeby wielokąt nie połączył skrajnych punktów, tylko była ładna ziemia
        points.add(0,new Coordinates(0, 640));
        this.fillArrayList();
        points.add(new Coordinates(960, 640));

        for(Coordinates c : points)
            polygon.addPoint((int)c.getX(), (int)c.getY());
    }

    //tymczasowa funkcja!
    private void fillArrayList() {
        int xcoord = 0;
        int ycoord = 500;
        for(int i = 0; i<numberOfPoints; i++) {
            this.points.add(new Coordinates(xcoord, ycoord));
            xcoord += 12;
        }
    }

    public Polygon getPolygon() {
        return polygon;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillPolygon(polygon);
    }
}
