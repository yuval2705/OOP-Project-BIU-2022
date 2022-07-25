import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, ICollidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;

    private Color color;
    private int speed;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard  the keyboard
     * @param rectangle the rectangle
     * @param speed     the speed
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = this.rectangle.getColor();
        this.speed = speed;
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard  the keyboard
     * @param rectangle the rectangle
     * @param color     the color
     * @param speed     the speed
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, Color color, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
        this.rectangle.setColor(color);
        this.speed = speed;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        Point newPoint = new Point(this.rectangle.getUpperLeft().getX() - this.speed,
                this.rectangle.getUpperLeft().getY());
        if (newPoint.getX() < 25) {
            newPoint.setX(25);
        }
        this.rectangle.setUpperLeft(newPoint);
    }

    /**
     * Move right.
     */
    public void moveRight() {
        Point newPoint = new Point(this.rectangle.getUpperLeft().getX() + this.speed,
                this.rectangle.getUpperLeft().getY());
        if (newPoint.getX() + this.rectangle.getWidth() > 800 - 25) {
            newPoint.setX(800 - 25 - this.rectangle.getWidth());
        }
        this.rectangle.setUpperLeft(newPoint);
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
        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth()) {
            //this.rectangle = null;
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        //this.rectangle = null;
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Draw on.
     *
     * @param drawSurface the draw surface
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        if (this.rectangle != null) {
            this.rectangle.drawOn(drawSurface);
        }
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed("a")) {
            this.moveLeft();
        } else if (this.keyboard.isPressed("d")) {
            this.moveRight();
        }
    }

    /**
     * Add to game.
     * Adds this paddle to the game
     *
     * @param g the g
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addColidable(this);
    }
}
