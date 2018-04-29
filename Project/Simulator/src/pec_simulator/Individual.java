package pec_simulator;

public class Individual {

	private float comfort;
	private int cost;
	private int length;
	private int my_x;
	private int my_y;
	private int possibilities;

	public Event evolve() {
		// TODO - implement Individual.evolve
		throw new UnsupportedOperationException();
	}

	public float getComfort() {
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