/**
 * The type Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Gets end.
     *
     * @return the end
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Length double.
     *
     * @return the double
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     *
     * @return the point
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        double dinominator = (this.start.getX() - this.end.getX()) * (other.getStart().getY() - other.getEnd().getY())
                - (this.start.getY() - this.end.getY()) * (other.getStart().getX() - other.getEnd().getX());
        if (dinominator == 0) {
            return false;
        }

        double t = (this.start.getX() - other.getStart().getX()) * (other.getStart().getY() - other.getEnd().getY())
                - (this.start.getY() - other.getStart().getY()) * (other.getStart().getX() - other.getEnd().getX());
        t = t / dinominator;
        if (t > 1 || t < 0) {
            return false;
        }

        double u = (this.start.getX() - other.getStart().getX()) * (this.start.getY() - this.end.getY())
                - (this.start.getY() - other.getStart().getY()) * (this.start.getX() - this.end.getX());
        u = u / dinominator;
        return !(u > 1) && !(u < 0);
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        double dinominator = (this.start.getX() - this.end.getX()) * (other.getStart().getY() - other.getEnd().getY())
                - (this.start.getY() - this.end.getY()) * (other.getStart().getX() - other.getEnd().getX());

        double t = (this.start.getX() - other.getStart().getX()) * (other.getStart().getY() - other.getEnd().getY())
                - (this.start.getY() - other.getStart().getY()) * (other.getStart().getX() - other.getEnd().getX());
        t = t / dinominator;

        double u = (this.start.getX() - other.getStart().getX()) * (this.start.getY() - this.end.getY())
                - (this.start.getY() - other.getStart().getY()) * (this.start.getX() - this.end.getX());
        u = u / dinominator;

        double newPointX = this.start.getX() + t * (this.end.getX() - this.start.getX());
        double newPointY = this.start.getY() + t * (this.end.getY() - this.start.getY());
        return new Point(newPointX, newPointY);
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        if (!this.start.equals(other.getStart()) || !this.start.equals(other.getEnd())) {
            return false;
        }
        if (!this.end.equals(other.getStart()) || !this.end.equals(other.getEnd())) {
            return false;
        }
        return true;
    }
}

