import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level name sprite.
 */
public class LevelNameSprite implements Sprite {
    /**
     * The constant LEVEL_NAME_START.
     */
    public static final int LEVEL_NAME_START = 500;
    /**
     * The constant BASE_STR.
     */
    public static final String BASE_STR = "Level Name: ";
    private String levelName;

    /**
     * Constructor.
     *
     * @param levelName the name of the level.
     */
    public LevelNameSprite(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Setter for level name.
     *
     * @param levelName the name of the level.
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(LEVEL_NAME_START, 20, BASE_STR + levelName,
                10);
    }

    @Override
    public void timePassed() {
        //nothing
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
