import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements ICollidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private Color color;
    private java.util.ArrayList<HitListener> hitListeners;

    /**
     * Draw on.
     *
     * @param drawSurface the draw surface
     */
    public void drawOn(DrawSurface drawSurface) {
        if (rectangle != null) {
            drawSurface.setColor(this.color);
            this.rectangle.drawOn(drawSurface);
        }
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    @Override
    public void addToGame(Game g) {
        g.addColidable(this);
        g.addSprite(this);
    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = rectangle.getColor();
    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        rectangle.setColor(color);
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Sets rectangle.
     *
     * @param rectangle the rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point[] corners = this.rectangle.getPoints();
        Velocity v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        //checks for a point if it is on the rectangle sides
        if (collisionPoint.equals(corners[0]) || collisionPoint.equals(corners[1])
                || collisionPoint.equals(corners[2]) || collisionPoint.equals(corners[3])) {
            //
            v = new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            //
        } else if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth() || collisionPoint.getX() == this.rectangle.getUpperLeft().getX()) {
            //
            v =  new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            //
        }
        this.notifyHit(hitter);
        return v;
    }

    /**
     * Remove this(Block) from a game.
     *
     * @param game the game
     */
    public void removeFromGame(Game game) {
         game.removeCollidable(this);
         game.removeSprite(this);
    }

    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }
}
