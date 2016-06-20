package gameobjects;

import gui.GamePanel;
import parsers.Parser;
import physics.Coordinates;
import physics.VelocityVector;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Klasa reprezentująca statek kosmiczny.
 */
public class Spaceship implements Drawable {

    /** położenie statku */
    private Coordinates coordinates;
    /** prędkość statku */
    private VelocityVector velocity;
    /** liczba pozostałego paliwa */
    private int fuelLeft;
    /** zmienna stwierdzająca czy silniki są w trakcie pracy */
    boolean areEnginesWorking;
    /** prostokąt reprezentujący ciało statku */
    Rectangle2D rectangle;


    /** stała przechowująca moc głównego silnika */
    public static final double MAIN_ENGINE_POWER;
    /** stała przechowująca moc bocznych silników */
    public static final double SIDE_ENGINE_POWER;
    /** stała przechowująca rozmiar statku */
    public static final int SHIP_SIZE;
    /** stała przechowująca startową liczbę paliwa */
    public static final int STARTING_FUEL;
    /** stała przechowująca pobór paliwa silników bocznych */
    public static final int X_FUEL_CONSUMPTION; //units per 0.1 acceleration
    /** stała przechowująca pobór paliwa silnika głównego */
    public static final int Y_FUEL_CONSUMPTION; //units per 0.1 acceleration

    static {
        X_FUEL_CONSUMPTION = Parser.getFuelconsx();
        Y_FUEL_CONSUMPTION = Parser.getFuelconsy();
        MAIN_ENGINE_POWER = Parser.getMaineng();
        SIDE_ENGINE_POWER = Parser.getSideeng();
        SHIP_SIZE = Parser.getShipsize();
        STARTING_FUEL = Parser.getStartfuel();
    }


    /**
     * możliwe stany jakie może przyjąć statek
     */
    public enum State {
        LANDED, CRASHED, IN_AIR, PAUSED
    }

    /** aktualny stan statku. Domyślnie jest zapauzowany. */
    private State state = State.PAUSED;

    /**
     * konstruktor statku przyjmujący osobno jego położenie
     * @param xCoordinate położenie statku w poziomie
     * @param yCoordinate położenie statku w pionie
     */
    public Spaceship(double xCoordinate, double yCoordinate) {
        this.coordinates = new Coordinates(xCoordinate, yCoordinate);
        velocity = new VelocityVector();
        rectangle = new Rectangle((int)coordinates.getX(), (int)coordinates.getY(), SHIP_SIZE, SHIP_SIZE);
        fuelLeft = STARTING_FUEL;
    }

    /**
     * konstruktor statku przyjmujący jego koordynaty
     * @param coordinates położenie statku
     */
    public Spaceship(Coordinates coordinates) {
        this.coordinates = new Coordinates(coordinates);
        velocity = new VelocityVector();
        rectangle = new Rectangle((int)coordinates.getX(), (int)coordinates.getY(), SHIP_SIZE, SHIP_SIZE);
        fuelLeft = STARTING_FUEL;
    }

    /**
     * getter położenia statku
     * @return koordynaty statku
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * getter prędkości statku
     * @return prędkość statku
     */
    public VelocityVector getVelocity() {
        return velocity;
    }

    /**
     * getter pozostałego paliwa w statku
     * @return paliwo pozostałe w statku
     */
    public int getFuelLeft() {
        return fuelLeft;
    }

    /**
     * metoda odświeżająca aktualną prędkość statku
     * @param vector wektor prędkości o który statek ma się uaktualnić
     */
    private void updateVelocity(VelocityVector vector) {
        this.velocity.add(vector);
    }

    /**
     * metoda odświeżająca położenie statku
     * @param velocity wektor prędkości powodujący przesunięcie statku
     */
    private void updatePosition(VelocityVector velocity) {
        this.coordinates.update(velocity);
        rectangle.setFrame(new Rectangle((int)coordinates.getX(), (int)coordinates.getY(), SHIP_SIZE, SHIP_SIZE));
    }

    /**
     * metoda uaktualniająca ilość paliwa pozostałą w baku. Wylicza ją na podstawie wektora prędkości.
     * @param vector wektor prędkości statku
     */
    private void updateFuel(VelocityVector vector) {
        fuelLeft += (Math.abs(vector.getY()) - VelocityVector.GRAVITIONAL_ACCELERATION) * Y_FUEL_CONSUMPTION;
        fuelLeft -= Math.abs(vector.getX()) * X_FUEL_CONSUMPTION;
    }

    /**
     * metoda uaktualniająca wszystkie parametry statku
     * @param vector wektor zmiany prękości
     */
    public void update(VelocityVector vector) {
        this.updateVelocity(vector);
        this.updateFuel(vector);
        this.updatePosition(this.getVelocity());
    }

    /**
     * metoda sprawdzająca czy statek nie wszedł w kontakt z otoczeniem lub wyleciał poza planszę
     * @param gameMap mapa na której znajduje się statek
     */
    public void checkForCollisions(GameMap gameMap) {
        Coordinates leftLowerCorner = new Coordinates(getCoordinates().getX(), getCoordinates().getY() + SHIP_SIZE);

        if(getCoordinates().getY() > GameMap.Y_RESOLUTION)
            setState(State.CRASHED);

        if(gameMap.getSurface().getPolygon().intersects(rectangle)) {
            if ((gameMap.getLandingSpot1().isOverLandingSpot(leftLowerCorner) || gameMap.getLandingSpot2().isOverLandingSpot(leftLowerCorner)) && velocity.isVelocityLegal())
                setState(State.LANDED);
            else
                setState(State.CRASHED);
        }
    }

    /**
     * getter stanu statku
     * @return stan statku
     */
    public State getState() {
        return state;
    }

    /**
     * setter stanu statku
     * @param state stan który ma przyjąć statek
     */
    private void setState(State state) {
        this.state = state;
    }

    /**
     * metoda sprawdzająca czy statek jest rozbity
     * @return prawda lub fałsz
     */
    public boolean isCrashed() {
        return state == State.CRASHED;
    }

    /**
     * metoda sprawdzająca czy statek wylądował
     * @return prawda lub fałsz
     */
    public boolean isLanded() {
        return state == State.LANDED;
    }

    /**
     * metoda sprawdzająca czy statek jest w powietrzu
     * @return prawda lub fałsz
     */
    public boolean isInAir() {
        return state == State.IN_AIR;
    }

    /**
     * metoda sprawdzająca czy statek jest zapauzowany
     * @return prawda lub fałsz
     */
    public boolean isPaused() {
        return state == State.PAUSED;
    }

    /**
     * metoda przełączająca stan statku z pauzy na aktywny i odwrotnie
     */
    public void togglePaused() {
        if(isPaused())
            this.setState(State.IN_AIR);
        else
            this.setState(State.PAUSED);
    }

    /**
     * setter pracy silników
     * @param areWorking czy silniki aktualnie pracują
     */
    public void setAreEnginesWorking(boolean areWorking) {
        areEnginesWorking = areWorking;
    }

    /**
     * metoda rysująca statek wraz ze wszystkimi jego elementami
     * @param g kontekst graficzny
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)coordinates.getX(), (int)coordinates.getY(), SHIP_SIZE, SHIP_SIZE);
        g.fillArc((int)coordinates.getX(), (int)coordinates.getY()-SHIP_SIZE/2, SHIP_SIZE, SHIP_SIZE, 0, 180);

        if (areEnginesWorking) {
            g.setColor(Color.red);
            g.fillRect((int)coordinates.getX()+SHIP_SIZE/4, (int)coordinates.getY()+SHIP_SIZE, SHIP_SIZE/2, 10);
        }
    }


}
