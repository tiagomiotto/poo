package grid;

import java.util.Objects;

/**
 * Used to represent the coordinates of a point from the specified map
 *
 * @author Group 18
 */
public class Coordinates {
    /**
     * x corresponds to the x coordinate
     * y corresponds to the y coordinate
     */
    int x;
    int y;

    /**
     * Constructor for class Coordinates
     * @param xCoord
     *              specified x coordinate
     * @param yCoord
     *              specified y coordinate
     */
    public Coordinates(int xCoord, int yCoord) {
        this.x = xCoord;
        this.y = yCoord;
    }

    // getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // toString description
    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }

    // equality test
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    // setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

