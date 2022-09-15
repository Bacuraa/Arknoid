/**
 * Class description: Class "ScoreTrackingListener" will be in charge of updating counter when blocks are being
 * hit and removed.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class ScoreTrackingListener implements HitListener {
    // the point of every block hit
    static final int SCORE_PER_BLOCK = 5;
    // Fields
    private Counter currentScore;

    /**
     * A constructor that updates the current score of the game.
     *
     * @param scoreCounter the current score of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SCORE_PER_BLOCK);
    }
}