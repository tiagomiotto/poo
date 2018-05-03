package pec_simulator;

import java.util.Comparator;

class IndividualComparator implements Comparator<Individual> {
    @Override
    public int compare(Individual o1, Individual o2) {
        if (o1.getComfort() >= o2.getComfort()) return 0;
        else return -1;
    }

}