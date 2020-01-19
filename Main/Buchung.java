package Main;

public class Buchung {

    private int menge = 0;
    private double wert = 0.0;
    private Einstellungen einstellungen;

    public Buchung(int menge, double wert, Einstellungen einstellungen) {
        this.setEinstellungen(einstellungen);
        this.setMenge(menge);
        this.setWert(wert);
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        // TODO: minimale Menge berücksichtigen
        if (menge >= einstellungen.getMaximalMenge()) {
            menge = einstellungen.getStandardMenge();
        }
        this.menge = menge;
    }

    public double getKoeffizient() {
        Regler[] reglerListe = einstellungen.getReglerListe();
        for (int i = 0; i < reglerListe.length; i++) {
            // TODO: Überprüfen, inwiefern die Länge der Main.Regler-Liste herangezogen werden kann
            Regler regler = reglerListe[i];
            if (regler.getMin() <= this.wert && this.wert <= regler.getMax()) {
                return regler.getKoeffizient();
            }
        }
        return einstellungen.getStandardKoeffizient();
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

    public Einstellungen getEinstellungen() {
        return einstellungen;
    }

    public void setEinstellungen(Einstellungen einstellungen) {
        this.einstellungen = einstellungen;
    }
}
