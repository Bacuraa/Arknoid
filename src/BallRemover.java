/**
 * Class description: Class "BallsRemover" will be in charge of removing balls, and updating an availabe-balls counter.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class BallRemover implements HitListener {
    //Fields
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * A constructor that receives object GameLevel and object Counter as parameters.
     * @param gameLevel the current gameLevel which is played.
     * @param remainingBalls the remaining balls in the gameLevel.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }
}
