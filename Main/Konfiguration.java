package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Konfiguration {
    public static final String Basispfad = "C://antonfries//projects//llr//";
    public static final String Konfigurationpfad = Basispfad + "config//";
    public static final String Mappe = Basispfad + "Testmappe.xlsx";
    public static final String BasisListe = Konfigurationpfad + "Basis-Einstellungen.txt";
    public static final String Pfad = Konfigurationpfad + "Pfad-Einstellung.txt";
    public static final String Arbeitszeit = Konfigurationpfad + "Arbeitszeit-Einstellung.txt";
    public static final String KoeffizientListe = Konfigurationpfad + "Koeffizienten-Einstellungen.txt";
    public static final String GrenzeListe = Konfigurationpfad + "Grenzen-Einstellungen.txt";
    public double[] grenzeListe;
    public double[] koeffizientListe;
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

    public Konfiguration() {
        try {
            initPfadEinstellungen();
            initArbeitszeitEinstellungen();
            initEinstellungen();
            initGrenzenKoeffizientenEinstellungen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initArbeitszeitEinstellungen() throws IOException {
        File ArbeitszeitDatei = new File(Arbeitszeit);
        boolean Ergebnis = ArbeitszeitDatei.createNewFile();
        if (!Ergebnis && !ArbeitszeitDatei.exists()) {
            throw new RuntimeException("Einstellungen können nicht gespeichert werden!");
        }
        Scanner arbeitszeitStream = new Scanner(ArbeitszeitDatei);
        double std = getStunden();
        if (arbeitszeitStream.hasNextLine()) {
            std = Double.parseDouble(arbeitszeitStream.nextLine());
        }
        setStunden(std);
        arbeitszeitStream.close();
    }

    public void persistArbeitszeitEinstellungen() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(Arbeitszeit)));
        pw.println(getStunden());
        pw.flush();
        pw.close();
    }

    public void initPfadEinstellungen() throws IOException {
        File PfadDatei = new File(Pfad);
        boolean Ergebnis = PfadDatei.createNewFile();
        if (!Ergebnis && !PfadDatei.exists()) {
            throw new RuntimeException("Einstellungen können nicht gespeichert werden!");
        }
        Scanner pfadStream = new Scanner(PfadDatei);
        String tempPfad = getPfad();
        if (pfadStream.hasNextLine()) {
            tempPfad = pfadStream.nextLine();
        }
        setPfad(tempPfad);
        pfadStream.close();
    }

    public void persistPfadEinstellungen() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(Pfad)));
        pw.println(getPfad());
        pw.flush();
        pw.close();
    }

    public void initEinstellungen() throws IOException {
        File BasisEinstellungenDatei = new File(BasisListe);
        boolean Ergebnis = BasisEinstellungenDatei.createNewFile();
        if (!Ergebnis && !BasisEinstellungenDatei.exists()) {
            throw new RuntimeException("Einstellungen können nicht gespeichert werden!");
        }
        int einstellungAnzahl = 4;
        String[] einstellungListe = new String[einstellungAnzahl];
        Arrays.fill(einstellungListe, "");
        Scanner einstellungStream = new Scanner(BasisEinstellungenDatei);
        for (int i = 0; i < einstellungAnzahl; i++) {
            if (einstellungStream.hasNextLine()) {
                einstellungListe[i] = einstellungStream.nextLine();
            }
        }
        if (!einstellungListe[0].equals("")) {
            setKoeffizientAnzahl(Integer.parseInt(einstellungListe[0]));
        }
        if (!einstellungListe[1].equals("")) {
            setWertSpalte(Integer.parseInt(einstellungListe[1]));
        }
        if (!einstellungListe[2].equals("")) {
            setMengeSpalte(Integer.parseInt(einstellungListe[2]));
        }
        if (!einstellungListe[3].equals("")) {
            setMaximalMenge(Integer.parseInt(einstellungListe[3]));
        }
        einstellungStream.close();
    }

    public void persistEinstellungen() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(BasisListe)));
        pw.println(getKoeffizientAnzahl());
        pw.println(getWertSpalte());
        pw.println(getMengeSpalte());
        pw.println(getMaximalMenge());
        pw.flush();
        pw.close();
    }

    public void initGrenzenKoeffizientenEinstellungen() throws IOException {
        File GrenzeDatei = new File(GrenzeListe);
        boolean Ergebnis = GrenzeDatei.createNewFile();
        if (!Ergebnis && !GrenzeDatei.exists()) {
            throw new RuntimeException("Einstellungen können nicht gespeichert werden!");
        }
        File KoeffizientDatei = new File(KoeffizientListe);
        Ergebnis = KoeffizientDatei.createNewFile();
        if (!Ergebnis && !KoeffizientDatei.exists()) {
            throw new RuntimeException("Einstellungen können nicht gespeichert werden!");
        }
        Scanner grenzeStream = new Scanner(GrenzeDatei);
        Scanner koeffizientStream = new Scanner(KoeffizientDatei);
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
            if (koeffizientListe[i] == 0.0) {
                koeffizientListe[i] = getStandardKoeffizient();
            }
        }
        grenzeStream.close();
        koeffizientStream.close();
        umwandelnReglerListe();
    }

    public void persistGrenzenKoeffizientenEinstellungen() throws IOException {
        PrintWriter grenzenPw = new PrintWriter(new FileWriter(new File(GrenzeListe)));
        PrintWriter koeffizientenPw = new PrintWriter(new FileWriter(new File(KoeffizientListe)));
        for (int i = 0; i < getKoeffizientAnzahl(); i++) {
            grenzenPw.println(grenzeListe[i]);
        }
        for (int j = 0; j < getKoeffizientAnzahl(); j++) {
            koeffizientenPw.println(koeffizientListe[j]);
        }
        grenzenPw.flush();
        grenzenPw.close();
        koeffizientenPw.flush();
        koeffizientenPw.close();
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
