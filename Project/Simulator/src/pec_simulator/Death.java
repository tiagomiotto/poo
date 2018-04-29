package pec_simulator;

import java.util.PriorityQueue;
import java.util.Random;

public class Death extends Event {


	public void simulateEvent() {
		// TODO - implement Death.simulateEvent
		PriorityQueue<Event> pqCopy = new PriorityQueue<Event>(getRef_pec().events);
		while (!pqCopy.isEmpty()) { //Removes following events from PEC
			Event obj = pqCopy.poll();
			if (obj.getMe() == this.getMe()) getRef_pec().events.remove(obj);
			// obj is the next ordered item in the queue
		}
		this.setMe(null); //Kill object?
		throw new UnsupportedOperationException();

	}

	public final double generateTimestamp() {
		Random rand= new Random();
		double lambda = (1 - Math.log(getMe().getComfort())) * getRef_pec().simulator.getVariables().getMiu();
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Death(Individual me, double timestamp, PEC ref_pec) {
		super(me, timestamp, ref_pec);
		super.setTimestamp(this.generateTimestamp());
	}
}