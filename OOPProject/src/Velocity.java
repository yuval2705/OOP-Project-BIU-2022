/**
 * The type Velocity.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets dx.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets dy.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     * Gets and angle and speed and returning a new velocity object according to the angle and speed
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //doing simple trigonometry
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Apply to point point.
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        return new Point((p.getX() + this.dx), (p.getY() + this.dy));
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "dx: " + this.dx + " dy: " + this.dy;
    }
}
