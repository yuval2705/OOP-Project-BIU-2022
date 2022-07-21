import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The type Abstract art drawing.
 */
public class AbstractArtDrawing {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        drawRandomLines();
    }

    /**
     * Draw random lines.
     */
    public static void drawRandomLines() {
        int numberOfLines = 10;
        Line[] lines = new Line[numberOfLines];
        GUI gui = new GUI("Random lines example!", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < numberOfLines; i++) {
            lines[i] = generateRandomLine();
            drawLine(d, lines[i]);
        }
        for (Line i:lines) {
            for (Line j:lines) {
                if (!i.equals(j)) {
                    Point intersection = i.intersectionWith(j);
                    if (intersection != null) {
                        d.setColor(Color.RED);
                        d.fillCircle((int) intersection.getX(), (int) intersection.getY(), 3);
                    }
                }
            }
        }
        gui.show(d);
    }

    /**
     * Draw random lines.
     *
     * @param d    the d
     * @param line the line
     */
    public static void drawLine(DrawSurface d, Line line) {
        d.setColor(Color.BLACK);
        d.drawLine((int) (line.getStart().getX()), (int) (line.getStart().getY()),
                (int) (line.getEnd().getX()), (int) (line.getEnd().getY()));
        Point middle = line.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
    }

    /**
     * Generate random line line.
     *
     * @return the line
     */
    public static Line generateRandomLine() {
        Random rnd = new Random();

        int x1 = rnd.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rnd.nextInt(300) + 1; // get integer in range 1-300

        int x2 = rnd.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rnd.nextInt(300) + 1; // get integer in range 1-300
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        return new Line(p1, p2);
    }
}
