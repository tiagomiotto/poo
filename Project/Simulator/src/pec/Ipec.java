package pec;

/**
 * Ipec.java - An interface outlining the minimun required methods to be provided by the PEC
 *
 * @author Tiago Miotto
 * @version 1.0
 */
public interface Ipec {

	/**
	 * Method to add events to the PEC
	 *
	 * @param o1, the event to be added
	 */
	void addEventPEC(Event o1);

	/**
	 * Method get the next event to be simulated and removes it from the PEC
	 */
	Event nextEventPEC();

}