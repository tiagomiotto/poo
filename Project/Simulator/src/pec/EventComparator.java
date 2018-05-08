package pec;

import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        return Double.compare(o1.getTimestamp(), o2.getTimestamp());
    }

}
