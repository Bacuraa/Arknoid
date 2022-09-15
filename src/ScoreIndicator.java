import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class description: Class "Scoreindicator" will be in charge of displaying the current score of the game.
 * This class will will hold a reference to the scores counter, and will be added to the game as a sprite positioned
 * at the top of the screen.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class ScoreIndicator implements Sprite {
    // the font size of the letters
    static final int WIDTH_DEVIATION = 60;     // the deviation from the rectangle's width middle
    static final int HEIGHT_DEVIATION = 6;     // the deviation from the rectangle's height middle
    static final int FONTSIZE = 15;
    // Fields
    private Rectangle rectangle;
    private Counter score;
    private Counter lives;
    private String levelName;

    /**
     * A constructor that receives a rectangle and the current score of the game.
     *
     * @param score     the current score which will be drawn on the game.
     * @param rectangle the score will be drawn inside the rectangle.
     * @param levelName represents the level of the current stage.
     * @param lives     lives remaining to the player.
     */
    public ScoreIndicator(Rectangle rectangle, Counter score, Counter lives, String levelName) {
        this.rectangle = rectangle;
        this.score = score;
        this.lives = lives;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Color lightGray = new Color(204, 204, 204);
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        d.setColor(lightGray);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        // (width * 3 / 8) determines the place where the score will be written
        d.drawText(width * 3 / 8, height / 2 + HEIGHT_DEVIATION,
                "Score: " + this.score.getValue(), FONTSIZE);
        // (width / 8) determines the place where the lives will be written
        d.drawText(width / 8, height / 2 + HEIGHT_DEVIATION,
                "Lives: " + this.lives.getValue(), FONTSIZE);
        // (width * 5 / 8) determines the place where the level name will be written
        d.drawText(width * 5 / 8, height / 2 + HEIGHT_DEVIATION,
                "Level Name: " + this.levelName, FONTSIZE);
    }

    @Override
    public void timePassed() {
        int x = 0;
    }
}
