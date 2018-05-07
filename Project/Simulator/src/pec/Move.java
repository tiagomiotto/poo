package pec;

import grid.Edge;
import grid.Point;
import population.Individual;

import java.util.LinkedList;
import java.util.Random;


public class Move extends Event {

    public void simulateEvent() {
        // TODO - implement Move.simulateEvent
        Random rand = new Random();
        // System.out.println("move");
        double possible = super.getMe().getPossibilities();
        int i;
        double aux = 0;
        double random = rand.nextDouble();
        LinkedList<Edge> poss = new LinkedList<Edge>(super.getMe().getSimulator().getGrid().getPoint(super.getMe()
                .getCurrent()).getListAdj()); //Pega os moves possiveis
        for (i = 0; i < possible - 1; i++) {
            if (random <= (aux = (i / possible))) break;
        }

        // System.out.println("i = " +i + " possible=  "+ possible + " random = " + random + " p/i = " + aux);
        super.getMe().evolve(poss.get(i).getAdjacent());

        super.getRef_pec().addEventPEC(new Move(super.getMe(), this.getTimestamp(), super.getRef_pec())); //
        // Add
        // next move

    }


    private final double generateTimestamp(double time) {
        Random rand = new Random();
        double lambda = (1 - Math.log(super.getMe().getComfort())) * getMe().getSimulator().getVariables().getDelta();
        return time + (-lambda * Math.log(Math.random()));
    }

    public Move(Individual me, double timestamp, PEC ref_pec) {
        super(me, timestamp, ref_pec);
        super.setTimestamp(this.generateTimestamp(super.getTimestamp()));
        //System.out.println("I will move at: " + this.getTimestamp());
    }
}