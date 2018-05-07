package pec;

import population.Individual;

import java.util.Random;

public class Reproduction extends Event {

    public void simulateEvent() {
        // TODO - implement Reproduction.simulateEvent
        System.out.println("reprod");
        Individual child = new Individual(getMe()); //Construtor  para reprodução
        //Add events
        getRef_pec().addEventPEC(new Reproduction(getMe(), this.getTimestamp(), getRef_pec()));

        //Adiciona primeiros eventos da child
        getRef_pec().addEventPEC(new Reproduction(child, this.getTimestamp(), getRef_pec()));
        getRef_pec().addEventPEC(new Move(child, this.getTimestamp(), getRef_pec()));
        getRef_pec().addEventPEC(new Death(child, this.getTimestamp(), getRef_pec()));

        //Update population
        getMe().getSimulator().getPopulation().add(child);

        //Test for epidemic if so add it to next event

        if (getMe().getSimulator().getPopulation().size() > getMe().getSimulator().getVariables().getV_max()) {
            getRef_pec().addEventPEC(new Epidemic(getMe(), getRef_pec()));
        }

    }


    private double generateTimestamp(double time) {
        Random rand = new Random();
        double lambda = (1 - Math.log(getMe().getComfort())) * getMe().getSimulator().getVariables().getP();
        return time + (-lambda * Math.log(Math.random()));
    }

    public Reproduction(Individual me, double timestamp, PEC ref_pec) {
        super(me, timestamp, ref_pec);
        super.setTimestamp(this.generateTimestamp(this.getTimestamp()));
        System.out.println("I will reproduce at: " + this.getTimestamp());
    }
}