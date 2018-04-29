package pec_simulator;

import java.util.Random;

public class Death extends Event {
	double miu;

	public void simulateEvent() {
		// TODO - implement Death.simulateEvent
		throw new UnsupportedOperationException();
	}

	@Override
	public double generateTimestamp() {
		Random rand= new Random();
		double lambda= (1-Math.log(me.getComfort()))*miu;
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Death(Individual me, double timestamp, PEC ref_pec, double miu) {
		super(me, timestamp, ref_pec);
		this.miu = miu;
	}
}