package simulator;

public abstract class SimulationVariables {
    protected double k;            //confortsens
    protected double v_max;        //maxpop
    protected double c_max;        //finalinst
    protected double v_init;    //initpop


    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }


    public double getV_init() {
        return v_init;
    }

    public void setV_init(double v_init) {
        this.v_init = v_init;
    }

    public double getV_max() {
        return v_max;
    }

    public void setV_max(double v_max) {
        this.v_max = v_max;
    }

    public double getC_max() {
        return c_max;
    }

    public void setC_max(double c_max) {
        this.c_max = c_max;
    }
}
