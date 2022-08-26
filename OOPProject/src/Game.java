import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The type Game.
 */
public class Game {
    /**
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;

    /**
     * The Ball radius.
     */
    static final int BALL_RADIUS = 4;

    /**
     * The Starting ball speed.
     */
    static final int STARTING_BALL_SPEED = 4;
    /**
     * The Block width.
     */
    static final int BLOCK_WIDTH = 50;
    /**
     * The Block height.
     */
    static final int BLOCK_HEIGHT = 25;
    /**
     * The Small side.
     */
    static final int SMALL_SIDE = 20;
    private BlockRemover blockRemover;
    private Counter blockCounter;

    private BallRemover ballRemover;
    private Counter ballCounter;

    private ScoreTrackingListener scoreTrackingListener;
    private Counter score;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
    private SpriteCollection sprites;
    private  GameEnvironment environment;

    /**
     * Instantiates a new Game.
     *
     * @param sprites     the sprites
     * @param environment the environment
     */
    public Game(SpriteCollection sprites, GameEnvironment environment) {
        this.sprites = sprites;
        this.environment = environment;
    }

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blockCounter);

        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, this.ballCounter);

        this.score = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
    }

    /**
     * Add colidable.
     *
     * @param c the c
     */
    public void addColidable(ICollidable c) {
        if (this.environment == null) {
            this.environment = new GameEnvironment();
        }
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        if (this.sprites == null) {
            this.sprites = new SpriteCollection();
        }
        this.sprites.addSprite(s);
    }

    /**
     * Initialize balls.
     *
     * @param number the number
     */
    public void initializeBalls(int number) {
        Random rnd = new Random();
        for (int i = 0; i < number; i++) {
            Ball ball = new Ball(100, 100, BALL_RADIUS, Color.BLACK);
            ball.addToGame(this);
            this.ballCounter.increase(1);
            Velocity v = Velocity.fromAngleAndSpeed(100 + rnd.nextInt(81), STARTING_BALL_SPEED);
            ball.setVelocity(v);
            ball.setGameEnvironment(this.environment);
        }
    }

    /**
     * Initialize blocks.
     */
    public void initializeBlocks() {
        //the color of the blocks
        Color[] colors = {Color.GREEN, Color.PINK, Color.CYAN, Color.YELLOW, Color.RED, Color.GRAY};

        //creating the blocks
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < (7 + i); j++) {
                Rectangle rect = new Rectangle(new Point(WIDTH - SMALL_SIDE - (j + 1) * BLOCK_WIDTH,
                        (HEIGHT / 2) - (i + 1) * BLOCK_HEIGHT), BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, colors[i]);
                block.addToGame(this);
                this.blockCounter.increase(1);
                block.addHitListener(this.blockRemover);
                block.addHitListener(this.scoreTrackingListener);
            }
        }

    }

    /**
     * Initialize borders.
     */
    public void initializeBorders() {
        Rectangle top = new Rectangle(new Point(0, 0), WIDTH, 25);
        Rectangle left = new Rectangle(new Point(0, 0), 25, HEIGHT);
        Rectangle right = new Rectangle(new Point(WIDTH - 25,  0), 25, HEIGHT);
        Rectangle bottom = new Rectangle(new Point(0, HEIGHT - 25), WIDTH, 25);
        Color color = Color.GRAY;
        //
        Block deathZone = new Block(bottom, color);
        deathZone.addHitListener(this.ballRemover);
        deathZone.addToGame(this);
        (new Block(left, color)).addToGame(this);
        (new Block(right, color)).addToGame(this);
        (new Block(top, color)).addToGame(this);
    }

    /**
     * Initialize.
     */
    public void initialize() {
        // creating the score
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        // creating the balls
        initializeBalls(3);
        // creating the blocks
        initializeBlocks();
        // creating the borders
        initializeBorders();
        // adding the score
        scoreIndicator.addToGame(this);
    }

    /**
     * Run.
     */
    public void run() {
        int paddleHeight = 10;
        int paddleWidth = 70;
        Point paddleStart = new Point((WIDTH - paddleWidth) / 2, 565);
        //
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                new Rectangle(paddleStart, paddleWidth, paddleHeight), Color.YELLOW, 6);
        paddle.addToGame(this);
        //
        Sleeper sleeper = new Sleeper();

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        DrawSurface d;
        while (this.ballCounter.getValue() > 0) {
            //checks if all the blocks are gone
            if (this.blockCounter.getValue() <= 0) {
                this.score.increase(100);
                //creating all the blocks again
                this.initializeBlocks();
                this.initializeBalls(1);
            }
            long startTime = System.currentTimeMillis(); // timing

            d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        d = gui.getDrawSurface();
        gui.close();
    }

    /**
     * Remove a collidable object.
     *
     * @param c the c
     */
    public void removeCollidable(ICollidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove a sprite object.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
