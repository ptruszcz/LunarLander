package gameobjects;

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

    private boolean crashed = false;
    private boolean landed = false;

    //uzyc settera
    public Spaceship(double xCoordinate, double yCoordinate) {
        this.coordinates = new Coordinates(xCoordinate, yCoordinate);
        velocity = new VelocityVector();
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

    private void updateVelocity(VelocityVector vector) {
        this.velocity.add(vector);
    }

    private void updatePosition(VelocityVector velocity) {
        this.coordinates.update(velocity);
    }

    public void update(VelocityVector vector) {
        this.updateVelocity(vector);
        this.updatePosition(this.getVelocity());
    }


    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
    }

    public boolean isCrashed() {
        return false;
    }

    public void setLanded(boolean landed) {
        this.landed = landed;
    }

    public boolean isLanded() {
        return false;
    }



    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)this.coordinates.getX(), (int)this.coordinates.getY(), 30, 30);
    }

}
