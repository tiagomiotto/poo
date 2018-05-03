package pec_simulator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Simulator {

	private PEC pec;
	private Event currentEv;
	private Grid grid;
	private Comparator<Individual> comp = new IndividualComparator();
	private PriorityQueue<Individual> population = new PriorityQueue<Individual>(10, comp);
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

	public PriorityQueue<Individual> getPopulation() {
		return population;
	}

	public PEC getPec() {
		return pec;
	}
}