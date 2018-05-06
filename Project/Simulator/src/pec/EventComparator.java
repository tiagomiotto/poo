package pec;

import java.util.Comparator;

  class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        if(o1.getTimestamp()>=o2.getTimestamp()) return  0;
        else return -1;
    }

}
