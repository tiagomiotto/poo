package grid;

/**
 * Used to represent an obstacle point
 *
 * @author Group 18
 */
public class Obstacle extends Point {

    // constructor

    /**
     * Constructor for class Obstacle
     *
     * @param x integer corresponding to the x coordinate
     * @param y integer corresponding to the y coordinate
     */
    public Obstacle(int x, int y) {
        super(x, y);
    }

    // indica que é obstáculo

    /**
     * Overrides the Point obsFlag method to indicate that this is an obstacle
     *
     * @return boolean true for obsFlag method
     */
    @Override
    public boolean obsFlag() {
        return true;
    }
}
