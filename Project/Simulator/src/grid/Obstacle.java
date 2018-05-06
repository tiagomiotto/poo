package grid;

public class Obstacle extends Point {

    // constructor
    public Obstacle(int x, int y) {
        super(x, y);
    }

    // indica que é obstáculo
    @Override
    public boolean obsFlag() {
        return true;
    }
}
