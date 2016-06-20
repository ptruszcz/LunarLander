package gameobjects;

import gui.GamePanel;

import java.awt.*;

/**
 * Klasa wyświetlająca informacje na temat postępów na ekranie
 * Created by piotr on 20.05.2016.
 */
public class HUD implements Drawable {

    /** pole przechowujące referencję na statek z którego należy pobierać informacje */
    Spaceship spaceship;

    /**
     * konstruktor ustawiający referencję na statek z którego należy pobierać informacje
     * @param ship referencja na statek
     */
    public HUD(Spaceship ship) {
        this.spaceship = ship;
    }

    /**
     * metoda rysująca wszystkie elementy interfejsu, takie jak aktualna prędkość czy liczba żyć
     * @param g kontekst graficzny
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);

        if (!spaceship.getVelocity().isYVelocityLegal())
            g.setColor(Color.red);
        g.drawString("Verical velocity: " + String.format("%.2f", spaceship.getVelocity().getY()), 1, 20);
        g.setColor(Color.green);

        if (!spaceship.getVelocity().isXVelocityLegal())
            g.setColor(Color.red);
        g.drawString("Horizontal velocity: " + String.format("%.2f", spaceship.getVelocity().getX()), 1, 35);
        g.setColor(Color.green);

        g.drawString("Position: " + String.format("%.2f %.2f", spaceship.getCoordinates().getX(), spaceship.getCoordinates().getY()), 1, 50);
        g.drawString("Fuel left: " + spaceship.getFuelLeft(), 1, 65);
        g.drawString("Lives left: " + GamePanel.getLivesLeft(), 1, 80);

        if(spaceship.isPaused())
            g.drawString("Press SPACE to unpause game", GameMap.X_RESOLUTION - 200, 20);

    }
}
