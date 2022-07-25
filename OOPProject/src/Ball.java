import biuoop.DrawSurface;

/**
 * The type Ball.
 */
public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;

    private GameEnvironment environment;

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx of the new velocity
     * @param dy the dy of the new velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    public Line traj
    /**
     * Move one step.
     *
     * @param collidable the collidable
     */
    public void  moveOneStep(ICollidable collidable) {
        Rectangle rect = collidable.getCollisionRectangle();
        double xRight = rect.getPoint().getX();
        double xLeft = rect.getPoint().getX() + rect.getWidth();
        , int yTop, int yBottom

    }

    /**
     * Move one step.
     * Moves the ball according to his velocity
     *
     * @param xRight  the right border of the box
     * @param xLeft   the left border of the box
     * @param yTop    the top border of the box
     * @param yBottom the bottom border of the box
     */
    public void moveOneStep(int xRight, int xLeft, int yTop, int yBottom) {
        boolean degel = true;
        //gets the supposed position of the ball
        Point point = this.getVelocity().applyToPoint(this.center);
        //checks if the ball has passed the right border
        if (point.getX() + this.r >= xRight) {
            //changes the velocity according to the hitting point
            this.center.setX(xRight - this.r);
            this.velocity.setDx(-this.velocity.getDx());
            degel = false;
        }
        //checks if the ball has passed the left border
        if (point.getX() - this.r <= xLeft) {
            //changes the velocity according to the hitting point
            this.center.setX(xLeft + this.r);
            this.velocity.setDx(-this.velocity.getDx());
            degel = false;
        }
        //checks if the ball has passed the top border
        if (point.getY() - this.r <= yTop) {
            //changes the velocity according to the hitting point
            this.center.setY(yTop + this.r);
            this.velocity.setDy(-this.velocity.getDy());
            degel = false;
        }
        //checks if the ball has passed the bottom border
        if (point.getY() + this.r >= yBottom) {
            //changes the velocity according to the hitting point
            this.center.setY(yBottom - this.r);
            this.velocity.setDy(-this.velocity.getDy());
            degel = false;
        }
        //checks if a new step should be taken
        if (degel) {
            moveOneStep();
        }
    }

    /**
     * Move one step.
     * sets the new center of the ball according to his velocity
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public int getRadius() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     * Draw the ball on the given surface
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }
}
