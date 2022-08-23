/**
 * The type Counter.
 */
public class Counter {
    private int value;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
    void increase(int number) {
        this.value += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
    void decrease(int number) {
        this.value -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
}