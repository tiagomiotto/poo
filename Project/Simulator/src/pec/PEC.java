package pec;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PEC implements PECS {


    PriorityQueue<Event> events = new PriorityQueue<>(10, new EventComparator());
    //Simulator simulator;

    /**
     * @param o1
     */
    public void addEventPEC(Event o1) {
        // TODO - implement PEC.addEventPEC
        events.add(o1);
        //System.out.println("prior quque: " + events);
        /*
        System.out.println("Event added at: " + o1.getTimestamp());
        System.out.println(("next event time:" + events.peek().getTimestamp()));
        System.out.println();*/
    }

    public Event nextEventPEC() {
        // TODO - implement PEC.nextEventPEC
        Event aux;
        aux = events.poll();

        if (aux != null) {
            //System.out.println("next event: "+ aux+ " from: "+ aux.getMe()+" at " +aux.getTimestamp() );
            aux.simulateEvent();

        }
        return aux;
    }

    public PriorityQueue<Event> getEvents() {
        return events;
    }
}