package physics;

import gameobjects.Spaceship;
import parsers.Parser;

/**
 * Created by piotr on 21.05.2016.
 *
 */
public class VelocityVector {

    public static final double GRAVITIONAL_ACCELERATION;
    public static final double MAX_Y_VELOCITY;
    public static final double MAX_X_VELOCITY;


    private double x;
    private double y;

    static {
        GRAVITIONAL_ACCELERATION = Parser.getGravity();
        MAX_Y_VELOCITY = Parser.getMaxyvel();
        MAX_X_VELOCITY = Parser.getMaxxvel();
    }

    public VelocityVector() {
        this.setX(0);
        this.setY(0);
    }

    public VelocityVector(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public synchronized void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public synchronized void add(VelocityVector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    public synchronized void descend() {
        this.add(0.0, GRAVITIONAL_ACCELERATION);
    }

    public synchronized void up() {
        this.add(0.0, -Spaceship.MAIN_ENGINE_POWER);
    }

    public synchronized void left() {
        this.add(-Spaceship.SIDE_ENGINE_POWER, 0.0);
    }

    public synchronized void right() {
        this.add(Spaceship.SIDE_ENGINE_POWER, 0.0);
    }

    public synchronized void reset() {
        this.setX(0);
        this.setY(0);
    }

    public boolean isXVelocityLegal() {
        return (getX() < MAX_X_VELOCITY);
    }

    public boolean isYVelocityLegal() {
       return (getY() < MAX_Y_VELOCITY);
    }

    public boolean isVelocityLegal() {
        return this.isXVelocityLegal() && this.isYVelocityLegal();
    }
}
