import biuoop.DrawSurface;
/**
 * Abstract Class description: Abstract Class "ScreenDecorator" is responsible to implement the Decorator design
 * pattern, and helps to enhance the regular stopped animation and give it the ability to end itself by pressing
 * a keyboard button.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public abstract class ScreenDecorator implements Animation {
    // Fields
    private Animation tempScreen;

    /**
     * @param tempScreen is the animation which our decorator intends to enhance.
     */
    public ScreenDecorator(Animation tempScreen) {
        this.tempScreen = tempScreen;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.tempScreen.doOneFrame(d);
    }
    @Override
    public int framesPerSecond() {
        return this.tempScreen.framesPerSecond();
    }
}
