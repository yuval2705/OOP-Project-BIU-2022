import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

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

    private BlockRemover blockRemover;
    private Counter blockCounter;

    private BlockRemover ballRemover;
    private Counter ballCounter;


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
     * Initialize.
     */
    public void initialize() {

        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blockCounter);

        this.ballCounter = new Counter();
        this.ballRemover = new BlockRemover(this, this.ballCounter);

        int blockWidth = 50;
        int blockHeight = 25;
        int sidesSmall = 20;
        int ballVelocity = 4;
        int ballRadius = 4;
        //
        Ball ball = new Ball(100, 100, ballRadius, Color.BLACK);
        this.ballCounter.increase(1);
        ball.setVelocity(ballVelocity, ballVelocity);
        ball.setGameEnvironment(this.environment);
        //
        ball.addToGame(this);
        //
        Color[] colors = {Color.GREEN, Color.PINK, Color.CYAN, Color.YELLOW, Color.RED, Color.GRAY};
        //
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < (7 + i); j++) {
                Rectangle rect = new Rectangle(new Point(WIDTH - sidesSmall - (j + 1) * blockWidth,
                        (HEIGHT / 2) - (i + 1) * blockHeight), blockWidth, blockHeight);
                Block block = new Block(rect, colors[i]);
                block.addToGame(this);
                this.blockCounter.increase(1);
                block.addHitListener(this.blockRemover);
            }
        }
        //creating the borders
        Rectangle top = new Rectangle(new Point(0, 0), WIDTH, 25);
        Rectangle left = new Rectangle(new Point(0, 0), 25, HEIGHT);
        Rectangle right = new Rectangle(new Point(WIDTH - 25,  0), 25, HEIGHT);
        Rectangle bottom = new Rectangle(new Point(0, HEIGHT - 25), WIDTH, 25);
        Color color = Color.GRAY;
        //
        Block deathZone = new Block(bottom, color);
        deathZone.addHitListener(this.ballRemover);
        (deathZone).addToGame(this);
        (new Block(left, color)).addToGame(this);
        (new Block(right, color)).addToGame(this);
        (new Block(top, color)).addToGame(this);
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
                new Rectangle(paddleStart, paddleWidth, paddleHeight), Color.YELLOW, 4);
        paddle.addToGame(this);
        //
        Sleeper sleeper = new Sleeper();

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        DrawSurface d;
        while (this.blockCounter.getValue() > 10 && this.ballCounter.getValue() > 0) {
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
