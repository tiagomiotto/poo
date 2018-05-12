package grid;

/**
 * Used to represent an adjacent Point of a specified Point
 *
 * @author Group 18
 */
public class Edge {
    /**
     * adjacent stores the adjacent Point to the specified Point
     * cost stores the cost of that connection, -1 by default to represent that there is no connection
     */
    private Point adjacent;
    private int cost = -1;

    /**
     * Constructor for the class Adjacent
     *
     * @param adjacent Point adjacent to a specified Point
     * @param cost     integer of the cost of their connection
     */
    Edge(Point adjacent, int cost) {
        this.adjacent = adjacent;
        this.cost = cost;
    }

    // getters
    public Point getAdjacent() {
        return adjacent;
    }

    public int getCost() {
        return cost;
    }

    // setter
    public void setCost(int cost) {
        this.cost = cost;
    }

}
