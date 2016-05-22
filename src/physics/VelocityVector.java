package physics;

/**
 * Created by piotr on 21.05.2016.
 *
 */
public class VelocityVector {

    public static final double GRAVITIONAL_ACCELERATION = 0.01;
    public static final double MAIN_ENGINE_POWER = 0.05;
    public static final double SIDE_ENGINE_POWER = 0.03;

    private double x;
    private double y;

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

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void add(VelocityVector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    public void descend() {
        this.add(0.0, GRAVITIONAL_ACCELERATION);
    }

    public void up() {
        this.add(0.0, -MAIN_ENGINE_POWER);
    }

    public void left() {
        this.add(-SIDE_ENGINE_POWER, 0.0);
    }

    public void right() {
        this.add(SIDE_ENGINE_POWER, 0.0);
    }

    public void reset() {
        this.setX(0);
        this.setY(0);
    }
}
