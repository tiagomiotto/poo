package grid;

public class Tuple {
    private Coordinates iCoord, fCoord;
    private int cost;

    // constructor
    Tuple(Coordinates initCoord, Coordinates finCoord, int cost) {
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
