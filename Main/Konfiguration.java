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
    // Spalten werden jetzt aneinander geklebt (PQ, RS)
    private String wertSpalte = "A"; // TODO: Spalten ab Z zulassen: AA,AB,AC...
    private String mengeSpalte = "B";
    private int sheetIndex = 0;
    private int buchungKoeffizient = 1;
    private Regler[] reglerListe;
    private int zeilenAnfang = 1;
    private int zeilenEnde = -1; // -1 bedeutet dass Einschränkung nicht berücksichtigt wird

    public Konfiguration() {
        File KonfigurationpfadDatei = new File(Konfigurationpfad);
        if (!KonfigurationpfadDatei.exists()) {
            boolean Ergebnis = KonfigurationpfadDatei.mkdirs();
            if (!Ergebnis) {
                throw new RuntimeException("Der Basispfad für die Einstellungen konnte nicht erzeugt werden!");
            }
        }
        initPfadEinstellungen();
        persistPfadEinstellungen();
        initArbeitszeitEinstellungen();
        initEinstellungen();
        initSheetEinstellungen();
        initGrenzenKoeffizientenEinstellungen();
    }

    public void initArbeitszeitEinstellungen() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistArbeitszeitEinstellungen() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(Arbeitszeit)));
            pw.println(getStunden());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPfadEinstellungen() {
        try {
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
            if (new File(tempPfad).exists()) { // Dieser Check beim Init erfordert eine Persistierung
                setPfad(tempPfad);
            } else {
                setPfad("");
            }
            pfadStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistPfadEinstellungen() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(Pfad)));
            pw.println(getPfad());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initEinstellungen() {
        try {
            File BasisEinstellungenDatei = new File(BasisListe);
            boolean Ergebnis = BasisEinstellungenDatei.createNewFile();
            if (!Ergebnis && !BasisEinstellungenDatei.exists()) {
                throw new RuntimeException("Einstellungen können nicht gespeichert werden!");
            }
            int einstellungAnzahl = 7;
            String[] einstellungListe = new String[einstellungAnzahl];
            Arrays.fill(einstellungListe, "");
            Scanner einstellungStream = new Scanner(BasisEinstellungenDatei);
            for (int i = 0; i < einstellungAnzahl; i++) {
                if (einstellungStream.hasNextLine()) {
                    einstellungListe[i] = einstellungStream.nextLine();
                }
            }
            if (!einstellungListe[0].equals("")) {
                setKoeffizientAnzahl((int) Double.parseDouble(einstellungListe[0]));
            }
            if (!einstellungListe[1].equals("")) {
                setWertSpalte(einstellungListe[1]); // TODO: Eingabevalidation implementieren
            }
            if (!einstellungListe[2].equals("")) {
                setMengeSpalte(einstellungListe[2]);
            }
            if (!einstellungListe[3].equals("")) {
                setMaximalMenge((int) Double.parseDouble(einstellungListe[3]));
            }
            if (!einstellungListe[4].equals("")) {
                setBuchungKoeffizient((int) Double.parseDouble(einstellungListe[4]));
            }
            if (!einstellungListe[5].equals("")) {
                setZeilenAnfang((int) Double.parseDouble(einstellungListe[5]));
            }
            if (!einstellungListe[6].equals("")) {
                setZeilenEnde((int) Double.parseDouble(einstellungListe[6]));
            }
            einstellungStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistEinstellungen() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(BasisListe)));
            pw.println(getKoeffizientAnzahl());
            pw.println(getWertSpalte());
            pw.println(getMengeSpalte());
            pw.println(getMaximalMenge());
            pw.println(getBuchungKoeffizient());
            pw.println(getZeilenAnfang());
            pw.println(getZeilenEnde());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initSheetEinstellungen() {
        try {
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
                setSheetIndex((int) Double.parseDouble(sheetEinstellungListe[0]));
            }
            sheetEinstellungStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistSheetEinstellungen() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(SheetListe)));
            pw.println(getSheetIndex());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initGrenzenKoeffizientenEinstellungen() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistGrenzenKoeffizientenEinstellungen() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getBuchungKoeffizient() {
        return buchungKoeffizient;
    }

    public void setBuchungKoeffizient(int buchungKoeffizient) {
        this.buchungKoeffizient = buchungKoeffizient;
    }

    public String getWertSpalte() {
        return wertSpalte;
    }

    public void setWertSpalte(String wertSpalte) {
        this.wertSpalte = wertSpalte;
    }

    public String getMengeSpalte() {
        return mengeSpalte;
    }

    public void setMengeSpalte(String mengeSpalte) {
        this.mengeSpalte = mengeSpalte;
    }

    public int getZeilenAnfang() {
        return zeilenAnfang;
    }

    public void setZeilenAnfang(int zeilenAnfang) {
        this.zeilenAnfang = zeilenAnfang;
    }

    public int getZeilenEnde() {
        return zeilenEnde;
    }

    public void setZeilenEnde(int zeilenEnde) {
        this.zeilenEnde = zeilenEnde;
    }
}
