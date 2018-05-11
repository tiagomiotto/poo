package pec;
import population.Individual;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Death.java - A class extending the event class in order to determine the death behaviourr of each individual.
 * It contains the individual it is related to, the time in which it should be simulated, and a reference to the
 * PEC simulating it.
 *
 * @author Tiago Miotto
 * @version 1.0
 */


public class Death extends Event {

	/**
	 * Method defining the individuals death behaviour.
	 */
	public void simulateEvent() {
		// TODO - implement Death.simulateEvent
		//System.out.println("death");
		PriorityQueue<Event> pqCopy = new PriorityQueue<Event>(getRef_pec().events);
		while (!pqCopy.isEmpty()) { //Removes following events from PEC
			Event obj = pqCopy.poll();
			if (obj.getMe() == this.getMe()) getRef_pec().events.remove(obj);
			// obj is the next ordered item in the queue
		}

		//System.out.println("new pop zise:" +getMe().getSimulator().getPopulation().size());
		getMe().getSimulator().getPopulation().remove(getMe()); //Remove da população


		//this.setMe(null); //Kill object?


	}

	/**
	 * Method for generating the correct timestamp corresponding to a exponential aleatory variable with mean value
	 * (1-log(fi)*miu
	 *
	 */
	public final double generateTimestamp() {
		Random rand = new Random();
		double lambda = (1 - Math.log(getMe().getComfort())) * getMe().getSimulator().getVariables().getMiu();
		return (-lambda * Math.log(Math.random()));
	}

	/**
	 * Constructor
	 *
	 */
	public Death(Individual me, double timestamp, PEC ref_pec) {
		super(me, timestamp, ref_pec);
		super.setTimestamp(this.generateTimestamp());


	}
}