/**
 * Interface description: Interface "HitNotifier" indicate that objects that implement it send notifications
 * when they are being hit.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public interface HitNotifier {
    /**
     * @param hl will be the listener that will be added to the events.
     */
    void addHitListener(HitListener hl);
    /**
     * @param hl will be the listener that will be removed from the events.
     */
    void removeHitListener(HitListener hl);
}
