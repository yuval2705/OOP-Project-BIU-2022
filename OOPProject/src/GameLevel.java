import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    public static final int BALL_VELOCITY = 5;
    public static final int PADDLE_VELOCITY = 7;
    /**
     * The Fps.
     */
    static final int FPS = 60;
    /**
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;

    static final int BLOCK_HEIGHT = 29;
    /**
     * The Ball radius.
     */
    static final int BALL_RADIUS = 4;
    private BlockRemover blockRemover;
    private Counter blockCounter;

    private BallRemover ballRemover;
    private Counter ballCounter;

    private ScoreTrackingListener scoreTrackingListener;
    private Counter score;

    private boolean running;
    private AnimationRunner runner;
    private SpriteCollection sprites;
    private  GameEnvironment environment;
    private KeyboardSensor keyBoard;
    private LevelNameSprite levelNameSprite;

    private LevelInformation levelInformation;
    /**
     * Instantiates a new Game.
     *
     * @param levelInformation the level information
     * @param ks               the ks
     * @param ar               the ar
     * @param score            the score
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.running = true;
        this.runner = ar;
        //
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blockCounter);

        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, this.ballCounter);

        this.score = score;
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.keyBoard = ks;
        this.levelInformation = levelInformation;
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
     */
    public void initializeBallsAndPaddle() {
        int paddleHeight = 10;
        int paddleWidth = this.levelInformation.paddleWidth();
        Point paddleStart = new Point((WIDTH - paddleWidth) / 2, 565);
        //
        Paddle paddle = new Paddle(this.keyBoard,
                new Rectangle(paddleStart, paddleWidth, paddleHeight), Color.YELLOW,
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);

        ArrayList<Velocity> ballVelocities = new ArrayList<Velocity>(this.levelInformation.initialBallVelocities());
        for (Velocity v: ballVelocities) {
            Ball ball = new Ball(paddleStart.getX(), HEIGHT - (paddleHeight + 40), BALL_RADIUS, Color.BLACK);
            ball.addToGame(this);
            this.ballCounter.increase(1);
            ball.setVelocity(v);
            ball.setGameEnvironment(this.environment);
        }
    }

    /**
     * Initialize blocks.
     */
    public void initializeBlocks() {
        ArrayList<Block> temp = new ArrayList<Block>(this.levelInformation.blocks());

        for (Block block : temp) {
            block.addToGame(this);
            this.blockCounter.increase(1);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTrackingListener);
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
        this.levelNameSprite = new LevelNameSprite(this.levelInformation.levelName());
        // creating the score
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        // creating the balls
        initializeBallsAndPaddle();
        // creating the blocks
        initializeBlocks();
        // creating the borders
        initializeBorders();
        // adding the score
        scoreIndicator.addToGame(this);
        (this.levelNameSprite).addToGame(this);
    }

    /**
     * Run.
     */
    public void run() {
        //this.initializeBallsAndPaddle(); // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

        if (this.blockCounter.getValue() <= 0) {
            this.score.increase(100);
            this.running = false;
        }
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

    /**
     * Do one frame.
     *
     * @param d the d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyBoard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyBoard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
            //
            this.runner.run(new CountDownAnimation(2, 3, this.sprites));
        }
        if (this.blockCounter.getValue() <= 0) {
            this.running = false;
        }
        if (this.ballCounter.getValue() <= 0) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets ballcounter.
     *
     * @return the ballcounter
     */
    public int getBallcounter() {
        return this.ballCounter.getValue();
    }

    /**
     * Gets blocks counter.
     *
     * @return the blocks counter
     */
    public int getBlocksCounter() {
        return this.blockCounter.getValue();
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }
}
