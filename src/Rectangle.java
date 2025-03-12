import java.util.ArrayList;

/**
 * Class description: Class "Rectangle" creates rectangle objects, basted on it's width, it's height, and it's
 * upperLeft point. Moreover, the class can check it the rectangle has intersection points with any line.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Rectangle {

    // Fields
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * A constructor which receives width, height, and a vertex point of the rectangle.
     *
     * @param upperLeft the upper left vertex point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * "intersectionsPoints", the method receives a line, checks the intersections points with the current rectangle,
     * adds them into a list of intersection points and returns this list.
     *
     * @param line we will check intersection with the rectangle with this line.
     * @return the method returns a list of intersection points with the current rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> collisionPoints = new ArrayList<>();
        Point intersection = line.closestIntersectionToStartOfLine(this);
        // only if intersection exists, we will add it to the list.
        if (intersection != null) {
            collisionPoints.add(intersection);
        }
        return collisionPoints;
    }
    /**
     * @param newUpperLeft the new upperLeft point of the rectangle.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * @return the field width in the class.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the field height in the class.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the field getUpperLeft in the class.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}