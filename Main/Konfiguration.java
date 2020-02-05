package Main;

import java.util.prefs.Preferences;

public class Konfiguration {
    public static Preferences basisNode = Preferences.userRoot().node("Basis");
    public static Preferences grenzeNode = Preferences.userRoot().node("Grenzen");
    public static Preferences koeffizientNode = Preferences.userRoot().node("Koeffizienten");
    public static final String DATEI_PFAD = "Datei-Pfad";
    public static final String ARBEITSZEIT = "Arbeitszeit";
    public static final String SHEET_POSITION = "Sheet-Position";
    public static final String KOEFFIZIENT_ANZAHL = "Koeffizient-Anzahl";
    public static final String WERT_SPALTE = "Wert-Spalte";
    public static final String MENGE_SPALTE = "Menge-Spalte";
    public static final String MINIMAL_SUMMAND = "Minimal-Summand";
    public static final String STANDARD_SUMMAND = "Standard-Summand";
    public static final String MAXIMAL_SUMMAND = "Maximal-Summand";
    public static final String BUCHUNG_KOEFFIZIENT = "Buchung-Koeffizient";
    public static final String ZEILE_ANFANG = "Zeile-Anfang";
    public static final String ZEILE_ENDE = "Zeile-Ende";
    public static final String STANDARD_KOEFFIZIENT = "Standard-Koeffizient";

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
        return basisNode.get(DATEI_PFAD, Default.DATEI_PFAD);
    }

    public static void setDateiPfad(String dateiPfad) {
        basisNode.put(DATEI_PFAD, dateiPfad);
    }

    public static double getMinimalSummand() {
        return basisNode.getDouble(MINIMAL_SUMMAND, Default.MINIMAL_SUMMAND);
    }

    public static void setMinimalSummand(double minimalSummand) {
        basisNode.putDouble(MINIMAL_SUMMAND, minimalSummand);
    }

    public static double getStandardSummand() {
        return basisNode.getDouble(STANDARD_SUMMAND, Default.STANDARD_SUMMAND);
    }

    public static void setStandardSummand(double standardSummand) {
        basisNode.putDouble(STANDARD_SUMMAND, standardSummand);
    }

    public static double getMaximalSummand() {
        return basisNode.getDouble(MAXIMAL_SUMMAND, Default.MAXIMAL_SUMMAND);
    }

    public static void setMaximalSummand(double maximalSummand) {
        basisNode.putDouble(MAXIMAL_SUMMAND, maximalSummand);
    }

    public static double getArbeitszeit() {
        return basisNode.getDouble(ARBEITSZEIT, Default.ARBEITSZEIT);
    }

    public static void setArbeitszeit(double arbeitszeit) {
        basisNode.putDouble(ARBEITSZEIT, arbeitszeit);
    }

    public static double getStandardKoeffizient() {
        return basisNode.getDouble(STANDARD_KOEFFIZIENT, Default.STANDARD_KOEFFIZIENT);
    }

    public static void setStandardKoeffizient(double standardKoeffizient) {
        basisNode.putDouble(STANDARD_KOEFFIZIENT, standardKoeffizient);
    }

    public static int getKoeffizientAnzahl() {
        return basisNode.getInt(KOEFFIZIENT_ANZAHL, Default.KOEFFIZIENT_ANZAHL);
    }

    public static void setKoeffizientAnzahl(int koeffizientAnzahl) {
        basisNode.putInt(KOEFFIZIENT_ANZAHL, koeffizientAnzahl);
    }

    public static int getSheetPosition() {
        return basisNode.getInt(SHEET_POSITION, Default.SHEET_POSITION);
    }

    public static void setSheetPosition(int sheetPosition) {
        basisNode.putInt(SHEET_POSITION, sheetPosition);
    }

    public static double getBuchungKoeffizient() {
        return basisNode.getDouble(BUCHUNG_KOEFFIZIENT, Default.BUCHUNG_KOEFFIZIENT);
    }

    public static void setBuchungKoeffizient(double buchungKoeffizient) {
        basisNode.putDouble(BUCHUNG_KOEFFIZIENT, buchungKoeffizient);
    }

    public static String getWertSpalte() {
        return basisNode.get(WERT_SPALTE, Default.WERT_SPALTE);
    }

    public static void setWertSpalte(String wertSpalte) {
        basisNode.put(WERT_SPALTE, wertSpalte);
    }

    public static String getMengeSpalte() {
        return basisNode.get(MENGE_SPALTE, Default.MENGE_SPALTE);
    }

    public static void setMengeSpalte(String mengeSpalte) {
        basisNode.put(MENGE_SPALTE, mengeSpalte);
    }

    public static int getZeileAnfang() {
        return basisNode.getInt(ZEILE_ANFANG, Default.ZEILE_ANFANG);
    }

    public static void setZeileAnfang(int zeileAnfang) {
        basisNode.putInt(ZEILE_ANFANG, zeileAnfang);
    }

    public static int getZeileEnde() {
        return basisNode.getInt(ZEILE_ENDE, Default.ZEILE_ENDE);
    }

    public static void setZeileEnde(int zeileEnde) {
        basisNode.putInt(ZEILE_ENDE, zeileEnde);
    }
}
