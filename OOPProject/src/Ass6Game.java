import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main1(String[] args) {
        GUI gui = new GUI(GameFlow.PROGRAM_NAME, GameLevel.WIDTH, GameLevel.HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> lst = new ArrayList<LevelInformation>();
        LevelInformation levelInformation1 = new Level1(keyboardSensor);
        lst.add(lst.size(), levelInformation1);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        gameFlow.runLevels(lst);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI(GameFlow.PROGRAM_NAME, GameLevel.WIDTH, GameLevel.HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> lst = new ArrayList<LevelInformation>();
        boolean wasThereLevel = false;
        for (String s: args) {
            switch (s) {
                case "1":
                    wasThereLevel = true;
                    LevelInformation levelInformation1 = new Level1(keyboardSensor);
                    lst.add(lst.size(), levelInformation1);
                    break;
                case "2":
                    wasThereLevel = true;
                    LevelInformation levelInformation2 = new Level2(keyboardSensor);
                    lst.add(lst.size(), levelInformation2);
                    break;
                case "3":
                    wasThereLevel = true;
                    LevelInformation levelInformation3 = new Level3(keyboardSensor);
                    lst.add(lst.size(), levelInformation3);
                    break;
                case "4":
                    wasThereLevel = true;
                    LevelInformation levelInformation4 = new Level4(keyboardSensor);
                    lst.add(lst.size(), levelInformation4);
                    break;
                default:
                    break;
            }
        }
        if (!wasThereLevel) {
            LevelInformation levelInformation = new Level1(keyboardSensor);
            lst.add(lst.size(), levelInformation);
            levelInformation = new Level2(keyboardSensor);
            lst.add(lst.size(), levelInformation);
            levelInformation = new Level3(keyboardSensor);
            lst.add(lst.size(), levelInformation);
            levelInformation = new Level4(keyboardSensor);
            lst.add(lst.size(), levelInformation);
        }
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        gameFlow.runLevels(lst);
    }
}
