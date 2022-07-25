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
        Line[] sides = this.rectangle.getSides();
        Velocity v = new Velocity(currentVelocity);
        Line collisionLine = new Line(collisionPoint, collisionPoint);

        if (sides[0].isIntersecting(collisionLine) || sides[2].isIntersecting(collisionLine)) {
            v.setDy(-v.getDy());
        }
        if (sides[1].isIntersecting(collisionLine) || sides[3].isIntersecting(collisionLine)) {
            v.setDx(-v.getDx());
        }
        return v;
    }
}
