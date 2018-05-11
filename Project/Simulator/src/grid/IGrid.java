
package grid;

import java.util.List;

/**
 * IGrid.java - An interface outlining the minimum required methods
 * to be provided by an the grid
 *
 * @author Tiago Miotto
 * @version 1.0
 */
public interface IGrid {
    double dist(Coordinates coord);

    int cost(List<Coordinates> path, int length);

    Point getPoint(Coordinates aCoord);
}