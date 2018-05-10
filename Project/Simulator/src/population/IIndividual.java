/**
* 
* IIndividual.java - An interface outlining the minimun required methods to be provided by an individual
* @author  Tiago Miotto
* @version 1.0 
* @see Individual.java
*/

package population;

import grid.Point;


public interface IIndividual {
	public void evolve(Point o1);
	public String getPathDesc();
}
