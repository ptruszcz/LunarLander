package gameobjects;

import parsers.Parser;
import physics.Coordinates;
import physics.VelocityVector;

import java.awt.*;

/**
 * Created by piotr on 20.05.2016.
 *
 */
public class Spaceship implements Drawable {

    private Coordinates coordinates;
    private VelocityVector velocity;
    private int fuelLeft;
    boolean areEnginesWorking;

    public static final double MAIN_ENGINE_POWER;
    public static final double SIDE_ENGINE_POWER;
    public static final int SHIP_SIZE;
    public static final int STARTING_FUEL;
    public static final int X_FUEL_CONSUMPTION; //units per 0.1 acceleration
    public static final int Y_FUEL_CONSUMPTION; //units per 0.1 acceleration

    static {
        MAIN_ENGINE_POWER = Parser.getMaineng();
        SIDE_ENGINE_POWER = Parser.getSideeng();
        SHIP_SIZE = Parser.getShipsize();
        STARTING_FUEL = Parser.getStartfuel();
        X_FUEL_CONSUMPTION = Parser.getFuelconsx();
        Y_FUEL_CONSUMPTION = Parser.getFuelconsy();
    }

    public enum State {
        LANDED, CRASHED, IN_AIR, PAUSED
    }

    private State state = State.PAUSED;

    public Spaceship(double xCoordinate, double yCoordinate) {
        this.coordinates = new Coordinates(xCoordinate, yCoordinate);
        velocity = new VelocityVector();
        fuelLeft = STARTING_FUEL;
    }

    public Spaceship(Coordinates coordinates) {
        this.coordinates = new Coordinates(coordinates);
        velocity = new VelocityVector();
    }

    //zedytować - rzucanie wyjątków itd.
    public void setCoordinates(Coordinates coordinates) {
        if(coordinates.areValid())
            this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setVelocity(VelocityVector velocity) {
        this.velocity = velocity;
    }

    public VelocityVector getVelocity() {
        return velocity;
    }

    public int getFuelLeft() {
        return fuelLeft;
    }

    private void updateVelocity(VelocityVector vector) {
        this.velocity.add(vector);
    }

    private void updatePosition(VelocityVector velocity) {
        this.coordinates.update(velocity);
    }

    private void updateFuel(VelocityVector vector) {
        fuelLeft += (Math.abs(vector.getY()) - VelocityVector.GRAVITIONAL_ACCELERATION) * Y_FUEL_CONSUMPTION;
        fuelLeft -= Math.abs(vector.getX()) * X_FUEL_CONSUMPTION;
    }

    public void update(VelocityVector vector) {
        this.updateVelocity(vector);
        this.updateFuel(vector);
        this.updatePosition(this.getVelocity());
    }

    public void checkForCollisions(GameMap gameMap) {
        Coordinates leftLowerCorner = new Coordinates(getCoordinates().getX(), getCoordinates().getY() + SHIP_SIZE);

        //ograniczyc o rozmiary planszy i predkosc
        if(gameMap.getSurface().getPolygon().contains(leftLowerCorner)) {
            if (gameMap.getLandingSpot().isOverLandingSpot(leftLowerCorner) && velocity.isVelocityLegal())
                setState(State.LANDED);
            else
                setState(State.CRASHED);
        }
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public boolean isCrashed() {
        return state == State.CRASHED;
    }

    public boolean isLanded() {
        return state == State.LANDED;
    }

    public boolean isInAir() {
        return state == State.IN_AIR;
    }

    public boolean isPaused() {
        return state == State.PAUSED;
    }

    public void togglePaused() {
        if(isPaused())
            this.setState(State.IN_AIR);
        else
            this.setState(State.PAUSED);
    }

    public void setAreEnginesWorking(boolean areWorking) {
        areEnginesWorking = areWorking;
    }

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
