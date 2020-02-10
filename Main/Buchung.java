package Main;

public class Buchung {

    private double menge = 0.0;
    private double wert = 0.0;

    public Buchung(double menge, double wert) {
        this.setMenge(menge);
        this.setWert(wert);
    }

    public double getMenge() {
        return menge;
    }

    public void setMenge(double menge) {
        if (menge > Konfiguration.getMaximalMenge()) {
            menge = Konfiguration.getStandardMenge();
        }
        this.menge = menge;
    }

    public double getKoeffizient() {
        for (Regler regler : Konfiguration.getReglerListe()) {
            if (regler.getMin() <= getWert()) {
                if (regler.getMax() == -1) {
                    return regler.getKoeffizient();
                } else {
                    if (getWert() <= regler.getMax()) {
                        return regler.getKoeffizient();
                    }
                }
            }
        }
        return Konfiguration.getStandardKoeffizient();
    }

    private double getProdukt() {
        return getKoeffizient() * getMenge();
    }

    public double getSummand() {
        double summand = getProdukt();
        if (summand < Konfiguration.getMinimalSummand() && summand != 0.0) {
            summand = Konfiguration.getMinimalSummand();
        }
        System.out.format("Summand: %.2f\tKoeffizient:%.2f\tMenge: %.2f\tWert: %.2f\n", summand, getKoeffizient(), getMenge(), wert);
        return summand;
    }

    public double getWert() {
        return wert;
    }

    public void setWert(double wert) {
        this.wert = wert;
    }
}
