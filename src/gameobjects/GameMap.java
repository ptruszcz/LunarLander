package gameobjects;

import parsers.Parser;
import gui.GamePanel;

import java.awt.*;

/**
 * To jest klasa, która zawiera w sobie wszystkie elementy etapu i wczytuje kolejne levele
 */
public class GameMap implements Drawable {

    /** stała przechowująca rozmiar planszy w pionie */
    public static final int Y_RESOLUTION;
    /** stała przechowująca rozmiar planszy w poziomie */
    public static final int X_RESOLUTION;

    static {
        Y_RESOLUTION = Parser.getYres();
        X_RESOLUTION = Parser.getXres();
    }

    /** pole zawierające instancję powierzchni planety */
    private Surface surface;
    /** pole zawierające instancję pierwszego lądowiska */
    private LandingSpot spot1;
    /** pole zawierające instancję drugiego lądowiska */
    private LandingSpot spot2;

    /**
     * bezargumentowy konstruktor klasy GameMap. Inicjuje wszystkie pola dla pierwszego poziomu.
     */
    public GameMap() {
        surface = new Surface("1");
        spot1 = new LandingSpot("1", "1");
        spot2 = new LandingSpot("1", "2");
    }

    /**
     * getter pierwszego lądowiska
     * @return referencja pierwszego lądowiska
     */
    public LandingSpot getLandingSpot1() {
        return spot1;
    }

    /**
     * getter drugiego lądowiska
     * @return referencja drugiego lądowiska
     */
    public LandingSpot getLandingSpot2() {
        return spot2;
    }

    /**
     * getter powierzchni planety
     * @return referencja powierzchni planety
     */
    public Surface getSurface() {
        return surface;
    }

    /**
     * wczytuje wygląd powierzchni planety i położenie lądowisk
     * @param lvl poziom dla którego mają być wczytane dane
     */
    public void loadLevel(int lvl) {
        surface = new Surface(Integer.toString(lvl));
        spot1 = new LandingSpot(Integer.toString(lvl), "1");
        spot2 = new LandingSpot(Integer.toString(lvl), "2");
    }

    /**
     * metoda rysująca powierzchnię planety oraz lądowiska
     * @param g kontekst graficzny
     */
    @Override
    public void draw(Graphics g) {
        surface.draw(g);
        spot1.draw(g);
        spot2.draw(g);
    }

}
