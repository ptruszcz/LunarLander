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

    private Surface surface = new Surface();
    private LandingSpot spot1 = new LandingSpot(Integer.toString(GamePanel.currentLevel), "1");
    private LandingSpot spot2 = new LandingSpot(Integer.toString(GamePanel.currentLevel), "2");

    public LandingSpot getLandingSpot1() {
        return spot1;
    }
    public LandingSpot getLandingSpot2() {
        return spot2;
    }

    public Surface getSurface() {
        return surface;
    }

    @Override
    public void draw(Graphics g) {
        surface.draw(g);
        spot1.draw(g);
        spot2.draw(g);
    }
}
