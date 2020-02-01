package Main;

import java.util.prefs.Preferences;

public class Konfiguration { // TODO: Mengen zu Double refactoren
    public static final String PROJEKT_PFAD = "C:\\antonfries\\projects\\llr\\";
    public static final String RESOURCEN_PFAD = PROJEKT_PFAD + "files\\";
    public static final String STANDARD_MAPPE = RESOURCEN_PFAD + "Testmappe1.xlsx";
    public static final Preferences userRoot = Preferences.userRoot();
    public static final Preferences grenzeRoot = Preferences.userRoot().node("Grenzen");
    public static final Preferences koeffizientRoot = Preferences.userRoot().node("Koeffizienten");
    public static final String WERT_SPALTE = "Wert-Spalte";
    public static final String MENGE_SPALTE = "Menge-Spalte";
    public static final String BUCHUNG_KOEFFIZIENT = "Buchung-Koeffizient";
    public static final String ZEILE_ANFANG = "Zeile-Anfang";
    public static final String ZEILE_ENDE = "Zeile-Ende";
    public static final String SHEET_POSITION = "Sheet-Position";
    public static final String KOEFFIZIENT_ANZAHL = "Koeffizient-Anzahl";
    public static final String STANDARD_KOEFFIZIENT = "Standard-Koeffizient";
    public static final String ARBEITSZEIT = "Arbeitszeit";
    public static final String MINIMAL_MENGE = "Minimal-Menge";
    public static final String MAXIMAL_MENGE = "Maximal-Menge";
    public static final String STANDARD_MENGE = "Standard-Menge";
    public static final String DATEI_PFAD = "Datei-Pfad";

    public static Regler[] getReglerListe() {
        Regler[] reglerListe = new Regler[getKoeffizientAnzahl()];
        for (int i = 0; i < getKoeffizientAnzahl(); i++) {
            reglerListe[i] = new Regler(
                    grenzeRoot.getDouble(String.valueOf(i), 0.0),
                    grenzeRoot.getDouble(String.valueOf(i + 1), 0.0),
                    koeffizientRoot.getDouble(String.valueOf(i), getStandardKoeffizient())
            );
        }
        return reglerListe;
    }

    public static String getDateiPfad() {
        return userRoot.get(DATEI_PFAD, STANDARD_MAPPE);
    }

    public static void setDateiPfad(String dateiPfad) {
        userRoot.put(DATEI_PFAD, dateiPfad);
    }

    public static int getMinimalMenge() {
        return userRoot.getInt(MINIMAL_MENGE, 5);
    }

    public static void setMinimalMenge(int minimalMenge) {
        userRoot.putInt(MINIMAL_MENGE, minimalMenge);
    }

    public static int getStandardMenge() {
        return userRoot.getInt(STANDARD_MENGE, 10);
    }

    public static void setStandardMenge(int standardMenge) {
        userRoot.putInt(STANDARD_MENGE, standardMenge);
    }

    public static int getMaximalMenge() {
        return userRoot.getInt(MAXIMAL_MENGE, 500);
    }

    public static void setMaximalMenge(int maximalMenge) {
        userRoot.putInt(MAXIMAL_MENGE, maximalMenge);
    }

    public static double getArbeitszeit() {
        return userRoot.getDouble(ARBEITSZEIT, 10.0);
    }

    public static void setArbeitszeit(double arbeitszeit) {
        userRoot.putDouble(ARBEITSZEIT, arbeitszeit);
    }

    public static double getStandardKoeffizient() {
        return userRoot.getDouble(STANDARD_KOEFFIZIENT, 2.0);
    }

    public static void setStandardKoeffizient(double standardKoeffizient) {
        userRoot.putDouble(STANDARD_KOEFFIZIENT, standardKoeffizient);
    }

    public static int getKoeffizientAnzahl() {
        return userRoot.getInt(KOEFFIZIENT_ANZAHL, 5);
    }

    public static void setKoeffizientAnzahl(int koeffizientAnzahl) {
        userRoot.putInt(KOEFFIZIENT_ANZAHL, koeffizientAnzahl);
    }

    public static int getSheetPosition() {
        return userRoot.getInt(SHEET_POSITION, 0);
    }

    public static void setSheetPosition(int sheetIndex) {
        userRoot.putInt(SHEET_POSITION, sheetIndex);
    }

    public static double getBuchungKoeffizient() {
        return userRoot.getDouble(BUCHUNG_KOEFFIZIENT, 1.0);
    }

    public static void setBuchungKoeffizient(double buchungKoeffizient) {
        userRoot.putDouble(BUCHUNG_KOEFFIZIENT, buchungKoeffizient);
    }

    public static String getWertSpalte() {
        // TODO: Spalten ab Z zulassen: AA,AB,AC...
        return userRoot.get(WERT_SPALTE, "A");
    }

    public static void setWertSpalte(String wertSpalte) {
        userRoot.put(WERT_SPALTE, wertSpalte);
    }

    public static String getMengeSpalte() {
        // TODO: Spalten mit Delimiter trennen und automatische Validierung des Delimiters
        return userRoot.get(MENGE_SPALTE, "B");
    }

    public static void setMengeSpalte(String mengeSpalte) {
        userRoot.put(MENGE_SPALTE, mengeSpalte);
    }

    public static int getZeileAnfang() {
        return userRoot.getInt(ZEILE_ANFANG, 1);
    }

    public static void setZeileAnfang(int zeilenAnfang) {
        userRoot.putInt(ZEILE_ANFANG, zeilenAnfang);
    }

    public static int getZeileEnde() {
        // -1 bedeutet dass Einschränkung nicht berücksichtigt wird
        return userRoot.getInt(ZEILE_ENDE, -1);
    }

    public static void setZeileEnde(int zeilenEnde) {
        userRoot.putInt(ZEILE_ENDE, zeilenEnde);
    }
}
