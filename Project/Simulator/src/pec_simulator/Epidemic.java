package pec_simulator;

public class Epidemic extends Event {

	public void simulateEvent() {
		// TODO - implement Epidemic.simulateEvent

        throw new UnsupportedOperationException();
    }


	public double generateTimestamp() {
		return 0;
	}

	public Epidemic(Individual me, PEC ref_pec) {
		super(me, 0, ref_pec);
	}
}