/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter counter;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel    the game
     * @param counter the counter
     */
    public BallRemover(GameLevel gameLevel, Counter counter) {
        this.gameLevel = gameLevel;
        this.counter = counter;
    }
    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.counter.decrease(1);
        hitter.removeFromGame(this.gameLevel);
    }
}
