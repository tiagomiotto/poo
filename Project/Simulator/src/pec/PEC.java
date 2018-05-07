package pec;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PEC implements PECS {

    Comparator<Event> ev = new EventComparator();
    PriorityQueue<Event> events = new PriorityQueue<>(10, ev);
    //Simulator simulator;

    /**
     * @param o1
     */
    public void addEventPEC(Event o1) {
        // TODO - implement PEC.addEventPEC
        events.add(o1);


    }

    public Event nextEventPEC() {
        // TODO - implement PEC.nextEventPEC
        Event aux;
        aux = events.poll();
        if (aux != null) aux.simulateEvent();
        return aux;
    }

    public PriorityQueue<Event> getEvents() {
        return events;
    }
}