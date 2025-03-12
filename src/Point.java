/**
 * Class description: Class "Point" creates a new point. A point has an
 * x and a y value, and the Class can measure the distance to other points
 * and check if it is equal to another point.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Point {
    // Fields
    private double x;
    private double y;

    /**
     * A constructor which receives x and y as values of the point.
     *
     * @param x a value stored in the point.
     * @param y a value stored in the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * equals will check if 2 values are equal.
     *
     * @param value1 we will this if this value is equal to the other value received in this method.
     * @param value2 we will this if this value is equal to the other value received in this method.
     * @return true - if the values are equal, false - if they are not equal.
     */
    public static boolean equals(double value1, double value2) {
        double epsilon = Math.pow(10, -7);
        return (Math.abs(value1 - value2) < epsilon);
    }

    /**
     * Distance returns the distance of this point to the other point.
     *
     * @param other the other point we calculate the distance with.
     * @return the distance between the 2 points is returned.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * angle calculates the angle between 2 points.
     *
     * @param other the other point we calculate the angle with.
     * @return the angle between the 2 points is returned.
     */
    public double angle(Point other) {
        double x1 = this.x, y1 = this.y;
        double x2 = other.getX(), y2 = other.getY();
        double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
        angle = angle + Math.ceil(-angle / 360) * 360;
        return angle;
    }

    /**
     * equals returns true if the points are equal, false otherwise.
     *
     * @param other the other point we check equality with.
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -10);
        double x1 = this.getX(), y1 = this.getY();
        double x2 = other.getX(), y2 = other.getY();
        return ((Math.abs(x2 - x1) < epsilon) && (Math.abs(y2 - y1) < epsilon));
    }

    /**
     * getX returns the field x in the class.
     *
     * @return the field x in the class.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY returns the fieldy in the class.
     *
     * @return the field y in the class.
     */
    public double getY() {
        return this.y;
    }
}