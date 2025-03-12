import java.util.List;

/**
 * Interface description: Interface "LevelInformation" holds all the information of the current level that is needed to
 * create the game level.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public interface LevelInformation {
    /**
     * @return the method returns and number of balls that will be initialized on the start of the game.
     */
    int numberOfBalls();

    /**
     * @return will hold the initial velocity of each ball in a list and return the whole list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle's speed in the current level.
     */
    int paddleSpeed();

    /**
     * @return the paddle's width in the current level.
     */
    int paddleWidth();

    /**
     * @return the name of the current name which will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return "getBackground" will return a sprite, which represents the background of the current level.
     */
    Sprite getBackground();

    /**
     * @return "blocks" is responsible the create the blocks of the current level, each block contains its
     * size, color and location.
     */
    List<Block> blocks();

    /**
     * @return "numberOfBlocksToRemove" returns the number of blocks that be removed before the level is
     * considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}