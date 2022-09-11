import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    /**
     * The constant PROGRAM_NAME.
     */
    public static final String PROGRAM_NAME = "Arkanoid";
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private boolean gameOver;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.gui = gui;
        this.gameOver = false;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        int numberOfLevels = levels.size();
        ArrayList<LevelInformation> levelss = new ArrayList<LevelInformation>(levels);
        for (LevelInformation levelInfo : levelss) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);

            level.initialize();

            this.animationRunner.run(new CountDownAnimation(2, 3, level.getSprites()));
            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getBallcounter() <= 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        keyboardSensor.SPACE_KEY, new GameOver(this.score)));
                        this.gameOver = true;
                break;
            }
        }
        if (!gameOver) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    keyboardSensor.SPACE_KEY, new GameWon(this.score)));
        }
        gui.close();
    }
}
