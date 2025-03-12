import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description: Class "Block" represents an object of type rectangle with different colors and sizes, and
 * changes the velocity of the object that just collided with the current rectangle accordingly.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // Fields
    private Rectangle rect;
    private java.awt.Color outerColor;
    private java.awt.Color innerColor;
    private List<HitListener> hitListeners;

    /**
     * A constructor which receives a certain rectangle and it's color.
     *
     * @param rec        represents a rectangle which is collidable.
     * @param outerColor the outer color of the ball.
     * @param innerColor the inner color of the ball.
     */
    public Block(Rectangle rec, java.awt.Color outerColor, java.awt.Color innerColor) {
        this.rect = rec;
        this.innerColor = innerColor;
        this.outerColor = outerColor;
    }

    /**
     * method "notifyHit" notifies all of the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter is the ball that has just hit the block.
     */
    private void notifyHit(Ball hitter) {
        // we will Make a copy of the hitListeners before iterating over them.
        if (this.hitListeners == null) {
            return;
        }
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // now we will notify all listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @return "getCollisionRectangle" returns the current collidable rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param hitListeners1 is a new listener which will be added to the new current list of listeners.
     */
    public void setListener(List<HitListener> hitListeners1) {
        this.hitListeners = hitListeners1;
    }

    /**
     * @param surface a surface to draw an animation on.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        surface.setColor(this.innerColor);
        surface.fillRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(this.outerColor);
        surface.drawRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Method to the object block to be able to add itself to the game.
     *
     * @param g the game that will be played with the ball.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * "timePassed" is a method that notifies the time passed and acts to move the object to the next step.
     */
    public void timePassed() {
        int x = 0;
    }

    /**
     * Method "hit" changes the velocity of the ball that has just been collided with the current rectangle.
     *
     * @param collisionPoint  the point of collision with the current rectangle.
     * @param currentVelocity the current velocity of the ball that collided with the current rectangle.
     * @param hitter          the ball that has just hit the current block.
     * @return the method returns the new velocity of the ball after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity newVelocity = new Velocity(dx, dy);
        // we will make a Line object out of a single point, to be able to use isIntersecting method from Line
        double x1 = collisionPoint.getX(), y1 = collisionPoint.getY();
        double x2 = this.rect.getUpperLeft().getX();
        double y2 = this.rect.getUpperLeft().getY();
        // we will now change the velocity according to collisionPoint's position, and the rectangle's position
        if (x1 >= x2 && x1 <= x2 + rect.getWidth() && Point.equals(y1, y2)) {
            newVelocity.setDy(dy * (-1));
        }
        if (x1 >= x2 && x1 <= x2 + rect.getWidth() && Point.equals(y1, y2 + rect.getHeight())) {
            newVelocity.setDy(dy * (-1));
        }
        if (y1 >= y2 && y1 <= y2 + rect.getHeight() && Point.equals(x1, x2)) {
            newVelocity.setDx(dx * (-1));
        }
        if (y1 >= y2 && y1 <= y2 + rect.getHeight() && Point.equals(x1, x2 + rect.getWidth())) {
            newVelocity.setDx((dx * (-1)));
        }
        this.notifyHit(hitter);
        return newVelocity;
    }
}
