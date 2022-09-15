import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Class description: Class "DirectHit" represents the first level of the game, and holds all the information needed
 * for the game to run level 1.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class DirectHit implements LevelInformation {
    // constant variables
    static final int BALL_SPEED = 5;
    static final int BALL_ANGLE = 0;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        Color blue = Color.BLUE;
        Color black = Color.BLACK;
        // the next rectangle will be responsible for the whole background of this level.
        Rectangle rectangle = new Rectangle(new Point(0, 0), GameLevel.BORDER_WIDTH, GameLevel.BORDER_HEIGHT);
        Block screen = new Block(rectangle, black, black);
        background.add(screen);
        // initializing all the balls and lines that will serve as the background of the game
        background.add(new Ball(new Point(400, 155), 120, blue, black));
        background.add(new Ball(new Point(400, 155), 85, blue, black));
        background.add(new Ball(new Point(400, 155), 50, blue, black));
        background.add(new Line(new Point(400, 180), new Point(400, 300), blue, blue));
        background.add(new Line(new Point(400, 130), new Point(400, 0), blue, blue));
        background.add(new Line(new Point(375, 155), new Point(255, 155), blue, blue));
        background.add(new Line(new Point(425, 155), new Point(540, 155), blue, blue));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Rectangle rectangle = new Rectangle(new Point(385, 140), 30, 30);
        list.add(new Block(rectangle, Color.RED, Color.RED));
        return list;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
