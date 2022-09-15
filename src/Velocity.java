/**
 * Class description: Class "Velocity" specifies the change in position on the `x` and the `y` axes.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Velocity {
    // Fields
    private double dx;
    private double dy;

    /**
     * A constructor which receives 'x' and a 'y' value that will change the value of the circle's center point.
     *
     * @param dx the 'x' value which will be added to the 'x' value of the circle's center point.
     * @param dy the 'y' value which will be added to the 'y' value of the circle's center point.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the 'x' value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the 'y' value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param newDx will be the new 'x' value of velocity.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
    /**
     * @param newDy will be the new 'y' value of velocity.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * method "applyToPoint" takes a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p represents the circle center's point.
     * @return returns the updated point after a single step of the ball.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * A constructor which receives the angle of the moving ball, and it's speed.
     *
     * @param angle represents the angle (direction) that the ball is going to move.
     * @param speed represents the speed of the moving ball.
     * @return returns the updated point after a single step of the ball.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle - 90);
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }
}