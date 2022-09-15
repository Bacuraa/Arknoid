import biuoop.DrawSurface;

import java.awt.Color;


/**
 * Class description: Class "Ball" draws itself on the DrawSurface based on its radius and its location (Point).
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Ball implements Sprite {
    // Fields
    private Point center;
    private Velocity v;
    private int r;
    private GameEnvironment environment;
    private Paddle paddle;
    private Color outerColor;
    private Color innerColor;

    /**
     * A constructor which receives the ball's center,it's color, and the radius.
     *
     * @param center     the center point of the ball.
     * @param r          the radius of the ball.
     * @param innerColor the inner color of the ball.
     * @param outerColor the outer color of the ball.
     */
    public Ball(Point center, int r, Color outerColor, Color innerColor) {
        this.center = center;
        this.r = r;
        this.outerColor = outerColor;
        this.innerColor = innerColor;
    }

    /**
     * An enhanced constructor which receives the ball's center,it's color,it's radius and the velocity.
     *
     * @param center     the center point of the ball.
     * @param r          the radius of the ball.
     * @param v          the velocity of the ball.
     * @param outerColor the outer color of the ball.
     * @param innerColor the inner color of the ball.
     */
    public Ball(Point center, int r, Color outerColor, Color innerColor, Velocity v) {
        this.center = center;
        this.r = r;
        this.v = v;
        this.outerColor = outerColor;
        this.innerColor = innerColor;
    }

    /**
     * A constructor which receives a start point and an end point.
     *
     * @param x          the x value of the center point of the ball.
     * @param y          the y value of the center point of the ball.
     * @param r          the radius of the ball.
     * @param outerColor the outer color of the ball.
     * @param innerColor the inner color of the ball.
     */
    public Ball(double x, double y, int r, Color outerColor, Color innerColor) {
        this.center = new Point(x, y);
        this.r = r;
        this.innerColor = innerColor;
    }

    /**
     * "setGame" sets the game environment of the ball.
     *
     * @param game is the game environment of the ball.
     */
    public void setGame(GameEnvironment game) {
        this.environment = game;
    }

    /**
     * @return the method returns the game environment of the ball.
     */
    public GameEnvironment getGame() {
        return this.environment;
    }

    /**
     * Method to the object ball to be able to add itself to the game.
     *
     * @param g the game that will be played with the ball.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * @return this method returns the center coordinates of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @param x sets the 'x' value of the ball's center.
     * @param y sets the 'y' value of the ball's center.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * @param paddle1 sets the paddle for the game.
     */
    public void setPaddle(Paddle paddle1) {
        this.paddle = paddle1;
    }

    /**
     * @return "getX" returns the x value of the center point of the circle.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return "getY" returns the y value of the center point of the circle.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return "getSize" returns the radius of the circle.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return "getSize" returns the color of the circle.
     */
    public java.awt.Color getInnerColor() {
        return this.innerColor;
    }

    /**
     * draw0n draws the ball on the given DrawSurface according to the size of the circle and the color desired.
     *
     * @param surface the surface we will draw on (we will draw the circles on it).
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.innerColor);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(this.outerColor);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * timePassed will execute actions based on the time passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * A constructor to set the ball's velocity.
     *
     * @param velocity the velocity of the ball.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * "getMeOut" is a method that is responsible for preventing the ball from getting inside the paddle.
     *
     * @param px represents the 'x' value of the upper left point of our paddle.
     * @param py represents the 'y' value of the upper left point of our paddle.
     */
    public void getMeOut(double px, double py) {
        double x1 = this.center.getX(), y1 = this.center.getY();
        double x2 = x1 + this.v.getDx(), y2 = y1 + this.v.getDy();
        double epsilon = Math.pow(10, -10);
        double speed = Math.sqrt(Math.pow(this.v.getDx(), 2) + Math.pow(this.v.getDy(), 2));
        // if the ball is closer to the left edge of the paddle, we will make changes accordingly
        if (x1 < px + ((double) this.paddle.getWidth() / 2)) {
            // if the paddle smashed the ball into the left wall, we will reset the ball's location accordingly
            if (x1 - this.paddle.getSpeed() - Math.abs(this.v.getDx()) <= GameLevel.SIDE_BORDER_SIZE) {
                this.center = new Point(GameLevel.SIDE_BORDER_SIZE + epsilon,
                        GameLevel.BORDER_HEIGHT - GameLevel.SIDE_BORDER_SIZE - Paddle.HEIGHT - epsilon);
                this.v = Velocity.fromAngleAndSpeed(315, speed);
            } else {
                // else we will move the ball according the paddles speed, and wont let it get inside the paddle
                this.center = new Point(x1 - this.paddle.getSpeed(), y2);
            }
            // if the ball is closer to the right edge of the paddle, we will make changes accordingly
        } else {
            // if the paddle smashed the ball into the right wall, we will reset the ball's location accordingly
            if (x1 + this.paddle.getSpeed() + Math.abs(this.v.getDx())
                    >= GameLevel.BORDER_WIDTH - GameLevel.SIDE_BORDER_SIZE) {
                this.center = new Point(GameLevel.BORDER_WIDTH - GameLevel.SIDE_BORDER_SIZE - epsilon,
                        GameLevel.BORDER_HEIGHT - GameLevel.SIDE_BORDER_SIZE - Paddle.HEIGHT - epsilon);
                this.v = Velocity.fromAngleAndSpeed(45, speed);
            } else {
                // else we will move the ball according the paddles speed, and wont let it get inside the paddle
                this.center = new Point(x1 + this.paddle.getSpeed() + 1, y2);
            }
        }
    }

    /**
     * A constructor to set the ball's velocity with a new x and y values.
     *
     * @param dx the 'x' value that is going to be added as velocity to the 'x' value of the circle's center point.
     * @param dy the 'x' value that is going to be added as velocity to the 'x' value of the circle's center point.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return method to return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * "moveOneStep" a method that draws a new ball according to its velocity and position.
     */
    public void moveOneStep() {
        if (this.v == null) {
            return;
        }
        double epsilon = Math.pow(10, -10);
        double x1 = this.center.getX(), y1 = this.center.getY();
        double x2 = x1 + this.v.getDx(), y2 = y1 + this.v.getDy();
        double px = this.paddle.getCollisionRectangle().getUpperLeft().getX();
        double py = this.paddle.getCollisionRectangle().getUpperLeft().getY();
        // if the ball happens to be inside our paddle, we will prevent it immediately
        if ((y1 < py + Paddle.HEIGHT) && y1 > py && (x1 >= px) && (x1 <= px + this.paddle.getWidth())) {
            // A method to prevent the ball from getting inside the paddle
            this.getMeOut(px, py);
        }
        // we will compute the line trajectory to predict intersection
        Line trajectory = new Line(this.center, new Point(x2, y2));
        CollisionInfo collision = this.environment.getClosestCollision(trajectory);
        // if there is no intersection, we can move the ball according to it's velocity
        if (collision == null) {
            this.center = this.v.applyToPoint(this.center);
            // we do have a collision point, we will insert the new circle center into a new temporary point
        } else {
            Point collisionPoint = collision.collisionPoint();
            double x0 = collisionPoint.getX(), y0 = collisionPoint.getY();
            // to prevent the ball from touching the sprites, we will add a minor epsilon accordingly
            if (this.v.getDx() > 0) {
                x0 = x0 - epsilon;
            }
            if (this.v.getDx() < 0) {
                x0 = x0 + epsilon;
            }
            if (this.v.getDy() > 0) {
                y0 = y0 - epsilon;
            }
            if (this.v.getDy() < 0) {
                y0 = y0 + epsilon;
            }
            this.center = new Point(x0, y0);
            this.v = collision.collisionObject().hit(this, collisionPoint, this.v);
        }
    }
}
