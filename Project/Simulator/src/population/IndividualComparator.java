package population;
import java.util.Comparator;

/**
 * IndividualComparator.java - A class implementing the Generic Comparator class, exclusively to compare Individuals by their current
 * comfort. Used for sorting queues
 *
 * @author Tiago Miotto
 * @version 1.0
 */

public class IndividualComparator implements Comparator<Individual> {


    /**
     * Implementation of the Comparator method, returning 0,1 or -1, depending if the comfort of o1=o2,o1>o2,o1<o2
     *
     * @param o1,o2, to be compared
     * @return Integer
     */
    @Override
    public int compare(Individual o1, Individual o2) {

        return -Double.compare(o1.getComfort(), o2.getComfort());
    }

}