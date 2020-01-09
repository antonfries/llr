public class Einstellungen {
    public static final String Basispfad = "C://ilyabykov//Spaß//";
    public static final String Mappe = Basispfad + "Mappe1.xlsx";
    public static final String Einstellungen = Basispfad + "Einstellungen.txt";
    public static final String ArbeitszeitEinstellungen = Basispfad + "Arbeitszeit.txt";
    public static final String KoeffizientenEinstellungen = Basispfad + "KoeffEinst.txt";
    public static final String GrenzenEinstellungen = Basispfad + "GrenzenEinst.txt";
    private int maximaleMenge = 500;
    private double stunden = 10.0;
    private int standardMenge = 10;
    private int standardKoeffizient = 2;
    private int anzahlDerRegler = 20;
    private Regler[] reglerListe = new Regler[anzahlDerRegler];

    public double getStunden() {
        return stunden;
    }

    public void setStunden(double stunden) {
        this.stunden = stunden;
    }

    public Regler[] getReglerListe() {
        return reglerListe;
    }

    public void setReglerListe(Regler[] reglerListe) {
        this.reglerListe = reglerListe;
    }

    public void addRegler(Regler regler, int i) {
        this.reglerListe[i] = regler;
    }

    public int getMaximaleMenge() {
        return this.maximaleMenge;
    }

    public void setMaximaleMenge(int maximaleMenge) {
        this.maximaleMenge = maximaleMenge;
    }

    public int getStandardMenge() {
        return standardMenge;
    }

    public void setStandardMenge(int standardMenge) {
        this.standardMenge = standardMenge;
    }

    public int getStandardKoeffizient() {
        return standardKoeffizient;
    }

    public void setStandardKoeffizient(int standardKoeffizient) {
        this.standardKoeffizient = standardKoeffizient;
    }

    public int getAnzahlDerRegler() {
        return anzahlDerRegler;
    }

    public void setAnzahlDerRegler(int AnzahlRegler) {
        this.anzahlDerRegler = AnzahlRegler;
    }
}
