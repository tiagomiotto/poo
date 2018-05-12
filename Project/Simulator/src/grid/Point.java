package grid;

import java.util.LinkedList;

/**
 * Used to represent a "free" point of the specified map
 *
 * @author Group 18
 */
public class Point extends Coordinates {

    // listAdj array that stores the adjacent Points for a specified Point
    private LinkedList<Edge> listAdj = new LinkedList<Edge>();

    // constructor

    /**
     * Constructor for class Point
     *
     * @param x integer corresponding to the x coordinate
     * @param y integer corresponding to the y coordinate
     */
    public Point(int x, int y) {
        super(x, y);
    }

    /**
     * Indicates that a specified Point is "free" (non-obstacle)
     *
     * @return boolean indicating that it is "free"
     */
    public boolean obsFlag() {
        return false;
    }

    /**
     * Adds a Point to the specified Point's adjacent list
     *
     * @param adjacent Point to be added to the specified Point's list
     * @param costAdj  integer corresponding to the cost of the connection between Point to be added and specified Point
     */
    public void addAdj(Point adjacent, int costAdj) {
        Edge adjPoint = new Edge(adjacent, costAdj);
        listAdj.addFirst(adjPoint);
    }

    // verifica se um ponto é adjacente a outro

    /**
     * Indicates if a Point is adjacent to a specified Point
     *
     * @param adjacent Point to be checked if it's adjacent to the specified Point
     * @return boolean that indicates if they are adjacent (true)
     */
    public boolean adjFlag(Point adjacent) {
        for (Edge edge : listAdj) {
            if (edge.getAdjacent() == adjacent) {
                return true;
            }
        }
        return false;
    }

    // getter do custo do adjacente

    /**
     * Gets the cost of the connection between the adjacent Point and the specified Point
     *
     * @param adjacent Point adjacent to the specified Point
     * @return Cost of their connection
     */
    public int getAdjCost(Point adjacent) {
        for (Edge edge : listAdj) {
            if (edge.getAdjacent() == adjacent) {
                return edge.getCost();
            }
        }
        return 0;
    }

    /**
     * Sets the cost of the connection between a specified Point and its adjacent Point
     *
     * @param adjacent Point adjacent to the specified Point
     * @param costAdj  Cost of their connection
     */
    public void setAdjCost(Point adjacent, int costAdj) {
        for (Edge edge : listAdj) {
            if (edge.getAdjacent() == adjacent) {
                edge.setCost(costAdj);
            }
        }
    }

    /**
     * Gets the total number of adjacent Points to a specified Point
     *
     * @return integer number of adjacent Points
     */
    public int getAdjNum() {
        return listAdj.size();
    }

    /**
     * Gets the list of adjacent Points to a specified Point
     *
     * @return LinkedList of adjacent Points
     */
    public LinkedList<Edge> getListAdj() {
        return listAdj;
    }
}
