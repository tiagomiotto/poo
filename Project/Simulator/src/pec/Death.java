package pec;

import population.Individual;

import java.util.PriorityQueue;
import java.util.Random;


public class Death extends Event {


	public void simulateEvent() {
		// TODO - implement Death.simulateEvent
		System.out.println("death");
		PriorityQueue<Event> pqCopy = new PriorityQueue<Event>(getRef_pec().events);
		while (!pqCopy.isEmpty()) { //Removes following events from PEC
			Event obj = pqCopy.poll();
			if (obj.getMe() == this.getMe()) getRef_pec().events.remove(obj);
			// obj is the next ordered item in the queue
		}
		getMe().getSimulator().getPopulation().remove(getMe()); //Remove da população

		this.setMe(null); //Kill object?



	}

	public final double generateTimestamp() {
		Random rand = new Random();
		double lambda = (1 - Math.log(getMe().getComfort())) * getMe().getSimulator().getVariables().getMiu();
		return (-lambda * Math.log(Math.random()));
	}

	public Death(Individual me, double timestamp, PEC ref_pec) {
		super(me, timestamp, ref_pec);
		super.setTimestamp(this.generateTimestamp());

	}
}