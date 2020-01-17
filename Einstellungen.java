import java.io.*;
import java.util.Scanner;

public class Einstellungen {
    public static final String Basispfad = "C://ilyabykov//Spaß//";
    public static final String Mappe = Basispfad + "Testmappe.xlsx";
    public static final String Einstellungen = Basispfad + "Einstellungen.txt";
    public static final String PfadEinstellungen = Basispfad + "PfadEinstellungen.txt";
    public static final String ArbeitszeitEinstellungen = Basispfad + "ArbeitszeitEinstellungen.txt";
    public static final String KoeffizientenEinstellungen = Basispfad + "KoeffizientenEinstellungen.txt";
    public static final String GrenzenEinstellungen = Basispfad + "GrenzenEinstellungen.txt";
    private String pfad = Mappe;
    private int minimalMenge = 5;
    private int standardMenge = 10;
    private int maximalMenge = 500;
    private double stunden = 10.0;
    private double standardKoeffizient = 2.0; // TODO: Check Type
    private int koeffizientAnzahl = 5;
    private int wertSpalte = 0; // TODO: Refactoring auf A-Z
    private int mengeSpalte = 1;
    private Regler[] reglerListe;
    public double[] grenzeListe;
    public double[] koeffizientListe;

    public Einstellungen() {
        try {
            initPfadEinstellungen();
            initArbeitszeitEinstellungen();
            initEinstellungen();
            initGrenzenKoeffizientenEinstellungen();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initArbeitszeitEinstellungen() throws FileNotFoundException {
        Scanner arbeitszeitStream = new Scanner(new File(ArbeitszeitEinstellungen));
        double std = getStunden();
        while (arbeitszeitStream.hasNextLine()) {
            std = Double.parseDouble(arbeitszeitStream.nextLine());
        }
        setStunden(std);
        arbeitszeitStream.close();
    }

    public void persistArbeitszeitEinstellungen() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(ArbeitszeitEinstellungen)));
        pw.println(getStunden());
        pw.flush();
        pw.close();
    }

    public void initPfadEinstellungen() throws FileNotFoundException {
        Scanner pfadStream = new Scanner(new File(PfadEinstellungen));
        String tempPfad = getPfad();
        while (pfadStream.hasNextLine()) {
            tempPfad = pfadStream.nextLine();
        }
        setPfad(tempPfad);
        pfadStream.close();
    }

    public void persistPfadEinstellungen() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(PfadEinstellungen)));
        pw.println(getPfad());
        pw.flush();
        pw.close();
    }

    public void initEinstellungen() throws FileNotFoundException {
        int einstellungAnzahl = 4;
        String[] einstellungListe = new String[einstellungAnzahl];
        Scanner einstellungStream = new Scanner(new File(Einstellungen));
        for (int i = 0; i < einstellungAnzahl; i++) {
            einstellungListe[i] = einstellungStream.nextLine();
        }
        setKoeffizientAnzahl(Integer.parseInt(einstellungListe[0]));
        setWertSpalte(Integer.parseInt(einstellungListe[1]));
        setMengeSpalte(Integer.parseInt(einstellungListe[2]));
        setMaximalMenge(Integer.parseInt(einstellungListe[3]));
        einstellungStream.close();
    }

    public void persistEinstellungen() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(Einstellungen)));
        pw.println(getKoeffizientAnzahl());
        pw.println(getWertSpalte());
        pw.println(getMengeSpalte());
        pw.println(getMaximalMenge());
        pw.flush();
        pw.close();
    }

    public void initGrenzenKoeffizientenEinstellungen() throws FileNotFoundException {
        Scanner grenzeStream = new Scanner(new File(GrenzenEinstellungen));
        Scanner koeffizientStream = new Scanner(new File(KoeffizientenEinstellungen));
        grenzeListe = new double[getKoeffizientAnzahl() + 1];
        koeffizientListe = new double[getKoeffizientAnzahl()];
        for (int i = 0; i <= getKoeffizientAnzahl(); i++) {
            if (grenzeStream.hasNextLine()) {
                grenzeListe[i] = Double.parseDouble(grenzeStream.nextLine());
            }
            if (grenzeListe[i] == 0.0 && i > 0) {
                grenzeListe[i] = grenzeListe[i - 1] + 0.01;
                // TODO: Auf 2 Nachkommastellen runden
            }
        }
        for (int i = 0; i < getKoeffizientAnzahl(); i++) {
            if (koeffizientStream.hasNextLine()) {
                koeffizientListe[i] = Double.parseDouble(koeffizientStream.nextLine());
            }
            if (koeffizientListe[i] == 0.0){
                koeffizientListe[i] = getStandardKoeffizient();
            }
        }
        grenzeStream.close();
        koeffizientStream.close();
        umwandelnReglerListe();
    }

    public void persistGrenzenKoeffizientenEinstellungen() {
        //  TODO: Einstellungen hier persistieren
    }

    public void umwandelnReglerListe() {
        reglerListe = new Regler[getKoeffizientAnzahl()];
        for (int i = 0; i < getKoeffizientAnzahl(); i++) {
            addRegler(new Regler(
                    grenzeListe[i],
                    grenzeListe[i + 1],
                    koeffizientListe[i]
            ), i);
        }
    }

    public void addRegler(Regler regler, int i) {
        this.reglerListe[i] = regler;
    }

    public String getPfad() {
        return pfad;
    }

    public void setPfad(String pfad) {
        this.pfad = pfad;
    }

    public int getMinimalMenge() {
        return minimalMenge;
    }

    public void setMinimalMenge(int minimalMenge) {
        this.minimalMenge = minimalMenge;
    }

    public int getStandardMenge() {
        return standardMenge;
    }

    public void setStandardMenge(int standardMenge) {
        this.standardMenge = standardMenge;
    }

    public int getMaximalMenge() {
        return maximalMenge;
    }

    public void setMaximalMenge(int maximalMenge) {
        this.maximalMenge = maximalMenge;
    }

    public double getStunden() {
        return stunden;
    }

    public void setStunden(double stunden) {
        this.stunden = stunden;
    }

    public double getStandardKoeffizient() {
        return standardKoeffizient;
    }

    public void setStandardKoeffizient(double standardKoeffizient) {
        this.standardKoeffizient = standardKoeffizient;
    }

    public int getKoeffizientAnzahl() {
        return koeffizientAnzahl;
    }

    public void setKoeffizientAnzahl(int koeffizientAnzahl) {
        this.koeffizientAnzahl = koeffizientAnzahl;
    }

    public int getWertSpalte() {
        return wertSpalte;
    }

    public void setWertSpalte(int wertSpalte) {
        this.wertSpalte = wertSpalte;
    }

    public int getMengeSpalte() {
        return mengeSpalte;
    }

    public void setMengeSpalte(int mengeSpalte) {
        this.mengeSpalte = mengeSpalte;
    }

    public Regler[] getReglerListe() {
        return reglerListe;
    }

    public void setReglerListe(Regler[] reglerListe) {
        this.reglerListe = reglerListe;
    }
}
