package pec;

import simulator.SimulationVariables;

public class EventVariables extends SimulationVariables {
    //TODO - stores the simulation variables to be accessible by the events
    private double miu;
    private double p;
    private double delta;

    public double getMiu() {
        return miu;
    }

    public double getP() {
        return p;
    }

    public double getDelta() {
        return delta;
    }

    public EventVariables(double miu, double p, double delta, double v_max, double k) {
        this.miu = miu;
        this.p = p;
        this.delta = delta;
        super.setK(k);

        super.setV_max(v_max);
    }
}
