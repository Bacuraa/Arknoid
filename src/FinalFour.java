import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Class description: Class "FinalFour" represents the 4th level of the game, and holds all the information needed
 * for the game to run level 4.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class FinalFour implements LevelInformation {
    static final int BLOCKS_PER_LINE = 15;
    static final int BALL_SPEED = 8;
    static final int HIGHEST_LINE = 100;          // the highest line of blocks
    static final int RECTANGLE_HEIGHT = 25;
    static final int RECTANGLE_WIDTH = 50;
    // the number of block lines in the game
    static final int NUM_OF_LINES = 7;
    // the right corner which we will start drawing our first block from
    static final int RIGHT_CORNER = GameLevel.BORDER_WIDTH - GameLevel.SIDE_BORDER_SIZE - RECTANGLE_WIDTH;
    // the space (on the X axis) between the lines in the clouds
    static final int LINE_SPACE = 10;
    // the number of lines that are getting out of the clouds in the background
    static final int LINES_FROM_CLOUDS = 10;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(0, BALL_SPEED));
        list.add(Velocity.fromAngleAndSpeed(315, BALL_SPEED));
        list.add(Velocity.fromAngleAndSpeed(45, BALL_SPEED));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        Rectangle rectangle = new Rectangle(new Point(0, 0), GameLevel.BORDER_WIDTH, GameLevel.BORDER_HEIGHT);
        Color blue = new Color(23, 136, 208);
        Block screen = new Block(rectangle, blue, blue);
        // the next rectangle will be responsible for the whole background of this level.
        background.add(screen);
        Color white = Color.white;
        int space1 = 100 + LINE_SPACE;
        int space2 = 600 + LINE_SPACE;
        // the next for loop is responsible to draw the lines from the left cloud at the background
        for (int i = 0; i < LINES_FROM_CLOUDS; i++) {
            background.add(new Line(new Point(space1, 400),
                    new Point(space1 - (3 * LINE_SPACE), 600), white, white));
            space1 += LINE_SPACE;
        }
        // the next for loop is responsible to draw the lines from the right cloud at the background
        for (int i = 0; i < LINES_FROM_CLOUDS; i++) {
            background.add(new Line(new Point(space2, 510),
                    new Point(space2 - (3 * LINE_SPACE), 600), white, white));
            space2 += LINE_SPACE;
        }
        // the next initialization will create the clouds in this level's background
        Color lightWhite = new Color(204, 204, 204);
        Color middleWhite = new Color(187, 187, 187);
        Color darkWhite = new Color(170, 170, 170);
        background.add(new Ball(new Point(103, 400), 23, lightWhite, lightWhite));
        background.add(new Ball(new Point(122, 422), 26, lightWhite, lightWhite));
        background.add(new Ball(new Point(140, 390), 28, middleWhite, middleWhite));
        background.add(new Ball(new Point(160, 422), 22, darkWhite, darkWhite));
        background.add(new Ball(new Point(180, 400), 32, darkWhite, darkWhite));
        background.add(new Ball(new Point(600, 500), 23, lightWhite, lightWhite));
        background.add(new Ball(new Point(620, 535), 28, lightWhite, lightWhite));
        background.add(new Ball(new Point(640, 510), 30, middleWhite, middleWhite));
        background.add(new Ball(new Point(660, 525), 23, darkWhite, darkWhite));
        background.add(new Ball(new Point(680, 515), 30, darkWhite, darkWhite));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        // a list of colors for each block line
        Color[] color = new Color[NUM_OF_LINES];
        color[0] = Color.gray;
        color[1] = Color.red;
        color[2] = Color.yellow;
        color[3] = Color.green;
        color[4] = Color.white;
        color[5] = Color.pink;
        color[6] = Color.cyan;
        int currentDrawHeight = HIGHEST_LINE;
        int currentDrawWidth = RIGHT_CORNER;
        // the next for loop will initialize the block sprites in the game
        for (int i = 0; i < NUM_OF_LINES; i++) {
            for (int j = BLOCKS_PER_LINE; j > 0; j--) {
                Rectangle rec = new Rectangle(new Point(currentDrawWidth, currentDrawHeight),
                        RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
                list.add(new Block(rec, Color.black, color[i]));
                currentDrawWidth -= RECTANGLE_WIDTH;
            }
            currentDrawHeight += RECTANGLE_HEIGHT;
            currentDrawWidth = RIGHT_CORNER;
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_LINES * BLOCKS_PER_LINE;
    }
}
