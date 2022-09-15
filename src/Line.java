import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class description: Class "Line" connects two points - a start point and an end point. The class can tell
 * the lengths of the lines, it can check if the lines has an intersection point, and it can also tell if
 * the line is the same as the the other line.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Line implements Sprite {
    // Fields
    static final int REC_NUM_OF_EDGES = 4;
    private Point start;
    private Point end;
    private Color outerColor;
    private Color innerColor;

    /**
     * A constructor which receives a start point and an end point.
     *
     * @param start a start point of the line.
     * @param end   a start point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * A constructor which receives a start point, end point, and the colors of the line.
     *
     * @param start a start point of the line.
     * @param end   a start point of the line.
     * @param outerColor represents the color of the line.
     * @param innerColor represents the color of the line too, and used to synchronize Class Line with other classes.
     */
    public Line(Point start, Point end, Color outerColor, Color innerColor) {
        this.start = start;
        this.end = end;
        this.outerColor = outerColor;
        this.innerColor = innerColor;
    }

    /**
     * A constructor which receives the x and y values of 2 points and creates them.
     *
     * @param x1 the x value of the start point.
     * @param y1 the y value of the start point.
     * @param x2 the x value of the start point.
     * @param y2 the y value of the start point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length calculates the length of the line.
     *
     * @return "length" returns the length of the lane
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle calculates the middle point of the line.
     *
     * @return "middle" returns the middle point of the line.
     */
    public Point middle() {
        double midx = (this.start.getX() + this.end.getX()) / 2;
        double midy = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midx, midy);
    }

    /**
     * @return "start" returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return "end" returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * "isIntersecting" checks if 2 lines are intersecting or not.
     *
     * @param other a line that we check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // x and y values of the first line
        double x1 = this.start.getX(), y1 = this.start.getY(), x2 = this.end.getX(), y2 = this.end.getY();

        // x and y values of the second line
        double x3 = other.start.getX(), y3 = other.start.getY(), x4 = other.end.getX(), y4 = other.end.getY();

        // we will check intersection using "cross product" formula and line segment orientation
        double o1, o2, o3, o4;
        o1 = orientation(this.start, this.end, other.start);
        o2 = orientation(this.start, this.end, other.end);
        o3 = orientation(other.start, other.end, this.start);
        o4 = orientation(other.start, other.end, this.end);

        // if o1! = o2 and o3! = o4, according to line segment orientation the lines are intersecting
        if ((o1 != o2) && (o3 != o4) && !(this.start.equals(other.start)) && !(this.start.equals(other.end))) {
            return true;
        }
        // a special case when one of the lines are a single dot, inside the second line
        if ((o1 == 0 && o2 == 0) && (o3 != o4)) {
            return true;
        }
        // the next "if" statement will contain a lot of special minor cases (when all the points are collinear)
        if ((o1 == 0 && o2 == 0 && o3 == 0 && o4 == 0 && (!(this.equals(other))))) {
            // A special case when orientations doesn't work (when the lines are part of the x and y axes)
            if (((!this.start.equals(this.end)) && (other.start.equals(other.end)))
                    && ((y1 < y2 && y3 >= y1 && y3 <= y2) || (y1 > y2 && y3 <= y1 && y3 >= y2))) {
                return true;
            }
            // A special case when orientations doesn't work (when the lines are part of the x and y axes)
            if (((!this.start.equals(this.end)) && (other.start.equals(other.end)))
                    && ((x1 < x2 && x3 >= x1 && x3 <= x2) || (x1 > x2 && x3 <= x1 && x3 >= x2))) {
                return true;
            }
            // if both of the lines are vertical
            if (x1 == x2 && x3 == x4) {
                // the next if statement contains all the possibilities of vertical lines intersecting at 1 dot
                if ((this.end.equals(other.end) && y2 > y1 && y3 >= y2)
                        || (this.end.equals(other.start) && y2 > y1 && y4 >= y2)
                        || (this.end.equals(other.end) && y2 < y1 && y3 <= y2)
                        || (this.end.equals(other.start) && y2 < y1 && y4 <= y2)) {
                    return true;
                }
            }
            // the next if statement contains all the possibilities of non vertical lines intersecting at 1 dot
            if ((this.end.equals(other.end) && x2 > x1 && x3 >= x2)
                    || (this.end.equals(other.start) && x2 > x1 && x4 >= x2)
                    || (this.end.equals(other.end) && x2 < x1 && x3 <= x2)
                    || (this.end.equals(other.start) && x2 < x1 && x4 <= x2)) {
                return true;
            }
        }
        // If the method reached this stage, then the lines don't intersect.
        return false;
    }

    /**
     * "intersectionWith" calculates the intersection point if the lines intersect.
     *
     * @param other a line that we check intersection point with.
     * @return this method returns the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if the lines don't intersect, we will return null.
        if (!this.isIntersecting(other)) {
            return null;
        }
        // x and y values of the first line
        double x1 = this.start.getX(), y1 = this.start.getY(), x2 = this.end.getX(), y2 = this.end.getY();

        // x and y values of the second line
        double x3 = other.start.getX(), y3 = other.start.getY(), x4 = other.end.getX(), y4 = other.end.getY();

        // if the first line is a single dot
        if ((x1 == x2 && y1 == y2)) {
            return this.start;
        }
        // if the second line is a single dot
        if ((x3 == x4 && y3 == y4)) {
            return other.start;
        }
        // if the first line is vertical
        if (x1 == x2 && y1 != y2) {
            double slope = (y4 - y3) / (x4 - x3);
            double n = slope * (-x3) + y3;
            double y = (slope * x1) + n;
            return new Point(x1, y);
        }
        // if the second line is vertical
        if (x3 == x4 && y3 != y4) {
            double slope = (y2 - y1) / (x2 - x1);
            double n = slope * (-x1) + y1;
            double y = (slope * x3) + n;
            return new Point(x3, y);
        }
        // a special case when both lines are vertical and 1 dot is intersecting
        if (x1 == x2 && x3 == x4) {
            if (y1 == y3 || y1 == y4) {
                return this.start;
            } else {
                return this.end;
            }
        }
        // m1 and m2 are the slopes of each line accordingly
        double m1 = (y2 - y1) / (x2 - x1);
        double m2 = (y4 - y3) / (x4 - x3);

        // A special case when the slopes are equal and 1 dot is intersecting
        if (m1 == m2) {
            if (x1 == x3 || x1 == x4) {
                return this.start;
            } else {
                return this.end;
            }
        }
        // calculation of the intersection point using regular straight line equation formula
        double n1 = m1 * (-x1) + y1;
        double n2 = m2 * (-x3) + y3;
        double x = (n2 - n1) / (m1 - m2);
        double y = m1 * x + n1;
        return new Point(x, y);
    }

    /**
     * "equals" checks if the lines are the same lines, in other words, equal.
     *
     * @param other a line that we check equality with.
     * @return this method returns true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * orientation checks the point's location compared to the line(start--->end), which means the line that starts
     * with the point "start" and ends with the point "end".
     *
     * @param start1 our line starts from the point "start".
     * @param end1   our line ends at the point "end".
     * @param point  the location of "point" will be checked compared to our line
     * @return 0 - if "point" is colinear to the line
     * 1 - if "point" is at the right side of the line
     * (-1) - if "point" is at the left side of the line
     */
    public int orientation(Point start1, Point end1, Point point) {

        // A formula to check the point's location compared to our line(start--->end)
        double orientation = ((end1.getX() - point.getX()) * (start1.getY() - point.getY()))
                - ((start1.getX() - point.getX()) * (end1.getY() - point.getY()));

        // if the point is colinear to the line then orientation == 0, and we will return 0
        if (orientation == 0) {
            return 0;
            // when the point is at the right side of the line orientation > 0 and we will return 1
        } else if (orientation > 0) {
            return 1;
            // orientation < 0 , which means the point is at the left side of the line, we will return -1
        } else {
            return -1;
        }
    }

    /**
     * "closestIntersectionToStartOfLine" checks the closest intersection point, between the start of the current line
     * to the edges of the rectangle.
     *
     * @param rect the rectangle we check intersection with.
     * @return null - if there is no intersection between the rectangle and the current line.
     * Point - the method returns the intersection point if there is one.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // "counter" will be responsible to check if an intersection point exists
        int counter = 0;
        // "minDistance" will check the minimum distance between all the intersection points to the start line
        double minDistance = Integer.MAX_VALUE;
        double x = rect.getUpperLeft().getX();
        double y = rect.getUpperLeft().getY();
        // "interLines" will be the array of the rectangle lines
        Line[] interLines = new Line[REC_NUM_OF_EDGES];
        // interPoints will be the array of the intersection points with the rectangle
        Point[] interPoints = new Point[REC_NUM_OF_EDGES];
        // the next Point initializations will represent all the rectangle vertexes
        Point topLeft = new Point(x, y);
        Point topRight = new Point(x + rect.getWidth(), y);
        Point botLeft = new Point(x, y + rect.getHeight());
        Point botRight = new Point(x + rect.getWidth(), y + rect.getHeight());
        // the next Line initializations will represent all the rectangle edges
        interLines[0] = new Line(topLeft, topRight);
        interLines[1] = new Line(botLeft, botRight);
        interLines[2] = new Line(topLeft, botLeft);
        interLines[3] = new Line(topRight, botRight);
        // the next for loop will check if there are intersections, and the minimum distance to the start of the line
        for (int i = 0; i < REC_NUM_OF_EDGES; i++) {
            interPoints[i] = this.intersectionWith(interLines[i]);
            if (interPoints[i] == null) {
                // counter will count the number of times an intersection doesn't exist
                counter++;
                continue;
            }
            // we will save the most minimum distance possible
            if (this.start.distance(interPoints[i]) < minDistance) {
                minDistance = this.start.distance(interPoints[i]);
            }
        }
        // if counter == 4, there are no intersections with the rectangle, return null
        if (counter == REC_NUM_OF_EDGES) {
            return null;
        }
        // intersection point has been found, we will search for it and return it
        for (int i = 0; i < REC_NUM_OF_EDGES; i++) {
            if (interPoints[i] == null) {
                continue;
            }
            // match has been found, return the desired point
            if (Point.equals(this.start.distance(interPoints[i]), minDistance)) {
                return interPoints[i];
            }
        }
        return null;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.outerColor);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }

    @Override
    public void timePassed() {
    }
}
