package gameobjects;

import parsers.Parser;
import gui.GamePanel;

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

    private Surface surface;
    private LandingSpot spot1;
    private LandingSpot spot2;

    public GameMap() {
        surface = new Surface("1");
        spot1 = new LandingSpot("1", "1");
        spot2 = new LandingSpot("1", "2");
    }

    public LandingSpot getLandingSpot1() {
        return spot1;
    }
    public LandingSpot getLandingSpot2() {
        return spot2;
    }

    public Surface getSurface() {
        return surface;
    }

    public void loadLevel(int lvl) {
        surface = new Surface(Integer.toString(lvl));
        spot1 = new LandingSpot(Integer.toString(lvl), "1");
        spot2 = new LandingSpot(Integer.toString(lvl), "2");
    }

    @Override
    public void draw(Graphics g) {
        surface.draw(g);
        spot1.draw(g);
        spot2.draw(g);
    }

}
