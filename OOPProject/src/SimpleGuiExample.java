import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Simple gui example.
 */
public class SimpleGuiExample {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameEnvironment environment = new GameEnvironment(new ArrayList<ICollidable>());

        int width = 1500;
        int height = 920;

        GUI gui = new GUI("Balls Animation!", width, height);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(100, 100, 4, Color.BLACK);
        ball.setVelocity(4, 3);
        ball.setGameEnvironment(environment);

        //getting the two borders of the balls
        Rectangle rect = new Rectangle(new Point(200, 0), 10, 200);
        Block b = new Block(rect);
        environment.addCollidble(b);
        environment.addCollidble(new Block(new Rectangle(new Point(0, 0), 10, 200)));
        environment.addCollidble(new Block(new Rectangle(new Point(0, 0), 200, 10)));
        environment.addCollidble(new Block(new Rectangle(new Point(0, 200), 200, 10)));
        //
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //drawing the two rectangles
            ball.moveOneStep();
            environment.drawOn(d);
            ball.drawOn(d);

            gui.show(d);
            sleeper.sleepFor(50);
        }


    }
}
