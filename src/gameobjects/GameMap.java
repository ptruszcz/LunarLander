package gameobjects;

import parsers.Parser;

import java.awt.*;

/**
 * To jest klasa, która zawiera w sobie wszystkie elementy etapu i wczytuje kolejne levele
 * Created by piotr on 20.05.2016.
 */
public class GameMap implements Drawable {

    public static final int Y_RESOLUTION;
    public static final int X_RESOLUTION;

    static {
        Y_RESOLUTION = Parser.getYres();
        X_RESOLUTION = Parser.getXres();

    }

    private Surface surface = new Surface();
    private LandingSpot spot = new LandingSpot();

    public LandingSpot getLandingSpot() {
        return spot;
    }

    public Surface getSurface() {
        return surface;
    }

    @Override
    public void draw(Graphics g) {
        surface.draw(g);
        spot.draw(g);
    }
}
