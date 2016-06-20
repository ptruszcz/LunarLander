package gameobjects;

import java.awt.*;

/**
 * Interfejs który przyjmują wszystkie rysowalne obiekty w grze
 */
public interface Drawable {
    /**
     * metoda pozwalajaca na rysowanie każdego obiektu
     * @param g kontekst graficzny
     */
    void draw(Graphics g);
}
