package physics;

import gameobjects.Spaceship;
import parsers.Parser;

/**
 * Klasa reprezentująca wektor prędkości
 */
public class VelocityVector {

    /** przyspieszenie grawitacyjne */
    public static final double GRAVITIONAL_ACCELERATION;
    /** maksymalna prędkość pionowa umożliwiająca lądowanie */
    public static final double MAX_Y_VELOCITY;
    /** maksymalna prędkość pozioma umożliwiająca lądowanie */
    public static final double MAX_X_VELOCITY;

    /** składowa x wektora */
    private double x;
    /** skłądowa y wektora */
    private double y;

    static {
        GRAVITIONAL_ACCELERATION = Parser.getGravity();
        MAX_Y_VELOCITY = Parser.getMaxyvel();
        MAX_X_VELOCITY = Parser.getMaxxvel();
    }

    /**
     * konstruktor wektora, domyślnie ustawiający jego wartość na 0
     */
    public VelocityVector() {
        this.setX(0);
        this.setY(0);
    }

    /**
     * konstruktor tworzący wektor wg zadanych parametrów
     * @param x składowa pozioma wektora
     * @param y składowa pionowa wektora
     */
    public VelocityVector(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * setter składowej poziomej
     * @param x wartość składowej poziomej
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * getter składowej poziomej
     * @return wartość składowej poziomej
     */
    public double getX() {
        return x;
    }

    /**
     * setter składowej pionowej
     * @param y wartość składowej pionowej
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * getter składowej pionowej
     * @return y wartość składowej pionowej
     */
    public double getY() {
        return y;
    }

    /**
     * metoda dodająca składowe do istniejącego wektora
     * @param x składowa pozioma
     * @param y składowa pionowa
     */
    public synchronized void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /**
     * metoda dodająca wektor do istniejącego
     * @param vector wektor do dodania
     */
    public synchronized void add(VelocityVector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    /**
     * metoda zmieniająca wektor o składową grawitacyjną
     */
    public synchronized void descend() {
        this.add(0.0, GRAVITIONAL_ACCELERATION);
    }

    /**
     * metoda zmieniająca wektor o moc silnika głównego
     */
    public synchronized void up() {
        this.add(0.0, -Spaceship.MAIN_ENGINE_POWER);
    }

    /**
     * metoda zmieniająca wektor o moc silnika bocznego (lot w lewo)
     */
    public synchronized void left() {
        this.add(-Spaceship.SIDE_ENGINE_POWER, 0.0);
    }

    /**
     * metoda zmieniająca wektor o moc silnika bocznego (lot w prawo)
     */
    public synchronized void right() {
        this.add(Spaceship.SIDE_ENGINE_POWER, 0.0);
    }

    /**
     * metoda resetująca wektor
     */
    public synchronized void reset() {
        this.setX(0);
        this.setY(0);
    }

    /**
     * metoda sprawdzająca czy prędkość pozioma nie przekracza maksymalnej dopuszczalnej do lądowania
     * @return prawda lub fałsz
     */
    public boolean isXVelocityLegal() {
        return (getX() < MAX_X_VELOCITY);
    }

    /**
     * metoda sprawdzająca czy prędkość pionowa nie przekracza maksymalnej dopuszczalnej do lądowania
     * @return prawda lub fałsz
     */
    public boolean isYVelocityLegal() {
       return (getY() < MAX_Y_VELOCITY);
    }

    /**
     * metoda sprawdzająca czy obie prędkości nie przekraczają maksymalnej dopuszczalnej do lądowania
     * @return prawda lub fałsz
     */
    public boolean isVelocityLegal() {
        return this.isXVelocityLegal() && this.isYVelocityLegal();
    }
}
