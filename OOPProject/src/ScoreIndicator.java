import biuoop.DrawSurface;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param drawSurface the draw surface
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.drawText(390, 20, "the score: " + this.score.getValue(), 10);
    }

    /**
     * Time passed.
     * notify the sprite that the time has passed
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add the sprite to a game.
     *
     * @param g the g
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
