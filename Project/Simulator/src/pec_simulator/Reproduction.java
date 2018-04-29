package pec_simulator;

import java.util.Random;

public class Reproduction extends Event {

	public void simulateEvent() {
		// TODO - implement Reproduction.simulateEvent
		throw new UnsupportedOperationException();
	}




	public double generateTimestamp() {
		Random rand= new Random();
		double lambda = (1 - Math.log(getMe().getComfort())) * getRef_pec().variables.getP();
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Reproduction(Individual me, double timestamp, PEC ref_pec) {
		super(me, timestamp, ref_pec);
		super.setTimestamp(this.generateTimestamp());
	}
}