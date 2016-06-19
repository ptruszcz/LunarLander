package gameobjects;

import parsers.Parser;
import physics.Coordinates;
import gameobjects.GameMap;

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
        points.add(new Coordinates(0, GameMap.Y_RESOLUTION));
        this.fillArrayList();
        points.add(new Coordinates(GameMap.X_RESOLUTION, GameMap.Y_RESOLUTION));
        for(Coordinates c : points)
            polygon.addPoint((int)c.getX(), (int)c.getY());
    }

    private void fillArrayList() {
        double xcoord=0;
        double ycoord;
        double[] ycoords = Parser.getYval("1");
        for(int i = 0; i<numberOfPoints-1; i++) {
            ycoord = ycoords[i];
            this.points.add(new Coordinates(xcoord, ycoord));
            xcoord += GameMap.X_RESOLUTION/numberOfPoints;
        }
        xcoord += GameMap.X_RESOLUTION/numberOfPoints;
        this.points.add(new Coordinates(xcoord, ycoords[ycoords.length-1]));
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
