import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * Class description: Class "SpriteCollection" will hold a collection of sprites and support them.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class SpriteCollection {
    // Fields
    private List<Sprite> collection = new ArrayList<>();

    /**
     * the paddle will get a VIP place in the list, he will be at index 0 always.
     *
     * @param s is a sprite which will be added to our collection.
     */
    public void vipAdding(Sprite s) {
        this.collection.add(0, s);
    }

    /**
     * A constructor that receives a sprite and adds it to our collection of sprites.
     *
     * @param s is a sprite which will be added to our collection.
     */
    public void addSprite(Sprite s) {
        this.collection.add(s);
    }

    /**
     * "removeSprite" removes sprite s from the sprite collection.
     *
     * @param s is a sprite which will be removed from our collection.
     */
    public void removeSprite(Sprite s) {
        this.collection.remove(s);
    }

    /**
     * @param collection1 will be the new collection in our field.
     */
    public void setList(List<Sprite> collection1) {
        this.collection = collection1;
    }

    /**
     * @return returns the collection of sprites.
     */
    public List<Sprite> getList() {
        return this.collection;
    }

    /**
     * "notifyAllTimePassed" is a method the calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : collection) {
            s.timePassed();
        }
    }

    /**
     * "drawAllOn" is a method the calls drawOn(d) on all sprites.
     *
     * @param d is the surface which all the sprites will move on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : collection) {
            s.drawOn(d);
        }
    }
}