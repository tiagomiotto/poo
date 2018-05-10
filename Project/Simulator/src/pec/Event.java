package pec;
/**  
* Event.java - An abstract class outlining all the necessary attributes and methods
* shared by the Events simulated on the PEC. All the events in the PEC are required to extend this class.
* It contains the individual it is related to, the time in which it should be simulated, and a reference to the 
* PEC simulating it.
* @author  Tiago Miotto
* @version 1.0 
*/

import population.Individual;

public abstract class Event {

    private Individual me;
    private double timestamp;
    private PEC ref_pec;


    /**  
     * Method to be defined by each event, it outlines the behavior of the event
     * 
     */  
    public abstract void simulateEvent();
    
    /**  
     * Retrieve the value of the timestamp of the event
     * 
     */  
    public final double getTimestamp() {
        return timestamp;
    }
    /**  
     * Set the value of the timestamp of the event
     * 
     */  
    void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    //abstract double generateTimestamp();
    /**  
     * Constructor
     * 
     */  
    public Event(Individual me, double timestamp, PEC ref_pec) {
        this.me = me;
        this.timestamp = timestamp;
        this.ref_pec = ref_pec;
    }

    /**  
     * Retrieve the individual reference
     * 
     */  
    Individual getMe() {
        return me;
    }
    /**  
     * Sets the inidivual reference
     * 
     */  
    void setMe(Individual me) {
        this.me = me;
    }
    /**  
     * Retrieve the reference to the PEC
     * 
     */ 
    PEC getRef_pec() {
        return ref_pec;
    }


}