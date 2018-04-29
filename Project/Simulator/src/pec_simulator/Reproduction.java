package pec_simulator;

import java.util.Random;

public class Reproduction extends Event {
	double p;
	public void simulateEvent() {
		// TODO - implement Reproduction.simulateEvent
		throw new UnsupportedOperationException();
	}



	@Override
	public double generateTimestamp() {
		Random rand= new Random();
		double lambda= (1-Math.log(me.getComfort()))*p;
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Reproduction(Individual me, double timestamp, PEC ref_pec, double p) {
		super(me, timestamp, ref_pec);
		this.p = p;

	}
}