package pec_simulator;

import java.util.LinkedList;

public class Simulator {

	private PEC pec;
	private Event currentEv;
	private Grid grid;
	private LinkedList<Individual> population = new LinkedList<Individual>();
	private EventVariables variables;

	public void main() {
		// TODO - implement Simulator.main
		throw new UnsupportedOperationException();
	}

	public EventVariables getVariables() {
		return variables;
	}

	public Grid getGrid() {
		return grid;
	}

	public LinkedList<Individual> getPopulation() {
		return population;
	}
}