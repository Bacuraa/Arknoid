import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description: Class "Background" will hold all the sprites which will serve as a background to the game, and
 * will be responsible to draw them into the game accordingly.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Background implements Sprite {
    // Fields
    private List<Sprite> sprites;

    /**
     * a constructor the initializes it's field without receiving any arguments.
     */
    public Background() {
        this.sprites = new ArrayList<>();
    }

    /**
     * method "add" will simply add the received sprite into the list in our field.
     * @param sprite the sprite that is going to be added to the list in the field.
     */
    public void add(Sprite sprite) {
        this.sprites.add(sprite);
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }
}
