package simulator;

import grid.Grid;
import pec.*;
import population.Individual;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Simulator {

	private PEC pec;
	private Event currentEv;
	private Grid grid;
	private Comparator<Individual> comp = new IndividualComparator();
	private PriorityQueue<Individual> population = new PriorityQueue<Individual>(10, comp);
	private EventVariables variables = new EventVariables(0, 0, 0, 0, 0);
	private double currentTime;
	private int events;




	public void main() {
		// TODO - implement Simulator.main
		currentTime = 0.0;
		int observ = 0;
		events = 0;
		int i = 1;
		String fim = new String();
		pec = new PEC();
		begin();
		currentEv = pec.getEvents().peek();
		currentTime = currentEv.getTimestamp();

		while (currentTime < variables.c_max) {
			if (currentTime > variables.c_max * i / 20) {
				observ++;
				System.out.println("Observation: " + observ);
				System.out.println("Present instant: " + currentTime);
				System.out.println("Number of realized events: " + events);
				System.out.println("Final point has been hit? " + fim);
				System.out.println("Path of the best fit individual: " + population.peek().getPathDesc());
				System.out.println("Cost/Comfort " + population.peek().getCost() + "/" + population.peek().getComfort());
				i++;
			}
			currentEv = pec.nextEventPEC();
			currentTime = currentEv.getTimestamp();
		}
		System.out.println("Path of the best fit individual: " + population.peek().getPathDesc());
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

	private void begin() {
		for (int i = 0; i < variables.v_init; i++) {
			Individual firstborn = new Individual(this);
			pec.addEventPEC(new Reproduction(firstborn, 1000, pec));
			pec.addEventPEC(new Move(firstborn, 1000, pec));
			pec.addEventPEC(new Death(firstborn, 1000, pec));
			population.add(firstborn);
		}
	}
}