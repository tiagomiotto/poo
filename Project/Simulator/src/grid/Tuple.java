package grid;

/**
 * Used to represent a high cost zone (tuple)
 *
 * @author Group 18
 */
public class Tuple {
    /**
     * iCoord corresponds to the initial Coordinates of the Tuple
     * fCoord corresponds to the final Coordinates of the Tuple
     */
    private Coordinates iCoord, fCoord;
    /**
     * cost represents the cost of the Tuple
     */
    private int cost;

    // constructor

    /**
     * Constructor for class Tuple
     * @param initCoord
     *              Coordinates corresponding to the initial point of the specified Tuple
     * @param finCoord
     *              Coordinates corresponding to the final point of the specified Tuple
     * @param cost
     *              integer corresponding to the cost of the specified Tuple
     */
    public Tuple(Coordinates initCoord, Coordinates finCoord, int cost) {
        this.iCoord = initCoord;
        this.fCoord = finCoord;
        this.cost = cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    // getters
    public Coordinates getiCoord() {
        return iCoord;
    }

    public Coordinates getfCoord() {
        return fCoord;
    }

    public int getCost() {
        return cost;
    }
}
