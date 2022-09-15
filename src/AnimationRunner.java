import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * Class description: Class "AnimationRunner" will be responsible to run the animations of the game. the animation
 * will be stopped if the player loses his lives, loses all the balls or managed to hit all the blocks in the game.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class AnimationRunner {
    // constant variable
    static final int FRAMES_PER_SECOND = 60;
    // Fields
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /**
     * a constructor the initializes it's field without any need from arguments.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arknoid", GameLevel.BORDER_WIDTH, GameLevel.BORDER_HEIGHT);
        this.sleeper = new Sleeper();
        this.framesPerSecond = FRAMES_PER_SECOND;
    }

    /**
     * method "run" will run the animation that is given inside a while loop.
     * @param animation is the animation which will be run right now by this method
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / animation.framesPerSecond();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     *
     * @return the method returns the current gui of the class.
     */
    public GUI getGui() {
        return this.gui;
    }
}
