package Main;

public class Regler {
    private double min = 0.0;
    private double max = 0.0;
    private double koeffizient = 0.0;

    public Regler(double min, double max, double koeffizient) {
        setMin(min);
        setMax(max);
        setKoeffizient(koeffizient);
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getKoeffizient() {
        return koeffizient;
    }

    public void setKoeffizient(double koeffizient) {
        this.koeffizient = koeffizient;
    }
}
