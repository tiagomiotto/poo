package pec_simulator;

public abstract class SimulationVariables {
    protected double k;
    protected double v_max;
    protected double c_max;

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
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
