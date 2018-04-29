package pec_simulator;
import java.util.Random;


public class Move extends Event {

	public void simulateEvent() {
		// TODO - implement Move.simulateEvent
		Random  rand = new Random();
		int possible = super.getMe().getPossibilities();
		int  i;
		for(i=0;i<possible;i++){
			if(rand.nextDouble()<=(double)(i/possible)) break;
		}
		switch (i){ //Falta  atualizar custos e afins
			case (1): {

			}
			case (2):
				super.getMe().setMy_y(super.getMe().getMy_y() + 1); //E
			case (3):
				super.getMe().setMy_x(super.getMe().getMy_x() - 1); //S
			case (4):
				super.getMe().setMy_y(super.getMe().getMy_y() - 1); //W
		}
		super.getRef_pec().addEventPEC(new Move(super.getMe(), generateTimestamp(), super.getRef_pec())); // Add next move
		throw new UnsupportedOperationException();
	}


	public final double generateTimestamp() {
		Random rand= new Random();
		double lambda = (1 - Math.log(super.getMe().getComfort())) * getMe().getSimulator().getVariables().getDelta();
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Move(Individual me, double timestamp, PEC ref_pec) {
		super(me, timestamp, ref_pec);
		super.setTimestamp(this.generateTimestamp());
	}
}