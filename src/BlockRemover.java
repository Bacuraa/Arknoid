/**
 * Class description: Class "BlockRemover" a BlockRemover is in charge of removing blocks from the gameLevel, as well
 * as of the number of blocks that remain.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class BlockRemover implements HitListener {
    // Fields
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * A constructor that receives object GameLevel and object Counter as parameters.
     * @param gameLevel the current gameLevel which is played.
     * @param remainingBlocks the remaining blocks in the gameLevel.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeCollidable(beingHit);
        this.gameLevel.removeSprite(beingHit);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}