import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Block.
 */
public class Block implements ICollidable, Sprite {

    private Rectangle rectangle;
    private Color color;


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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Point[] corners = this.rectangle.getPoints();
        //checks for a point if it is on the rectangle sides
        if (collisionPoint.equals(corners[0]) || collisionPoint.equals(corners[1])
                || collisionPoint.equals(corners[2]) || collisionPoint.equals(corners[3])) {
            //
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            //
        } else if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth() || collisionPoint.getX() == this.rectangle.getUpperLeft().getX()) {
            //
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            //
        }
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }
}
