package pec_simulator;

public class Grid {

	private int n;
	private int m;
	private int xi;
	private int xf;
	private int yi;
	private int yf;
	private Tuple[] tuples;
	private Obstacle[] obstacles;

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public int getXf() {
		return xf;
	}

	public int getYf() {
		return yf;
	}

    public int calclulateCost(int[][] path) {
        //TODO calculates cost of the path
		// a
        return 0;
    }

    public int calculateDist(int x, int y) {
        //TODO calculate dist from (x,y) to (xf,yf) shortest path
        return 0;
    }

    public int getPossibilities(int x, int y) {
        //TODO get possible movements for each (x,y)
        return 0;
    }
}