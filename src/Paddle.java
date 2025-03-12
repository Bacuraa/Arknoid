import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Class description: Class "Paddle" The Paddle is the player in the game. It is a rectangle that is controlled
 * by the arrow keys, and moves according to the player key presses. It should implement the Sprite and the
 * Collidable interfaces. It should also know how to move to the left and to the right
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Paddle implements Sprite, Collidable {
    // constant variables
    static final int HEIGHT = 27;            // the paddle height     // paddle moving speed

    // Fields
    private Rectangle paddle;
    private biuoop.KeyboardSensor keyboard;
    private java.awt.Color color;
    private int speed;
    private int width;

    /**
     * A contructor to build our object paddle.
     *
     * @param keyboard keyboard will be resposible to give us the ability to control the paddle while playing.
     * @param paddle   a rectangle which will act as a paddle for our game.
     * @param color    the color of the paddle.
     * @param speed    represents the moving speed of the paddle.
     * @param width    represents the paddle's width.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddle, Color color, int speed, int width) {
        this.keyboard = keyboard;
        this.paddle = paddle;
        this.color = color;
        this.speed = speed;
        this.width = width;
    }

    /**
     * a simple function that will move the paddle a single step to the left.
     */
    public void moveLeft() {
        double x = this.paddle.getUpperLeft().getX(), y = this.paddle.getUpperLeft().getY();
        if (x == GameLevel.SIDE_BORDER_SIZE) {
            return;
        } else if (x - this.speed < GameLevel.SIDE_BORDER_SIZE) {
            this.paddle.setUpperLeft(new Point(GameLevel.SIDE_BORDER_SIZE + 1, y));
        } else {
            this.paddle.setUpperLeft(new Point(x - this.speed, y));
        }
    }

    /**
     * a simple function that will move the paddle a single step to the right.
     */
    public void moveRight() {
        double x = this.paddle.getUpperLeft().getX(), y = this.paddle.getUpperLeft().getY();
        double x1 = x + this.width;
        if (x1 == GameLevel.BORDER_WIDTH - GameLevel.SIDE_BORDER_SIZE) {
            return;
        } else if (x1 + this.speed > GameLevel.BORDER_WIDTH - GameLevel.SIDE_BORDER_SIZE - 1) {
            this.paddle.setUpperLeft(new Point(GameLevel.BORDER_WIDTH - GameLevel.SIDE_BORDER_SIZE - this.width, y));
        } else {
            this.paddle.setUpperLeft(new Point(x + this.speed, y));
        }
    }

    /**
     * A function of the interface "Sprite", responsible for making objects do things.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * method "drawOn" is responsible for drawing our moving animations.
     *
     * @param d is the draw surface we are drawing on.
     */
    public void drawOn(DrawSurface d) {
        double x = this.paddle.getUpperLeft().getX(), y = this.paddle.getUpperLeft().getY();
        d.setColor(this.color);
        d.fillRectangle(((int) x), ((int) y), this.width, HEIGHT);
        d.setColor(Color.black);
        d.drawRectangle(((int) x), ((int) y), this.width, HEIGHT);
    }

    /**
     * Rectangle just returns the object that had collision with the ball.
     *
     * @return the object that had collision with the ball.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * The method "hit" changes the velocity of the ball, according to the spot of the collision point.
     *
     * @param collisionPoint  is the collision Point with the object.
     * @param currentVelocity is the velocity of the object.
     * @param hitter          is the ball that has just hit paddle.
     * @return the new and updated velocity of the ball
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = this.paddle.getUpperLeft().getX(), y = this.paddle.getUpperLeft().getY();
        double x1 = collisionPoint.getX(), y1 = collisionPoint.getY();
        if (y1 > y) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // a formula to calculate the velocity of the currentVelocity
        double velocity = Math.sqrt((Math.pow(currentVelocity.getDx(), 2)) + (Math.pow(currentVelocity.getDy(), 2)));
        double distributedLength = (double) (this.width / 5);
        // the next if statement checks if the collision point is located at the 1/5 of the paddle's edge
        if (collisionPoint.getX() <= (x + (distributedLength * 1))) {
            return Velocity.fromAngleAndSpeed(300, velocity);
            // the next if statement checks if the collision point is located at the 2/5 of the paddle's edge
        } else if (collisionPoint.getX() <= (x + (distributedLength * 2))) {
            return Velocity.fromAngleAndSpeed(330, velocity);
            // the next if statement checks if the collision point is located around the middle of the paddle's edge
        } else if (collisionPoint.getX() <= (x + (distributedLength * 3))) {
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            // the next if statement checks if the collision point is located at the 4/5 of the paddle's edge
        } else if (collisionPoint.getX() <= (x + (distributedLength * 4))) {
            return Velocity.fromAngleAndSpeed(30, velocity);
            // the last possibility, the collision point is located at the most right part of the paddle's edge
        } else {
            return Velocity.fromAngleAndSpeed(60, velocity);
        }
    }

    /**
     * "addToGame" allows the paddle to invite itself to the game.
     *
     * @param g the game that will be played with the ball.
     */
    public void vipAdding(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @return a getter that returns the paddle's speed.
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * @return a getter that returns the paddle's width.
     */
    public int getWidth() {
        return this.width;
    }
}