package pec_simulator;

public class Epidemic extends Event {

	public void simulateEvent() {
		// TODO - implement Epidemic.simulateEvent
		throw new UnsupportedOperationException();
	}

	@Override
	public double generateTimestamp() {
		return 0;
	}

	public Epidemic(Individual me, double timestamp, PEC ref_pec) {
		super(me, 0, ref_pec);
	}
}