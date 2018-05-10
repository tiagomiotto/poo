/**
 * 
 */
package grid;

import java.util.List;

/**
 * @author poo
 *
 */
public interface IGrid {
	public double dist(Coordinates coord);
	public int cost(List<Coordinates> path, int length);
	public Point getPoint(Coordinates aCoord);
}
