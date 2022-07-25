import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private java.util.ArrayList<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     *
     * @param sprites the sprites
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        if (sprites == null) {
            this.sprites = new ArrayList<Sprite>();
        }
        this.sprites.add(s);
    }

    /**
     * Notify all time passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite s:this.sprites) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param drawSurface the draw surface
     */
    public void drawAllOn(DrawSurface drawSurface) {
        for (Sprite s:this.sprites) {
            s.drawOn(drawSurface);
        }
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public java.util.List<Sprite> getSprites() {
        return this.sprites;
    }

}
