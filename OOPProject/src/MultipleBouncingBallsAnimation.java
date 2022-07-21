import java.util.Random;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int width = 1500;
        int height = 920;
        GUI gui = new GUI("Multiple Bouncing Balls Animation!", width, height);
        Sleeper sleeper = new Sleeper();
        Ball[] balls = new Ball[args.length];
        //creating the random balls and putting them into an array
        for (int i = 0; i < args.length; i++) {
            //creates a random ball and putting it in the array
            balls[i]  = createRandomBall(Integer.parseInt(args[i]), width, 0, 0, height);
        }
        //doing the animation
        while (true) {
            //a function that is moving all the ball
            moveMultipleBalls(balls, width, 0,  0, height);
            DrawSurface d = gui.getDrawSurface();
            drawMultipleBalls(balls, d);
            gui.show(d);
            sleeper.sleepFor(20);
        }
    }

    /**
     * Create random ball ball.
     * Creates a random ball between the given borders
     *
     * @param radius  the radius
     * @param xRight  the right border of the box
     * @param xLeft   the left border of the box
     * @param yTop    the top border of the box
     * @param yBottom the bottom border of the box
     * @return the ball
     */
    public static Ball createRandomBall(int radius, int xRight, int xLeft, int yTop, int yBottom) {
        Random rnd = new Random();
        //gets the random starting place of the ball
        double x = rnd.nextDouble(xLeft, xRight + 1);
        double y = rnd.nextDouble(yTop, yBottom + 1);
        //creating a new random ball with the random starting point
        Ball ball = new Ball(new Point(x, y), radius * 5, new Color(rnd.nextInt(256),
                rnd.nextInt(256), rnd.nextInt(256)));
        //checking the size of the ball for his velocity
        double speed = 1;
        if (radius < 50) {
            speed = ((double) 70) / radius;
        }
        //creating the random velocity
        Velocity velocity = Velocity.fromAngleAndSpeed(rnd.nextDouble(361), speed);
        ball.setVelocity(velocity);

        return ball;
    }

    /**
     * Draw multiple balls.
     * Drawing all the balls in an array of balls on a given draw surface
     * @param balls the array of balls
     * @param d     the draw surface
     */
    public static void drawMultipleBalls(Ball[] balls, DrawSurface d) {
        for (Ball i:balls) {
            i.drawOn(d);
        }
    }

    /**
     * Move multiple balls.
     * Moves all the balls in a given array of balls
     * @param balls   the array of balls
     * @param xRight  the right border of the box
     * @param xLeft   the left border of the box
     * @param yTop    the top border of the box
     * @param yBottom the bottom border of the box
     */
    public static void moveMultipleBalls(Ball[] balls, int xRight, int xLeft, int yTop, int yBottom) {
        for (Ball i:balls) {
            i.moveOneStep(xRight, xLeft, yTop, yBottom);
        }
    }
}
