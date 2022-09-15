import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class description: Class "KeyPressStoppableAnimation" enhances the regular pause screen and gives it the ability
 * to stop the animation by pressing a keyboard key.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class KeyPressStoppableAnimation extends ScreenDecorator {
    // Fields
    private KeyboardSensor sensor;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * A regular constructor with 2 arguments and 2 fields it initializes by itself.
     *
     * @param sensor    is the keyboardSensor that the player plays with.
     * @param animation the animation that "KeyPressStoppableAnimation" is going to enhance.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, Animation animation) {
        super(animation);
        this.animation = animation;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // we will run the father's "doOneFrame" method first, and the add our desired code into it
        super.doOneFrame(d);
        // if the space key is already pressed, we wont stop the current animation
        if (!(this.sensor.isPressed(KeyboardSensor.SPACE_KEY))) {
            this.isAlreadyPressed = false;
        }
        // if the space key has not been pressed, then we will stop the current pause animation
        if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY) && !this.isAlreadyPressed) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
