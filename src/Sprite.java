import biuoop.DrawSurface;

/**
 * interface description: interface "Sprite" will be responsible for all for all the moving objects that can
 * be drawn to the screen. Sprites can be drawn on the screen, and can be notified that time has passed.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public interface Sprite {
    /**
     * "drawOn" is a method to draw a sprite to the screen.
     * @param d is the surface that we will draw the animation on.
     */
    void drawOn(DrawSurface d);

    /**
     * "timePassed" is a method that notifies the time passed ant acts to move the object to the next step.
     */
    void timePassed();
}