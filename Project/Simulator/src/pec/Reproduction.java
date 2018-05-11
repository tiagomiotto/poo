package pec;

import population.Individual;

import java.util.Random;

/**
 * Reproduction.java - A class extending the event class in order to determine the reproduction behaviour of each
 * individual.
 * It contains the individual it is related to, the time in which it should be simulated, and a reference to the
 * PEC simulating it.
 *
 * @author Tiago Miotto
 * @version 1.0
 * @see Event
 */
public class Reproduction extends Event {

    /**
     * Method defining the individuals reproduction behaviour.
     */
    public void simulateEvent() {


        Individual child = new Individual(super.getMe()); //Construtor  para reprodução

        //Add events
        super.getRef_pec().addEventPEC(new Reproduction(super.getMe(), super.getTimestamp(), getRef_pec()));

        //Adiciona primeiros eventos da child
        super.getRef_pec().addEventPEC(new Reproduction(child, super.getTimestamp(), getRef_pec()));
        super.getRef_pec().addEventPEC(new Move(child, super.getTimestamp(), getRef_pec()));
        super.getRef_pec().addEventPEC(new Death(child, super.getTimestamp(), getRef_pec()));

        //Update population
        getMe().getSimulator().getPopulation().add(child);

        //Test for epidemic if so add it to next event
        if (getMe().getSimulator().getPopulation().size() > getMe().getSimulator().getVariables().getV_max()) {
            System.out.println("Epidemic will occur now");
            getRef_pec().addEventPEC(new Epidemic(getMe(), getRef_pec()));
        }

    }

    /**
     * Method for generating the correct timestamp corresponding to a exponential aleatory variable with mean value
     * (1-log(fi)*P
     */
    private double generateTimestamp(double time) {
        Random rand = new Random();
        double lambda = (1 - Math.log(getMe().getComfort())) * getMe().getSimulator().getVariables().getP();
        return time + (-lambda * Math.log(Math.random()));
    }

    /**
     * Constructor
     */
    public Reproduction(Individual me, double timestamp, PEC ref_pec) {
        super(me, timestamp, ref_pec);
        super.setTimestamp(this.generateTimestamp(this.getTimestamp()));
    }

}