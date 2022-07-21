import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The type Bouncing ball animation.
 */
public class BouncingBallAnimation {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        double x, y, z, w;
        x = Double.parseDouble(args[0]);
        y = Double.parseDouble(args[1]);
        z = Double.parseDouble(args[2]);
        w = Double.parseDouble(args[3]);
        drawAnimation(new Point(x, y), z, w);
    }

    /**
     * Draw animation.
     *
     * @param start the start
     * @param dx    the dx
     * @param dy    the dy
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(new Point(start.getX(), start.getY()), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
