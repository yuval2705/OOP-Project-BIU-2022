import biuoop.DrawSurface;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
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

    /**
     * Move one step.
     */
    public void  moveOneStep1() {
        if (this.velocity == null) {
            return;
        }
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy());
        CollisionInfo collisionInfo = this.environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = this.velocity.applyToPoint(this.center);
            return;
        }
        this.center = collisionInfo.collisionPoint();
        this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Move one step 1.
     */
    public void moveOneStep() {
        Velocity v = this.getVelocity();
        Line l = new Line(new Point(this.center), new Point(this.center.getX() + v.getDx(), this.center.getY()
                + v.getDy()));
        CollisionInfo c = this.environment.getClosestCollision(l);
        //
        //checks if there was a collision with an object.
        if (c != null) {
            //get the collision rectangle from the collision object.
            ICollidable c1 = c.collisionObject();
            this.velocity = c1.hit(this, c.collisionPoint(), this.velocity);
            Rectangle rect = c1.getCollisionRectangle();
            //
            //get the sides of the rectangle to check if or where the collision happened.
            Line[] sides = rect.getSides();
            //checks for a collision in every side.
            if (sides[0].isOnLine(c.collisionPoint()) && sides[1].isOnLine(c.collisionPoint())) {
                this.center = new Point(c.collisionPoint().getX() + this.r,
                        c.collisionPoint().getY() - this.r);
            } else if (sides[1].isOnLine(c.collisionPoint()) && sides[2].isOnLine(c.collisionPoint())) {
                this.center = new Point(c.collisionPoint().getX() + this.r,
                        c.collisionPoint().getY() + this.r);
            } else if (sides[2].isOnLine(c.collisionPoint()) && sides[3].isOnLine(c.collisionPoint())) {
                this.center = new Point(c.collisionPoint().getX() - this.r,
                        c.collisionPoint().getY() + this.r);
            } else if (sides[0].isOnLine(c.collisionPoint()) && sides[3].isOnLine(c.collisionPoint())) {
                this.center = new Point(c.collisionPoint().getX() - this.r,
                        c.collisionPoint().getY() - this.r);
            } else if (sides[0].isOnLine(c.collisionPoint())) {
                this.center = new Point(c.collisionPoint().getX(), c.collisionPoint().getY() - this.r);
            } else if (sides[1].isOnLine(c.collisionPoint())) {
                this.center = new Point(c.collisionPoint().getX() + this.r + this.velocity.getDx(),
                        c.collisionPoint().getY());
            } else if (sides[2].isOnLine(c.collisionPoint())) {
                this.center = new Point(c.collisionPoint().getX(), c.collisionPoint().getY() + this.r);
            } else { //the collision is on the last side.
                this.center = new Point(c.collisionPoint().getX() - this.r + this.velocity.getDx(),
                        c.collisionPoint().getY());
            }
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
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

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
