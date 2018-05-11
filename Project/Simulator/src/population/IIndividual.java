
package population;

import grid.Point;

/**
 * IIndividual.java - An interface outlining the minimun required methods to be provided by an individual
 *
 * @author Tiago Miotto
 * @version 1.0
 */


public interface IIndividual {
    void evolve(Point o1);

    String getPathDesc();
}