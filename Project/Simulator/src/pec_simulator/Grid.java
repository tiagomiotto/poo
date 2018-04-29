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
}