import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * Class description: Class "GameEnvironment" During the game, there are going to be many objects a Ball can
 * collide with. The GameEnvironment class will be a collection of such things. The ball will know
 * the game environment, and will use it to check for collisions and direct its movement.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class GameEnvironment {
    // Fields
    private ArrayList<Collidable> collidable = new ArrayList<>();
    private ArrayList<CollisionInfo> collisions = new ArrayList<>();

    /**
     * the paddle will get a VIP place in the list, he will be at index 0 always.
     *
     * @param c is a sprite which will be added to our collection.
     */
    public void vipAdding(Collidable c) {
        this.collidable.add(0, c);
    }

    /**
     * A method which receives Collidable objects, and add them into our field.
     *
     * @param c the center point of the circle.
     */
    public void addCollidable(Collidable c) {
        this.collidable.add(c);
    }

    /**
     * A method which receives Collidable objects, and removes them from our field.
     *
     * @param c the center point of the circle.
     */
    public void removeCollidable(Collidable c) {
        this.collidable.remove(c);
    }

    /**
     * A constructor which receives collision objects, and add them into our field.
     *
     * @param collision is the collision info with one of the Collidable objects.
     */
    public void addCollisions(CollisionInfo collision) {
        this.collisions.add(collision);
    }

    /**
     * @return a function to get the collision list.
     */
    public ArrayList<CollisionInfo> getCollisions() {
        return this.collisions;
    }

    /**
     * @return returns the the current updated list.
     */
    public ArrayList<Collidable> getGame() {
        return this.collidable;
    }

    /**
     * @param surface a surface to draw an animation on.
     */
    public void drawOn(DrawSurface surface) {
        int x = 0;
    }

    /**
     * "getClosestCollision" is a method that receives a line trajectory, and checks if this line intersects with
     * one or more rectangles or not. In case it does intersect with more then 1 rectangle, it will return
     * the rectangle's information, that has the closest intersection point to the start point of "trajectory"
     *
     * @param trajectory we will check intersections between this line, and our list of rectangles.
     * @return null - if there are no intersections at all.
     * Collision info - about the rectangle that has the closest intersection point to the start point of "trajectory"
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // minDistance will check which rectangle will have the lowest distance to the "trajectory" start point
        double minDistance = Integer.MAX_VALUE;
        // CollisionInfo will save the collision info of a rectangle with the lowest distance to trajectory.start()
        CollisionInfo closestRect = null;
        // a list that stores all the rectangles that are intersecting with trajectory
        ArrayList<CollisionInfo> collisionInfo = new ArrayList<>();
        // the next for loop will store in a list all the rectangle's info that intersect with "trajectory"
        for (Collidable value : collidable) {
            if (value.getCollisionRectangle().intersectionPoints(trajectory).size() == 0) {
                continue;
            }
            Point temp = value.getCollisionRectangle().intersectionPoints(trajectory).get(0);
            CollisionInfo info = new CollisionInfo(temp, value);
            collisionInfo.add(info);

        }
        // if our list is empty, there are no intersection with any rectangle, return null.
        if (collisionInfo.size() == 0) {
            return null;
        }
        // the next for loop will find the closest rectangle(it's intersection point) to trajectory.start()
        for (CollisionInfo info : collisionInfo) {
            if (info.collisionPoint().distance(trajectory.start()) < minDistance) {
                minDistance = info.collisionPoint().distance(trajectory.start());
                closestRect = info;
            }
        }
        for (CollisionInfo info : collisionInfo) {
            if (info.collisionPoint().distance(trajectory.start()) == minDistance) {
                this.collisions.add(info);
            }
        }
        return closestRect;
    }
}