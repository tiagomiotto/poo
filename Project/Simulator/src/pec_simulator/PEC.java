package pec_simulator;

import java.util.PriorityQueue;

public class PEC implements PECS {

	private PriorityQueue<Event> events;

	/**
	 * 
	 * @param
	 */
	public void addEventPEC(Event o1) {
		// TODO - implement PEC.addEventPEC
		events.add(o1);
		throw new UnsupportedOperationException();
	}

	public void nextEventPEC() {
		// TODO - implement PEC.nextEventPEC
		Event aux;
		aux=events.poll();
		aux.simulateEvent();
		throw new UnsupportedOperationException();
	}

}