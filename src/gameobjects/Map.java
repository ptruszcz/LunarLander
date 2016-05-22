package gameobjects;

import java.awt.*;

/**
 * To jest klasa, która zawiera w sobie wszystkie elementy etapu i wczytuje kolejne levele
 * Created by piotr on 20.05.2016.
 */
public class Map implements Drawable {

    public static final int Y_RESOLUTION = 640;
    public static final int X_RESOLUTION = 960;

    private Surface surface = new Surface();
    private LandingSpot spot = new LandingSpot();

    @Override
    public void draw(Graphics g) {
        surface.draw(g);
        spot.draw(g);
    }
}
