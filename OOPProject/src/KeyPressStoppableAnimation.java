import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private String key;
    private KeyboardSensor sensor;
    private boolean isAlreadyPressed;

    private boolean shouldStop;
    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.shouldStop = false;
        this.isAlreadyPressed = sensor.isPressed(key);
    }
    /**
     * Do one frame.
     *
     * @param d the d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.isAlreadyPressed && this.sensor.isPressed(key)) {
            this.shouldStop = true;
        } else if (!this.sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}