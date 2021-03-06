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
        if (menge > Konfiguration.getMaximalMenge() && Konfiguration.getMaximalMenge() != -1) {
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
        return Konfiguration.STANDARD_KOEFFIZIENT;
    }

    private double getProdukt() {
        return getKoeffizient() * getMenge();
    }

    public double getSummand() {
        double summand = getProdukt();
        if (summand < Konfiguration.getMinimalSummand() && summand != 0.0) {
            summand = Konfiguration.getMinimalSummand();
        }
        return summand;
    }

    public double getWert() {
        return wert;
    }

    public void setWert(double wert) {
        this.wert = wert;
    }
}
