
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class description: Class "WideEasy" represents the 2nd level of the game, and holds all the information needed
 * for the game to run level 2.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class WideEasy implements LevelInformation {
    static final int LINE_HEIGHT = 250;        // represents the line of the blocks
    static final int SUN_CENTER = 150;         // the sun's center X and Y values
    static final int SMALL_SUN_RADIUS = 40;
    static final int MIDDLE_SUN_RADIUS = 50;
    static final int BIG_SUN_RADIUS = 60;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int angle1 = 10;
        int angle2 = 350;
        for (int i = 0; i < 5; i++) {
            list.add(Velocity.fromAngleAndSpeed(angle1, 8));
            angle1 += 10;
        }
        for (int i = 5; i < 10; i++) {
            list.add(Velocity.fromAngleAndSpeed(angle2, 8));
            angle2 -= 10;
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        Rectangle rectangle = new Rectangle(new Point(0, 0), GameLevel.BORDER_WIDTH, GameLevel.BORDER_HEIGHT);
        Block screen = new Block(rectangle, Color.white, Color.white);
        // the next rectangle will be responsible for the whole background of this level.
        background.add(screen);
        // the next color initializations represents the color of the 3 suns
        Color lightYellow = new Color(231, 231, 176);
        Color yellow = new Color(255, 255, 24);
        Color darkYellow = new Color(236, 215, 73);
        int movingline = 0;
        // the next while loop is responsible to draw lines from the sun to the line of blocks at the background
        while (movingline < 700) {
            movingline += 7;
            Line line = new Line(new Point(SUN_CENTER, SUN_CENTER),
                    new Point(movingline, LINE_HEIGHT), lightYellow, lightYellow);
            background.add(line);
        }
        // the next ball initializations are the 3 suns on the level's background
        background.add(new Ball(new Point(SUN_CENTER, SUN_CENTER), BIG_SUN_RADIUS, lightYellow, lightYellow));
        background.add(new Ball(new Point(SUN_CENTER, SUN_CENTER), MIDDLE_SUN_RADIUS, darkYellow, darkYellow));
        background.add(new Ball(new Point(SUN_CENTER, SUN_CENTER), SMALL_SUN_RADIUS, yellow, yellow));
        return background;
    }

    @Override
    public List<Block> blocks() {
        // the next two yellow colors represent the color of some block int the line
        Color yellow = new Color(255, 255, 0);
        Color darkYellow = new Color(255, 204, 0);
        int rectangleX = GameLevel.SIDE_BORDER_SIZE;
        // calculation of the block width size based on the number of blocks, and the screen borders
        int blockWidth = (GameLevel.BORDER_WIDTH - (2 * GameLevel.SIDE_BORDER_SIZE)) / this.numberOfBlocksToRemove();
        List<Block> list = new ArrayList<>();
        // the next for loop will initialize all the block one 1 line, according to its size, location and color
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            Rectangle rectangle = new Rectangle(new Point(rectangleX, LINE_HEIGHT),
                    blockWidth, GameLevel.SIDE_BORDER_SIZE);
            if (i < 2) {
                list.add(new Block(rectangle, Color.black, Color.red));
            } else if (i < 4) {
                list.add(new Block(rectangle, Color.black, yellow));
            } else if (i < 6) {
                list.add(new Block(rectangle, Color.black, darkYellow));
            } else if (i < 9) {
                list.add(new Block(rectangle, Color.black, Color.green));
            } else if (i < 11) {
                list.add(new Block(rectangle, Color.black, Color.blue));
            } else if (i < 13) {
                list.add(new Block(rectangle, Color.black, Color.pink));
            } else {
                list.add(new Block(rectangle, Color.black, Color.cyan));
            }
            rectangleX += blockWidth;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
