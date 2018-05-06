package pec;


import population.Individual;

public abstract class Event {

    private Individual me;
    private double timestamp;
    private PEC ref_pec;

    public abstract void simulateEvent();

    public double getTimestamp() {
        return timestamp;
    }

    void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    //abstract double generateTimestamp();

    public Event(Individual me, double timestamp, PEC ref_pec) {
        this.me = me;
        this.timestamp = timestamp;
        this.ref_pec = ref_pec;
    }

    Individual getMe() {
        return me;
    }

    void setMe(Individual me) {
        this.me = me;
    }

    PEC getRef_pec() {
        return ref_pec;
    }


}