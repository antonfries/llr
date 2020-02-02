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
        if (menge < Konfiguration.getMinimalMenge()) {
            menge = 0.0;
        }
        this.menge = menge;
    }

    public double getKoeffizient() {
        for (Regler regler : Konfiguration.getReglerListe()) {
            if (regler.getMin() <= getWert() && getWert() <= regler.getMax()) {
                return regler.getKoeffizient();
            }
        }
        return Konfiguration.getStandardKoeffizient();
    }

    public double getProdukt() {
        return getKoeffizient() * getMenge();
    }

    public double getWert() {
        return wert;
    }

    public void setWert(double wert) {
        this.wert = wert;
    }
}
