/**
 * Class description: Class "Counter" is a simple class that is used for counting various things, like the score of
 * the game, and number of objects left in the game.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Counter {
    // Fields
    private int counter;

    /**
     * A constructor that initializes the counter to value 0.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * A constructor that initializes according to the argument.
     * @param counter counter will initialize our field.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * method "increase" will increase the value of the field "counter".
     *
     * @param number the number which will be added to our field.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * method "decrease" will decrease the value of the field "counter".
     *
     * @param number the number which will be added to our field.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * @return returns the value of the current count.
     */
    public int getValue() {
        return this.counter;
    }
}