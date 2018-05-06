package simulator;

import grid.Grid;
import pec.Event;
import pec.EventVariables;
import pec.PEC;
import population.Individual;

import java.util.Comparator;
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