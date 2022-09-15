import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class description: Class "PauseScreen" is responsible for every screen that pauses the game for any reason.
 * In other words, this class is responsible to stop the game.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class PauseScreen implements Animation {
    // Fields
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;

    /**
     * A constructor that receives GUI's keyboardSensor and a string.
     *
     * @param k   represents the GUI's keyboardSensor the player plays with.
     * @param key represents the message the player will get at the stopped screen.
     */
    public PauseScreen(KeyboardSensor k, String key) {
        this.keyboard = k;
        this.stop = false;
        this.key = key;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(100, d.getHeight() / 2, this.key, 40);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public int framesPerSecond() {
        return GameLevel.FRAMES_PER_SECOND;
    }
}