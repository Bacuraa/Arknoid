import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class description: Class "Green3" represents the third level of the game, and holds all the information needed
 * for the game to run level 3.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Green3 implements LevelInformation {
    static final int BALL_SPEED = 8;
    static final int NUM_OF_LINES = 5;          // the number of block lines of the level
    static final int BLOCKS_FIRST_LINE = 10;    // represents the number of blocks on the first line
    static final int HIGHEST_LINE = 150;        // represents the height of the first line from above
    static final int RECTANGLE_HEIGHT = 25;
    static final int RECTANGLE_WIDTH = 50;
    // the right corner which we will start drawing our first block from
    static final int RIGHT_CORNER = GameLevel.BORDER_WIDTH - GameLevel.SIDE_BORDER_SIZE - RECTANGLE_WIDTH;
    // the width of the building in the level's background
    static final int BUILDING_WIDTH = 100;
    // the X coordinate of the building in the background
    static final int BUILDING_RECTANGLE_X = 65;
    // the Y coordinate of the building in the background
    static final int BUILDING_RECTANGLE_Y = 450;
    //the width of the small building above the main one
    static final int MIDDLE_BUILDING_WIDTH = 30;
    // represents the vertical gaps between the building windows
    static final int WINDOW_GAP = 7;
    // the width of the antenna above the building
    static final int ANTENNA_WIDTH = 9;
    // the next balls will be used as a light above the building
    static final int BIG_BALL_RADIUS = 12;
    static final int MIDDLE_BALL_RADIUS = 8;
    static final int SMALL_BALL_RADIUS = 3;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(45, BALL_SPEED));
        list.add(Velocity.fromAngleAndSpeed(315, BALL_SPEED));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        // the next color initializations will be used to draw the background sprites
        Color green = new Color(42, 130, 21);
        Color darkBlack = new Color(46, 42, 41);
        Color middleBlack = new Color(62, 58, 57);
        Color weakBlack = new Color(78, 74, 73);
        Color bigBallColor = new Color(216, 172, 102);
        Color middleBallColor = new Color(246, 77, 54);
        Color smallBallColor = Color.white;
        Rectangle rectangle = new Rectangle(new Point(0, 0), GameLevel.BORDER_WIDTH, GameLevel.BORDER_HEIGHT);
        Block screen = new Block(rectangle, green, green);
        // the next rectangle will be responsible for the whole background of this level.
        background.add(screen);
        // the initialization of the antenna above the building
        Rectangle antenna = new Rectangle(new Point(BUILDING_RECTANGLE_X + (BUILDING_WIDTH / 2) - (ANTENNA_WIDTH / 2),
                HIGHEST_LINE + 2 * RECTANGLE_HEIGHT), ANTENNA_WIDTH, GameLevel.BORDER_HEIGHT);
        background.add(new Block(antenna, weakBlack, weakBlack));
        // the initialization of the small block above the building
        Rectangle aboveBuilding = new Rectangle(new Point(BUILDING_WIDTH,
                BUILDING_RECTANGLE_Y - RECTANGLE_WIDTH), MIDDLE_BUILDING_WIDTH, GameLevel.BORDER_HEIGHT);
        background.add(new Block(aboveBuilding, middleBlack, middleBlack));
        // the initialization of the main building on the level's background
        Rectangle building = new Rectangle(new Point(BUILDING_RECTANGLE_X, BUILDING_RECTANGLE_Y),
                BUILDING_WIDTH, GameLevel.BORDER_HEIGHT);
        background.add(new Block(building, darkBlack, darkBlack));
        // the next point will serve as the center point of all the lights above the building
        Point light = new Point(BUILDING_RECTANGLE_X + ((BUILDING_WIDTH) / 2) + 1,
                HIGHEST_LINE + 2 * RECTANGLE_HEIGHT);
        // the next ball initializations will serve as light on the antenna of the building
        background.add(new Ball(light, BIG_BALL_RADIUS, bigBallColor, bigBallColor));
        background.add(new Ball(light, MIDDLE_BALL_RADIUS, middleBallColor, middleBallColor));
        background.add(new Ball(light, SMALL_BALL_RADIUS, smallBallColor, smallBallColor));
        // the next initializations will be responsible for the building windows
        int windowWidth = BUILDING_WIDTH / 11;
        int windowHeight = RECTANGLE_HEIGHT;
        int windowX = BUILDING_RECTANGLE_X + windowWidth;
        int windowY = BUILDING_RECTANGLE_Y + windowWidth;
        // the next for loop will initialize the window blocks inside the building
        for (int i = 0; i < NUM_OF_LINES; i++) {
            for (int j = 0; j < NUM_OF_LINES; j++) {
                Rectangle rec = new Rectangle(new Point(windowX, windowY), windowWidth, windowHeight);
                background.add(new Block(rec, Color.white, Color.white));
                windowY += windowHeight + WINDOW_GAP;
            }
            windowX += 2 * windowWidth;
            windowY = BUILDING_RECTANGLE_Y + windowWidth;
        }
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
        color[3] = Color.blue;
        color[4] = Color.white;
        int currentDrawHeight = HIGHEST_LINE;
        int currentDrawWidth = RIGHT_CORNER;
        // the next for loop will initialize the block sprites in the game
        for (int i = 0; i < NUM_OF_LINES; i++) {
            for (int j = BLOCKS_FIRST_LINE - i; j > 0; j--) {
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
        return 40;
    }
}
