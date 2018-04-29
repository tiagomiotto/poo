package pec_simulator;

public class Individual {

	private double comfort;
	private int cost;
	private int length;
	private int my_x;
	private int my_y;
	private int possibilities;
	private Simulator simulator;

	private void evolve(int x, int y, int l, int cost) {
		// TODO - implement Individual.evolve
		this.cost = cost;
		this.length = l;
		this.my_x = x;
		this.my_y = y;
		int dist = 1; // Calcular aqui
		this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
				simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getN() + simulator.getGrid().getM() + 1))), simulator.getVariables().getK());
		throw new UnsupportedOperationException();
	}

	public Individual(int cost, int length, int my_x, int my_y, Simulator simulator) {
		this.cost = cost;
		this.length = length;
		this.my_x = my_x;
		this.my_y = my_y;
		this.simulator = simulator;
		int dist = 1; //Calcular isso
		this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
				simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getN() + simulator.getGrid().getM() + 1))), simulator.getVariables().getK());
	}


	/*Getters and setters*/
	public double getComfort() {
		return comfort;
	}

	public void setComfort(float comfort) {
		this.comfort = comfort;
	}

	public int getPossibilities() {
		return possibilities;
	}

	public void setPossibilities(int possibilities) {
		this.possibilities = possibilities;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getMy_x() {
		return my_x;
	}

	public void setMy_x(int my_x) {
		this.my_x = my_x;
	}

	public int getMy_y() {
		return my_y;
	}

	public void setMy_y(int my_y) {
		this.my_y = my_y;
	}
}