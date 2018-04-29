package pec_simulator;

public abstract class SimulationVariables {
    protected double k;
    protected double v;
    protected double v_max;

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getV_max() {
        return v_max;
    }

    public void setV_max(double v_max) {
        this.v_max = v_max;
    }
}
