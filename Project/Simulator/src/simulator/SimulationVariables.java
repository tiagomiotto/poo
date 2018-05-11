package simulator;

/**
 * SimulationVariables.java - A class containing the simulation level variables
 *
 * @author Tiago Miotto
 * @version 1.0
 */
public abstract class SimulationVariables {
    protected double k;            //confortsens
    protected double v_max;        //maxpop
    protected double c_max;        //finalinst
    protected double v_init;    //initpop

    /**
     * Retrieve k(confortsense)
     */
    public double getK() {
        return k;
    }

    /**
     * Set the k(confortsens)
     */
    public void setK(double k) {
        this.k = k;
    }

    /**
     * Retrieve the initial population size
     */
    public double getV_init() {
        return v_init;
    }

    /**
     * Set the initial population size
     */
    public void setV_init(double v_init) {
        this.v_init = v_init;
    }

    /**
     * Retrieve the maximum population size
     */
    public double getV_max() {
        return v_max;
    }

    /**
     * Set the maximum simulation size
     */
    public void setV_max(double v_max) {
        this.v_max = v_max;
    }

    /**
     * Retrieve the simulation end time
     */
    public double getC_max() {
        return c_max;
    }

    /**
     * Set the simulation end time
     */
    public void setC_max(double c_max) {
        this.c_max = c_max;
    }

    public SimulationVariables(double k, double v_max, double c_max, double v_init) {
        this.k = k;
        this.v_max = v_max;
        this.c_max = c_max;
        this.v_init = v_init;
    }
}
