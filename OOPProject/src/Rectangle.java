import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point point;
    private double width;
    private double height;

    /**
     * Draw on.
     *
     * @param drawSurface the draw surface
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.fillRectangle((int) this.point.getX(), (int) this.point.getY(),
                (int) this.width, (int) this.height);
    }

    /**
     * Sets point.
     *
     * @param point the point
     */
    public void setUpperLeft(Point point) {
        this.point = point;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets point.
     *
     * @return the point
     */
    public Point getUpperLeft() {
        return point;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param point  the point
     * @param width  the width
     * @param height the height
     */
    public Rectangle(Point point, double width, double height) {
        this.point = point;
        this.height = height;
        this.width = width;
    }

    /**
     * Gets point.
     *
     * @return the point
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<Point>();
        //
        //gets all the border of the rectangle

        Line left = new Line(this.point, new Point(this.point.getX(), this.point.getY() + this.getHeight()));
        //
        Line right = new Line(new Point(this.point.getX() + this.width, this.point.getY()),
                new Point(this.point.getX() + this.width, this.point.getY() + this.getHeight()));
        //
        Line top = new Line(this.point, new Point(this.point.getX() + this.width, this.point.getY()));
        //
        Line bottom = new Line(new Point(this.point.getX(), this.point.getY() + this.getHeight()),
                new Point(this.point.getX() + this.width, this.point.getY() + this.getHeight()));

        //creating an array that contains all the border of the rectangle
        Line[] borders = new Line[]{left, right, top, bottom};

        //goes through all the borders of the rectangle and checks if there is an intersection
        for (Line l:borders) {
            Point intersectionPoint = line.intersectionWith(l);
            //checks for every side of the rectangle if the line is intersection with it
            if (intersectionPoint != null) {
                //adding the intersection point to the list
                pointsList.add(intersectionPoint);
            }
        }
        return pointsList;
    }

    /**
     * Get sides line [ ].
     *
     * @return the line [ ]
     */
    public Line[] getSides() {
        Line[] sides = new Line[4];

        Point p1 = new Point(this.getUpperLeft());
        Point p2 = new Point(this.getUpperLeft().getX() + this.width, this.getUpperLeft().getY());
        Point p3 = new Point(this.getUpperLeft().getX() + this.width, this.getUpperLeft().getY() + this.height);
        Point p4 = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.height);
        //
        sides[0] = new Line(p1, p2);
        sides[1] = new Line(p2, p3);
        sides[2] = new Line(p3, p4);
        sides[3] = new Line(p4, p1);
        return sides;
    }

}
