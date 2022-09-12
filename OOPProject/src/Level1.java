import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    private KeyboardSensor keyboardSensor;
    private Sprite background;
    private List<Block> blocks;
    private static final int NUMBER_OF_BLOCKS = 1;
    private static final int NUMBER_OF_BALLS = 1;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 80;
    private static final String LEVEL_NAME = "Direct Hit";
    /**
     * Instantiates a new Level 1.
     *
     * @param keyboardSensor the keyboard sensor
     */
    public Level1(KeyboardSensor keyboardSensor) {
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
        Rectangle rect = new Rectangle(new Point(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2),
                GameLevel.BLOCK_HEIGHT, GameLevel.BLOCK_HEIGHT);
        Block b = new Block(rect, Color.RED);
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(b);
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
        Random rnd = new Random();
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v = Velocity.fromAngleAndSpeed(45, GameLevel.BALL_VELOCITY);
        velocities.add(v);
        return new ArrayList<>(velocities);
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
