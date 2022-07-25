import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private java.util.List<ICollidable> collidables;

    /**
     * Add collidble.
     *
     * @param collidables the collidables
     */
    public GameEnvironment(ArrayList<ICollidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<ICollidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Add collidble.
     *
     * @param c the c
     */
    public void addCollidble(ICollidable c) {
        if (this.collidables == null) {
            this.collidables = new ArrayList<ICollidable>();
        }
        this.collidables.add(c);
    }

    /**
     * Draw on.
     *
     * @param drawSurface the draw surface
     */
    public void drawOn(DrawSurface drawSurface) {
        for (ICollidable i:this.collidables) {
            i.getCollisionRectangle().drawOn(drawSurface);
        }
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //create a point and CollidableAndSpriteObjects.Collidable to save the collidable and the point.
        Point closest = null;
        ICollidable c1 = null;
        //foreach Collidable of these collidable
        for (ICollidable c: this.collidables) {
            //we'll get that rectangle.
            Rectangle rect = c.getCollisionRectangle();
            //we'll get the list of intersections.
            java.util.List<Point> lst1 = rect.intersectionPoints(trajectory);
            //while that list isn't empty we'll check if its currently first point is the closer.
            while (!lst1.isEmpty()) {
                if (closest == null) {
                    /*
                     * if it isn't empty, we'll set that Collidable. Collidable as the current
                     * collidable and the point as current closest.
                     */
                    closest = new Point(lst1.remove(0));
                    c1 = c;
                } else { //wels, we'll check for the current first point from the intersection is the closest.
                    Point p = lst1.remove(0);
                    //if the current first point is closer than closest we'll update the closer.
                    if (p.distance(trajectory.getStart()) < closest.distance(trajectory.getStart())) {
                        c1 = c;
                        closest = p;
                    }
                }
            }
        }
        //if there is not any point like this we'll return null.
        if ((closest == null) && (c1 == null)) {
            return null;
        }
        //we'll return the new Collision info.
        return new CollisionInfo(closest, c1);
    }
}
