package pec;

import population.Individual;

import java.util.Random;

public class Reproduction extends Event {

    public void simulateEvent() {
        

        Individual child = new Individual(super.getMe()); //Construtor  para reprodução

        //Add events
        super.getRef_pec().addEventPEC(new Reproduction(super.getMe(), super.getTimestamp(), getRef_pec()));

        //Adiciona primeiros eventos da child
        super.getRef_pec().addEventPEC(new Reproduction(child, super.getTimestamp(), getRef_pec()));
        super.getRef_pec().addEventPEC(new Move(child, super.getTimestamp(), getRef_pec()));
        super.getRef_pec().addEventPEC(new Death(child, super.getTimestamp(), getRef_pec()));

        //Update population
        getMe().getSimulator().getPopulation().add(child);

        //Test for epidemic if so add it to next event
        if (getMe().getSimulator().getPopulation().size() > getMe().getSimulator().getVariables().getV_max()) {
            System.out.println("Epidemic will occur now");
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
    }

}