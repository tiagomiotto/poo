package pec_simulator;
import java.util.Random;


public class Move extends Event {
	private double delta;



	public void simulateEvent() {
		// TODO - implement Move.simulateEvent
		Random  rand = new Random();
		int possible = super.getMe().getPossibilities();
		int  i;
		for(i=0;i<possible;i++){
			if(rand.nextDouble()<=(double)(i/possible)) break;
		}
		switch (i){ //Falta  atualizar custos e afins
			case (1):
				super.getMe().setMy_x(super.getMe().getMy_x() + 1); //N
			case (2):
				super.getMe().setMy_y(super.getMe().getMy_y() + 1); //E
			case (3):
				super.getMe().setMy_x(super.getMe().getMy_x() - 1); //S
			case (4):
				super.getMe().setMy_y(super.getMe().getMy_y() - 1); //W
		}
		super.getRef_pec().addEventPEC(new Move(super.getMe(), generateTimestamp(), super.getRef_pec(), this.delta)); // Add next move
		throw new UnsupportedOperationException();
	}

	@Override
	public double generateTimestamp() {
		Random rand= new Random();
		double lambda = (1 - Math.log(super.getMe().getComfort())) * delta;
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Move(Individual me, double timestamp, PEC ref_pec, double delta2) {
		super(me, timestamp, ref_pec);
		this.delta = delta2;
	}


}