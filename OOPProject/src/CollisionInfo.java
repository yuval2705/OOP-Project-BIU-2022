/**
 * The type Collision info.
 */
public class CollisionInfo {
    /**
     * Collision point point.
     *
     * @return the point
     */
    private Point collisionPoint;
    private ICollidable collisionObject;

    /**
     * Constructor for MovingAndMechanics.CollisionInfo.
     *
     * @param p the collision point.
     * @param c the collidable to add.
     */
    public CollisionInfo(Point p, ICollidable c) {
        this.collisionPoint = new Point(p);
        this.collisionObject = c;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
    public ICollidable collisionObject() {
        return this.collisionObject;
    }
}
