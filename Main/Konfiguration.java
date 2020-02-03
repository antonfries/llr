package Main;

import java.util.prefs.Preferences;

public class Konfiguration {
    public static final String STANDARD_MAPPE = "C:\\antonfries\\projects\\llr\\files\\Testmappe1.xlsx";
    public static final Preferences basisNode = Preferences.userRoot().node("Basis");
    public static final Preferences grenzeNode = Preferences.userRoot().node("Grenzen");
    public static final Preferences koeffizientNode = Preferences.userRoot().node("Koeffizienten");
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
                    grenzeNode.getDouble(String.valueOf(i), 0.0),
                    grenzeNode.getDouble(String.valueOf(i + 1), 0.0),
                    koeffizientNode.getDouble(String.valueOf(i), getStandardKoeffizient())
            );
        }
        return reglerListe;
    }

    public static String getDateiPfad() {
        return basisNode.get(DATEI_PFAD, STANDARD_MAPPE);
    }

    public static void setDateiPfad(String dateiPfad) {
        basisNode.put(DATEI_PFAD, dateiPfad);
    }

    public static double getMinimalMenge() {
        return basisNode.getDouble(MINIMAL_MENGE, 5.0);
    }

    public static void setMinimalMenge(double minimalMenge) {
        basisNode.putDouble(MINIMAL_MENGE, minimalMenge);
    }

    public static double getStandardMenge() {
        return basisNode.getDouble(STANDARD_MENGE, 10.0);
    }

    public static void setStandardMenge(double standardMenge) {
        basisNode.putDouble(STANDARD_MENGE, standardMenge);
    }

    public static double getMaximalMenge() {
        return basisNode.getDouble(MAXIMAL_MENGE, 500.0);
    }

    public static void setMaximalMenge(double maximalMenge) {
        basisNode.putDouble(MAXIMAL_MENGE, maximalMenge);
    }

    public static double getArbeitszeit() {
        return basisNode.getDouble(ARBEITSZEIT, 10.0);
    }

    public static void setArbeitszeit(double arbeitszeit) {
        basisNode.putDouble(ARBEITSZEIT, arbeitszeit);
    }

    public static double getStandardKoeffizient() {
        return basisNode.getDouble(STANDARD_KOEFFIZIENT, 2.0);
    }

    public static void setStandardKoeffizient(double standardKoeffizient) {
        basisNode.putDouble(STANDARD_KOEFFIZIENT, standardKoeffizient);
    }

    public static int getKoeffizientAnzahl() {
        return basisNode.getInt(KOEFFIZIENT_ANZAHL, 5);
    }

    public static void setKoeffizientAnzahl(int koeffizientAnzahl) {
        basisNode.putInt(KOEFFIZIENT_ANZAHL, koeffizientAnzahl);
    }

    public static int getSheetPosition() {
        return basisNode.getInt(SHEET_POSITION, 0);
    }

    public static void setSheetPosition(int sheetPosition) {
        basisNode.putInt(SHEET_POSITION, sheetPosition);
    }

    public static double getBuchungKoeffizient() {
        return basisNode.getDouble(BUCHUNG_KOEFFIZIENT, 1.0);
    }

    public static void setBuchungKoeffizient(double buchungKoeffizient) {
        basisNode.putDouble(BUCHUNG_KOEFFIZIENT, buchungKoeffizient);
    }

    public static String getWertSpalte() {
        return basisNode.get(WERT_SPALTE, "A");
    }

    public static void setWertSpalte(String wertSpalte) {
        basisNode.put(WERT_SPALTE, wertSpalte);
    }

    public static String getMengeSpalte() {
        return basisNode.get(MENGE_SPALTE, "B");
    }

    public static void setMengeSpalte(String mengeSpalte) {
        basisNode.put(MENGE_SPALTE, mengeSpalte);
    }

    public static int getZeileAnfang() {
        return basisNode.getInt(ZEILE_ANFANG, 1);
    }

    public static void setZeileAnfang(int zeileAnfang) {
        basisNode.putInt(ZEILE_ANFANG, zeileAnfang);
    }

    public static int getZeileEnde() {
        // -1 bedeutet dass Einschränkung nicht berücksichtigt wird
        return basisNode.getInt(ZEILE_ENDE, -1);
    }

    public static void setZeileEnde(int zeileEnde) {
        basisNode.putInt(ZEILE_ENDE, zeileEnde);
    }
}
