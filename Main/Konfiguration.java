package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Konfiguration {
    public static final String Basispfad = "C:\\antonfries\\projects\\llr\\";
    public static final String Konfigurationpfad = Basispfad + "config\\";
    public static final String Dateipfad = Basispfad + "files\\";
    public static final String Mappe = Dateipfad + "Testmappe1.xlsx";
    public static final String BasisListe = Konfigurationpfad + "Basis-Einstellungen.txt";
    public static final String Pfad = Konfigurationpfad + "Pfad-Einstellung.txt";
    public static final String Arbeitszeit = Konfigurationpfad + "Arbeitszeit-Einstellung.txt";
    public static final String KoeffizientListe = Konfigurationpfad + "Koeffizienten-Einstellungen.txt";
    public static final String GrenzeListe = Konfigurationpfad + "Grenzen-Einstellungen.txt";
    public static final String SheetListe = Konfigurationpfad + "Sheet-Einstellungen.txt";
    public double[] grenzeListe;
    public double[] koeffizientListe;
    private String pfad = Mappe;
    private int minimalMenge = 5;
    private int standardMenge = 10;
    private int maximalMenge = 500;
    private double stunden = 10.0;
    private double standardKoeffizient = 2.0;
    private int koeffizientAnzahl = 5;
    private char wertSpalte = 'A';
    private char mengeSpalte = 'B';
    private int sheetIndex = 0;
    private Regler[] reglerListe;

    public Konfiguration() {
        try {
            File KonfigurationpfadDatei = new File(Konfigurationpfad);
            if (!KonfigurationpfadDatei.exists()) {
                boolean Ergebnis = KonfigurationpfadDatei.mkdirs();
                if (!Ergebnis) {
                    throw new RuntimeException("Der Basispfad für die Einstellungen konnte nicht erzeugt werden!");
                }
            }
            initPfadEinstellungen();
            initArbeitszeitEinstellungen();
            initEinstellungen();
            initSheetEinstellungen();
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
        if (new File(tempPfad).exists()) {
            setPfad(tempPfad);
        } else {
            setPfad("");
        }
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
            setWertSpalte(einstellungListe[1].charAt(0));
        }
        if (!einstellungListe[2].equals("")) {
            setMengeSpalte(einstellungListe[2].charAt(0));
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

    public void initSheetEinstellungen() throws IOException {
        File SheetEinstellungenDatei = new File(SheetListe);
        boolean Ergebnis = SheetEinstellungenDatei.createNewFile();
        if (!Ergebnis && !SheetEinstellungenDatei.exists()) {
            throw new RuntimeException("Einstellungen können nicht gespeichert werden!");
        }
        int einstellungAnzahl = 1;
        String[] sheetEinstellungListe = new String[einstellungAnzahl];
        Arrays.fill(sheetEinstellungListe, "");
        Scanner sheetEinstellungStream = new Scanner(SheetEinstellungenDatei);
        for (int i = 0; i < einstellungAnzahl; i++) {
            if (sheetEinstellungStream.hasNextLine()) {
                sheetEinstellungListe[i] = sheetEinstellungStream.nextLine();
            }
        }
        if (!sheetEinstellungListe[0].equals("")) {
            setSheetIndex(Integer.parseInt(sheetEinstellungListe[0]));
        }
        sheetEinstellungStream.close();
    }

    public void persistSheetEinstellungen() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(new File(SheetListe)));
        pw.println(getSheetIndex());
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
        for (int i = 0; i < getKoeffizientAnzahl() + 1; i++) {
            if (grenzeStream.hasNextLine()) {
                grenzeListe[i] = Double.parseDouble(grenzeStream.nextLine());
            }
            if (i > 0 && grenzeListe[i] < grenzeListe[i - 1]) {
                grenzeListe[i] = grenzeListe[i - 1] + 0.01;
            }
            grenzeListe[i] = (double) Math.round(grenzeListe[i] * 100d) / 100d;
        }

        for (int i = 0; i < getKoeffizientAnzahl(); i++) {
            if (koeffizientStream.hasNextLine()) {
                koeffizientListe[i] = Double.parseDouble(koeffizientStream.nextLine());
            }
        }
        grenzeStream.close();
        koeffizientStream.close();
        umwandelnReglerListe();
    }

    public void persistGrenzenKoeffizientenEinstellungen() throws IOException {
        PrintWriter grenzenPw = new PrintWriter(new FileWriter(new File(GrenzeListe)));
        PrintWriter koeffizientenPw = new PrintWriter(new FileWriter(new File(KoeffizientListe)));
        for (double grenze : grenzeListe) {
            grenzenPw.println(grenze);
        }
        for (double koeffizient : koeffizientListe) {
            koeffizientenPw.println(koeffizient);
        }
        grenzenPw.flush();
        grenzenPw.close();
        koeffizientenPw.flush();
        koeffizientenPw.close();
    }

    public void umwandelnReglerListe() {
        setReglerListe(new Regler[getKoeffizientAnzahl()]);
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

    public Regler[] getReglerListe() {
        return reglerListe;
    }

    public void setReglerListe(Regler[] reglerListe) {
        this.reglerListe = reglerListe;
    }

    public char getWertSpalte() {
        return wertSpalte;
    }

    public void setWertSpalte(char wertSpalte) {
        this.wertSpalte = wertSpalte;
    }

    public char getMengeSpalte() {
        return mengeSpalte;
    }

    public void setMengeSpalte(char mengeSpalte) {
        this.mengeSpalte = mengeSpalte;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
