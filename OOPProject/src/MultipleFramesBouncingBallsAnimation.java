import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
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
        Ball[] balls1 = new Ball[args.length / 2];
        Ball[] balls2 = new Ball[args.length - args.length / 2];
        //getting the two borders of the balls
        int xR1, xL1, yT1, yB1, xR2, xL2, yT2, yB2;
        xR1 = 500;
        xL1 = 50;
        xL2 = 450;
        xR2 = 600;
        yB1 = 500;
        yT1 = 50;
        yT2 = 450;
        yB2 = 600;
        //creating the random balls and adding them to the array of balls
        for (int i = 0; i < args.length; i++) {
            //checks if the current ball is in the first half of the balls
            if (i < balls1.length) {
                //creating the random ball
                balls1[i]  = MultipleBouncingBallsAnimation.createRandomBall(Integer.parseInt(args[i]),
                        xR1, xL1, yT1, yB1);
            } else {
                //creating the random ball
                balls2[i - balls1.length]  = MultipleBouncingBallsAnimation.createRandomBall(Integer.parseInt(args[i]),
                        xR2, xL2, yT2, yB2);
            }

        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //drawing the two rectangles
            d.setColor(Color.GRAY);
            d.fillRectangle(xL1, yT1, xR1 - xL1, yB1 - yT1);
            d.setColor(Color.YELLOW);
            d.fillRectangle(xL2, yT2, xR2 - xL2, yB2 - yT2);
            //moving all the balls according to their borders
            MultipleBouncingBallsAnimation.moveMultipleBalls(balls1, xR1, xL1, yT1, yB1);
            MultipleBouncingBallsAnimation.moveMultipleBalls(balls2, xR2, xL2, yT2, yB2);
            //drawing the balls
            MultipleBouncingBallsAnimation.drawMultipleBalls(balls1, d);
            MultipleBouncingBallsAnimation.drawMultipleBalls(balls2, d);
            gui.show(d);
            sleeper.sleepFor(20);
        }
    }
}
