import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class GameOver implements Animation {
    private Counter score;

    /**
     * Instantiates a new Pause screen.
     *
     * @param score the score
     */
    public GameOver(Counter score) {
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        String message = "you died! --- your Score is: " + this.score.getValue() + "\npress space to Quit";
        //
        d.drawText(10, d.getHeight() / 2, message, 32);
    }
    @Override
    public boolean shouldStop() {
        return true;
    }
}
