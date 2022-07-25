import biuoop.DrawSurface;

/**
 * The type Block.
 */
public class Block implements ICollidable {

    private Rectangle rectangle;


    /**
     * Draw on.
     *
     * @param drawSurface the draw surface
     */
    public void drawOn(DrawSurface drawSurface) {
        this.rectangle.drawOn(drawSurface);
    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Sets rectangle.
     *
     * @param rectangle the rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        currentVelocity.setDx(-currentVelocity.getDx());
        currentVelocity.setDy(-currentVelocity.getDy());
        collisionPoint.setX(collisionPoint.getX() + currentVelocity.getDx());
        collisionPoint.setY(collisionPoint.getY() + currentVelocity.getDy());
        return currentVelocity;
    }
}
