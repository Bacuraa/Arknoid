/**
 * Interface description: The Collidable interface will be used by things that can be collided with.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public interface Collidable {
    /**
     * @return a method that returns the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * The method "hit" notifies the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  is the collision Point with the object.
     * @param currentVelocity is ghe velocity of the object.
     * @param hitter          is the ball that just hit the collidable object.
     * @return The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}