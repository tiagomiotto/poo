package simulator;

import java.util.Objects;

public class Coordinates {
    int x;
    int y;

    // constructor
    Coordinates (int xCoord, int yCoord) {
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
}
