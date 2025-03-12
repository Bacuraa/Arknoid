import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class description: Class "CountdownAnimation" is the animation that is responsible to make a countdown
 * at the start of the game.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class CountdownAnimation implements Animation {
    // constant variables
    static final int FRAMES_PER_SECOND = 1;
    static final int FONTSIZE = 50;
    // Fields
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * A contructor for class "CountdownAnimation".
     *
     * @param numOfSeconds represents the number of seconds we will have to wait before the game starts.
     * @param countFrom    represents the number we will be counting down from.
     * @param gameScreen   the screen which will be shown during the countdown.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, GameLevel.BORDER_WIDTH, GameLevel.BORDER_HEIGHT);
        Sleeper sleeper = new Sleeper();
        SpriteCollection spritesCopy = new SpriteCollection();
        spritesCopy.setList(new ArrayList<>(gameScreen.getList()));
        spritesCopy.drawAllOn(d);
        d.setColor(Color.MAGENTA);
        // drawText will draw the numbers of the countdown on the screen
        d.drawText((GameLevel.BORDER_WIDTH / 2), (GameLevel.BORDER_HEIGHT / 2), "" + this.countFrom--, FONTSIZE);
    }

    @Override
    public boolean shouldStop() {
        if (this.countFrom == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int framesPerSecond() {
        return FRAMES_PER_SECOND;
    }
}