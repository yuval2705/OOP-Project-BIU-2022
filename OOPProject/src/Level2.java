import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1.
 */
public class Level2 implements LevelInformation {
    private static final int FIRST_BLOCK_START = 24;
    private KeyboardSensor keyboardSensor;
    private Sprite background;
    private List<Block> blocks;
    private static final int NUMBER_OF_BLOCKS = 15;
    private static final int DISTANCE_BETWEEN_BLOCKS = (GameLevel.WIDTH
            - 2 * GameLevel.BLOCK_HEIGHT + 2) / NUMBER_OF_BLOCKS;

    private static final int BLOCK_WIDTH = DISTANCE_BETWEEN_BLOCKS + 1;

    private static final int START_ANGLE = 310;
    private static final int ANGLE_JUMP = 10;
    private static final int NUMBER_OF_BALLS = 10;
    private static final int PADDLE_SPEED = 2;
    private static final int PADDLE_WIDTH = 300;
    private static final String LEVEL_NAME = "Wide easy";
    /**
     * Instantiates a new Level 1.
     *
     * @param keyboardSensor the keyboard sensor
     */
    public Level2(KeyboardSensor keyboardSensor) {
        this.keyboardSensor = keyboardSensor;
        this.background = this.createBackground();
        this.blocks = this.createBlocks();
    }

    private Sprite createBackground() {
        Rectangle backgournd = new Rectangle(new Point(0, 0), GameLevel.WIDTH, GameLevel.HEIGHT);
        backgournd.setColor(Color.WHITE);
        return background;
    }

    private List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        Color[] colors1 = new Color[15];
        colors1[0] = new Color(255, 0, 0);
        colors1[1] = colors1[0];
        colors1[2] = new Color(255, 200, 0);
        colors1[3] = colors1[2];
        colors1[4] = new Color(255, 255, 0);
        colors1[5] = colors1[4];
        colors1[6] = new Color(0, 255, 0);
        colors1[7] = colors1[6];
        colors1[8] = colors1[6];
        colors1[9] = new Color(0, 0, 255);
        colors1[10] = colors1[9];
        colors1[11] = new Color(255, 175, 175);
        colors1[12] = colors1[11];
        colors1[13] = new Color(0, 255, 255);
        colors1[14] = colors1[13];
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(FIRST_BLOCK_START + i * DISTANCE_BETWEEN_BLOCKS,
                    250), BLOCK_WIDTH, GameLevel.BLOCK_HEIGHT);
            Block b = new Block(rect, colors1[i]);
            blocks.add(blocks.size(), b);
        }
        return blocks;
    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocitys = new ArrayList<Velocity>();
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                velocitys.add(velocitys.size(), Velocity.fromAngleAndSpeed(START_ANGLE + i
                        * ANGLE_JUMP, GameLevel.BALL_VELOCITY));
            } else {
                velocitys.add(velocitys.size(), Velocity.fromAngleAndSpeed(START_ANGLE
                        + (i + 1) * ANGLE_JUMP, GameLevel.BALL_VELOCITY));
            }
        }
        return velocitys;
    }
    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}
