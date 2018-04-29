package pec_simulator;

import java.util.Random;

public class Individual {

	private double comfort;
	private int cost;
	private int length;
	private int my_x;
	private int my_y;
    private int[][] path;
    private int possibilities;
    private Simulator simulator;

    void evolve() {
        // TODO - implement Evolution
        this.length += 1;
        path[length - 1][0] = my_x;
        path[length - 1][1] = my_y;
        this.cost = simulator.getGrid().calclulateCost(path);

        int dist = simulator.getGrid().calculateDist(my_x, my_y);
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getN() + simulator.getGrid().getM() + 1))), simulator.getVariables().getK());

		throw new UnsupportedOperationException();
	}

    public Individual(Simulator simulator) {
        this.cost = 0;
        this.length = 0;
        this.my_x = 0;
        this.my_y = 0;
        this.simulator = simulator;
        int dist = simulator.getGrid().calculateDist(this.my_x, this.my_y);
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getN() + simulator.getGrid().getM() + 1))), simulator.getVariables().getK());
	}

    public Individual(Individual o1) {
        //TODO construct from a parent individual
        double percentage = Math.ceil(90 + o1.getComfort() * 10);
        this.length = (int) Math.ceil(percentage / 100 * o1.getLength());
        int[][] aux = o1.getPath();

        for (int i = 0; i < this.length; i++)
            for (int j = 0; j < 2; j++)
                this.path[i][j] = aux[i][j];

        this.my_x = this.path[length - 1][0];
        this.my_y = this.path[length - 1][1];
        this.cost = simulator.getGrid().calclulateCost(this.path);

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

    public int[][] getPath() {
        return path;
    }

    public Simulator getSimulator() {
        return simulator;
    }
}