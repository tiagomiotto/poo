package grid;

public class Edge {
    private Point adjacent;
    private int cost = -1;

    // constructor
    Edge (Point adjacent, int cost) {
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
