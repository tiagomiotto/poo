package pec;


import java.util.PriorityQueue;

/**
 * PEC.java - A class implementing a Private Event Container, used for simulation based events.
 * It implements the Ipec interface.
 *
 * @author Tiago Miotto
 * @version 1.0
 */
public class PEC implements Ipec {


    PriorityQueue<Event> events = new PriorityQueue<>(10, new EventComparator());

    //Simulator simulator;

    /**
     * Method to add events to the PEC
     *
     * @param o1, the event to be added
     */
    public void addEventPEC(Event o1) {

        events.add(o1);

    }

    /**
     * Method get the next event to be simulated and removes it from the PEC
     */
    public Event nextEventPEC() throws OutOfEventsException {

        Event aux;
        aux = events.poll();

        if (aux != null) {
            aux.simulateEvent();

        } else throw new OutOfEventsException();

        return aux;
    }

    /**
     * Retrieve a reference to the events PQ
     */
    public PriorityQueue<Event> getEvents() {
        return events;
    }
}