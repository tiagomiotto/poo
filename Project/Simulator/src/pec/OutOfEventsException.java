package pec;

public class OutOfEventsException extends Exception {


    @Override
    public String toString() {
        return "OutOfEventsException{" + " PEC ran out of events }";
    }
}
