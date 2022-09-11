import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Count down animation.
 */
public class CountDownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int currentCounterValue;
    private int framesPassedThatPart;
    private SpriteCollection gameScreen;

    /**
     * Constructor.
     *
     * @param numOfSeconds the number of seconds the animation will work.
     * @param countFrom    the point to start count from.
     * @param gameScreen   the list of sprites.
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.currentCounterValue = countFrom;
        this.framesPassedThatPart = 0;
        this.gameScreen = gameScreen;
    }
    @Override
    public boolean shouldStop() {
        return this.currentCounterValue == -1;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        String msg;
        if (currentCounterValue > 0) {
            msg = "Starting in: " + currentCounterValue;
        } else {
            msg = "GO!";
        }
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, msg, 32);
        //calc the num of frames we reached until now and update it.
        double framesToReach = GameLevel.FPS * numOfSeconds;
        //checking if the animation is happening
        if (((int) ((framesToReach) / countFrom)) == framesPassedThatPart) {
            this.currentCounterValue--;
            this.framesPassedThatPart = 1;
        } else {
            this.framesPassedThatPart++;
        }
    }
}
