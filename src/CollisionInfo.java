/**
 * Class description: Class "CollisionInfo", this class will hold the information of the the collidable
 * object involved in the collision., and the intersection point.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class CollisionInfo {
    // Fields
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * A constructor which receives the collision point, the collidable object involved in the collision.
     * @param collisionPoint the point of the collision.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}