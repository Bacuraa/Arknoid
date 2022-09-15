import biuoop.DrawSurface;

/**
 * Interface description: Interface "Animation" will be responsible to handle all the different animations in our game.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public interface Animation {
    /**
     * method "doOneFrame" will run a single frame of the game every time it will be called.
     *
     * @param d represents the surface we are drawing on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return boolean value which determines if the animation which is currently running will be stopped or not.
     */
    boolean shouldStop();

    /**
     * @return int value which represents the frames per second of the running animation.
     */
    int framesPerSecond();
}