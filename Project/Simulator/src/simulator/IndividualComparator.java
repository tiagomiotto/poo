package simulator;

import population.Individual;

import java.util.Comparator;

class IndividualComparator implements Comparator<Individual> {
    @Override

    public int compare(Individual o1, Individual o2) {

        return -Double.compare(o1.getComfort(), o2.getComfort());
    }

}