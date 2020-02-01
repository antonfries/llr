package Main;

public class Buchung {

    private int menge = 0;
    private double wert = 0.0;

    public Buchung(int menge, double wert) {
        this.setMenge(menge);
        this.setWert(wert);
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        if (menge < Konfiguration.getMinimalMenge() || menge > Konfiguration.getMaximalMenge()) {
            menge = Konfiguration.getStandardMenge();
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
