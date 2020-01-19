package Main;

public class Buchung {

    private int menge = 0;
    private double wert = 0.0;
    private Konfiguration konfiguration;

    public Buchung(int menge, double wert, Konfiguration konfiguration) {
        this.setKonfiguration(konfiguration);
        this.setMenge(menge);
        this.setWert(wert);
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        // TODO: minimale Menge berücksichtigen
        if (menge >= konfiguration.getMaximalMenge()) {
            menge = konfiguration.getStandardMenge();
        }
        this.menge = menge;
    }

    public double getKoeffizient() {
        Regler[] reglerListe = konfiguration.getReglerListe();
        for (int i = 0; i < reglerListe.length; i++) {
            // TODO: Überprüfen, inwiefern die Länge der Main.Regler-Liste herangezogen werden kann
            Regler regler = reglerListe[i];
            if (regler.getMin() <= this.wert && this.wert <= regler.getMax()) {
                return regler.getKoeffizient();
            }
        }
        return konfiguration.getStandardKoeffizient();
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

    public Konfiguration getKonfiguration() {
        return konfiguration;
    }

    public void setKonfiguration(Konfiguration konfiguration) {
        this.konfiguration = konfiguration;
    }
}
