package pec_simulator;
import java.util.Random;


public class Move extends Event {
	double  delta;
	public void simulateEvent() {
		// TODO - implement Move.simulateEvent
		Random  rand = new Random();
		int possible = me.getPossibilities();
		int  i;
		for(i=0;i<possible;i++){
			if(rand.nextDouble()<=(double)(i/possible)) break;
		}
		switch (i){ //Falta  atualizar custos e afins
			case(1) : me.setMy_x(me.getMy_x()+1); //N
			case(2) : me.setMy_y(me.getMy_y()+1); //E
			case(3) : me.setMy_x(me.getMy_x()-1); //S
			case(4) : me.setMy_y(me.getMy_y()-1); //W
		}
		ref_pec.addEventPEC(new Move(this.me,generateTimestamp(),this.ref_pec,this.delta)); // Add next move
		throw new UnsupportedOperationException();
	}

	@Override
	public double generateTimestamp() {
		Random rand= new Random();
		double lambda= (1-Math.log(me.getComfort()))*delta;
		return Math.log(1-rand.nextDouble())/(-lambda);
	}

	public Move(Individual me, double timestamp, PEC ref_pec, double delta) {
		super(me, timestamp, ref_pec);
		this.delta = delta;
	}
}