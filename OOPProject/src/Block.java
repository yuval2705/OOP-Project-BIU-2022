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
        if (rectangle != null) {
            this.rectangle.drawOn(drawSurface);
        }
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
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth()) {
            //this.rectangle = null;
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        //this.rectangle = null;
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }
}
