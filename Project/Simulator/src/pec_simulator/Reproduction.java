package pec_simulator;

import java.util.Random;

public class Reproduction extends Event {

	public void simulateEvent() {
		// TODO - implement Reproduction.simulateEvent
		Individual child = new Individual(getMe());
		//Add events
		getRef_pec().addEventPEC(new Reproduction(getMe(), 1000, getRef_pec()));
		getRef_pec().addEventPEC(new Reproduction(child, 1000, getRef_pec()));
		getRef_pec().addEventPEC(new Move(child, 1000, getRef_pec()));
		getRef_pec().addEventPEC(new Death(child, 1000, getRef_pec()));
		//Update population
		getMe().getSimulator().getPopulation().add(child);
		getMe().getSimulator().getVariables().setV(getMe().getSimulator().getVariables().getV() + 1);
		//Test for epidemic and add it to next event
		if (getMe().getSimulator().getVariables().getV() > getMe().getSimulator().getVariables().getV_max()) {
			getRef_pec().addEventPEC(new Epidemic(getMe(), getRef_pec()));
		}
		throw new UnsupportedOperationException();
	}



	public double generateTimestamp() {
		Random rand= new Random();
		double lambda = (1 - Math.log(getMe().getComfort())) * getRef_pec().simulator.getVariables().getP();
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Reproduction(Individual me, double timestamp, PEC ref_pec) {
		super(me, timestamp, ref_pec);
		super.setTimestamp(this.generateTimestamp());
	}
}