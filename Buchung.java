public class Buchung {

    private String bezeichnung = "";
    private int menge = 0;
    private double wert = 0.0;
    private Einstellungen einstellungen;

    public Buchung(int menge, double wert, Einstellungen einstellungen) {
        this.einstellungen = einstellungen;
        this.setMenge(menge);
        this.setWert(wert);
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        if (menge >= einstellungen.getMaximalMenge()) {
            menge = einstellungen.getStandardMenge();
        }
        this.menge = menge;
    }

    public double getWert() {
        return wert;
    }

    public void setWert(double wert) {
        this.wert = wert;
    }

    public double getKoeffizient() {
        Regler[] reglerListe = einstellungen.getReglerListe();
        for (int i = 0; i < reglerListe.length; i++) {
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
}
