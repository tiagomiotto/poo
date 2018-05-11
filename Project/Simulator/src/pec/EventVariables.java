package pec;

import simulator.SimulationVariables;

/**
 * Death.java - A class extending the Simulation variables class in order to contain the
 * event specific variables
 *
 * @author Tiago Miotto
 * @version 1.0
 */
public class EventVariables extends SimulationVariables {

    private double miu;
    private double p;
    private double delta;

    /**
     * Retrieve Miu
     */
    public double getMiu() {
        return miu;
    }

    /**
     * Retrieve P
     */
    public double getP() {
        return p;
    }

    /**
     * Retrieve Delta
     */
    public double getDelta() {
        return delta;
    }

    /**
     * Set the Miu
     */
    public void setMiu(double miu) {
        this.miu = miu;
    }

    /**
     * Set the P
     */
    public void setP(double p) {
        this.p = p;
    }

    /**
     * Set the Delta
     */
    public void setDelta(double delta) {
        this.delta = delta;
    }

    /**
     * Constructor
     */
    public EventVariables(double miu, double p, double delta, double v_max, double k, double c_max, double v_init) {
        super(k, v_max, c_max, v_init);
        this.miu = miu;
        this.p = p;
        this.delta = delta;
    }

}
