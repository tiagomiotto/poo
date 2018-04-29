package pec_simulator;

public abstract class Event {

	protected Individual me;
	protected double timestamp;
	protected PEC ref_pec;

	public abstract void simulateEvent();

	public double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}

	public abstract double generateTimestamp();

	public Event(Individual me, double timestamp, PEC ref_pec) {
		this.me = me;
		this.timestamp = timestamp;
		this.ref_pec = ref_pec;
	}
}