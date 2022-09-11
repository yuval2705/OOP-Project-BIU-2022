import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     * draw the sprite to the screen
     *
     * @param drawSurface the draw surface
     */
    void drawOn(DrawSurface drawSurface);

    /**
     * Time passed.
     * notify the sprite that the time has passed
     */
    void timePassed();

    /**
     * Add the sprite to a game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}
