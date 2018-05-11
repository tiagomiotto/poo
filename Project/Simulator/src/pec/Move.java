package pec;

import grid.Edge;

import population.Individual;

import java.util.LinkedList;
import java.util.Random;

/**
 * Move.java - A class extending the event class in order to determine the move behaviour of each individual.
 * It contains the individual it is related to, the time in which it should be simulated, and a reference to the
 * PEC simulating it.
 *
 * @author Tiago Miotto
 * @version 1.0
 * @see Event
 */

public class Move extends Event {

    /**
     * Method defining the individuals move(evolve) behaviour.
     */
    public void simulateEvent() {
        // TODO - implement Move.simulateEvent
        Random rand = new Random();
        // System.out.println("move");
        int possible = super.getMe().getPossibilities();
        int i;
        double random = rand.nextDouble();
        LinkedList<Edge> poss = new LinkedList<Edge>(super.getMe().getSimulator().getGrid().getPoint(super.getMe()
                .getCurrent()).getListAdj()); //Pega os moves possiveis
        for (i = 0; i < possible - 1; i++) {
            if (random <= (i / possible)) break;
        }

        // System.out.println("i = " +i + " possible=  "+ possible + " random = " + random + " p/i = " + aux);
        super.getMe().evolve(poss.get(i).getAdjacent());

        super.getRef_pec().addEventPEC(new Move(super.getMe(), this.getTimestamp(), super.getRef_pec())); //
        // Add
        // next move

    }

    /**
     * Method for generating the correct timestamp corresponding to a exponential aleatory variable with mean value
     * (1-log(fi)*delta
     */

    private final double generateTimestamp(double time) {
        Random rand = new Random();
        double lambda = (1 - Math.log(super.getMe().getComfort())) * getMe().getSimulator().getVariables().getDelta();
        return time + (-lambda * Math.log(Math.random()));
    }

    /**
     * Constructor
     */
    public Move(Individual me, double timestamp, PEC ref_pec) {
        super(me, timestamp, ref_pec);
        super.setTimestamp(this.generateTimestamp(super.getTimestamp()));

    }
}
