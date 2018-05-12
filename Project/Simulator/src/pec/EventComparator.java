package pec;

import java.util.Comparator;

/**
 * EventComparator.java - A class implementing the Generic Comparator class, exclusively to compare Events by their current
 * timestamp. Used for sorting queues
 *
 * @author Tiago Miotto
 * @version 1.0
 */

class EventComparator implements Comparator<Event> {

    /**
     * Implementation of the Comparator method, returning 0,1 or -1, depending if the timestamp of o1=o2,o1 more than o2,o1 less than o2
     *
     * @param o1 Individual to be compared
     * @return Integer
     */
    @Override
    public int compare(Event o1, Event o2) {
        return Double.compare(o1.getTimestamp(), o2.getTimestamp());
    }

}