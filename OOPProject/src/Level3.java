import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1.
 */
public class Level3 implements LevelInformation {
    private KeyboardSensor keyboardSensor;
    private Sprite background;
    private List<Block> blocks;

    private static final Color[] COLORS = {new Color(128, 128, 128), new Color(255, 0, 0),
            new Color(255, 255, 0), new Color(0, 0, 255), new Color(255, 255, 255)};
    private static final int NUMBER_OF_BALLS = 2;
    private static final int NUMBER_OF_BLOCKS = 40;
    private static final int PADDLE_WIDTH = 86;
    private static final int PADDLE_SPEED = 5;
    private static final int NUMBER_OF_ROWS = 5;

    private static final int START_OF_BLOCK_X = 250 + GameLevel.BLOCK_HEIGHT;

    private static final int START_OF_BLOCK_Y = 150;

    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_WIDTH = 50;
    private static final int NUMBER_OF_BLOCKS_FIRST_ROW = 10;
    private static final String LEVEL_NAME = "Green 3";
    /**
     * Instantiates a new Level 1.
     *
     * @param keyboardSensor the keyboard sensor
     */
    public Level3(KeyboardSensor keyboardSensor) {
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
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = i; j < NUMBER_OF_BLOCKS_FIRST_ROW; j++) {
                Rectangle rect = new Rectangle(new Point(START_OF_BLOCK_X + j * BLOCK_WIDTH,
                        START_OF_BLOCK_Y + i * BLOCK_HEIGHT), BLOCK_WIDTH,
                        BLOCK_HEIGHT);
                Block b = new Block(rect, COLORS[i]);
                blocks.add(blocks.size(), b);
            }
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
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v = Velocity.fromAngleAndSpeed(315, GameLevel.BALL_VELOCITY);
        velocities.add(v);
        v = Velocity.fromAngleAndSpeed(45, GameLevel.BALL_VELOCITY);
        velocities.add(v);
        return velocities;
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
