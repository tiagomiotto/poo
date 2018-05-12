package pec;

import population.Individual;

/**
 * Observation.java - A class extending the event class to realize periodic observations on how
 * the simulation is proceeding, printing the current timestamp, the number of events realized,
 * whether or not the final point has been hit, the pathof the current best fit individual,
 * as well as its cost and comfort
 *
 * @author group 18
 * @version 1.0
 * @see Event
 */
public class Observation extends Event {
    int count;
    int event_count;

    /**
     * Constructor
     */
    public Observation(Individual me, double timestamp, PEC ref_pec, int count, int event_count) {
        super(me, timestamp, ref_pec);
        this.count = count;
        this.event_count = event_count;
    }


    /**
     * Method defining the observation behaviour.
     */
    @Override
    public void simulateEvent() {

        System.out.println("Observation: " + count);
        System.out.println("Present instant: " + timestamp);
        System.out.println("Number of realized events: " + event_count);

        if (me.getSimulator().getWinners().size() > 0) System.out.println("Final point has been hit? " +
                "Yes");
        else System.out.println("Final point has been hit? No");

        System.out.println("Path of the best fit individual: " + me.getSimulator().getPopulation().peek().getPathDesc
                ());
        System.out.println("Cost/Comfort " + me.getSimulator().getPopulation().peek().getCost() + "/" + me.getSimulator().getPopulation().peek().getComfort());
        System.out.println();
/*
        if(me.getSimulator().getPec().events.peek()!=null && (timestamp/20)<=1) me.getSimulator().getPec().addEventPEC
                (new
                Observation
                (this)); // Doesnt add if the pec is out of events*/
    }
}
