/**
 * Interface description: Objects that want to be notified of hit events, will implement the HitListener interface,
 * and will be responsible for the execution of the events.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public interface HitListener {
    /**
     *
     * @param beingHit This method is called whenever the beingHit object is hit.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
