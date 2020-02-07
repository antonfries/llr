package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;
import Main.Utility;
import Main.Validation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public static boolean checkDuplicates(String a, String b) {
        String g = a + b;
        for (int i = 0; i < g.length(); i++) {
            for (int j = i + 1; j < g.length(); j++) {
                if (g.charAt(i) == g.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkValidity(String s) {
        for (Character c : s.toCharArray()) {
            if (!isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isLetter(Character c) {
        int numericValue = Character.getNumericValue(c) - 10;
        return numericValue >= 0 && numericValue <= 25;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        boolean persistMenge = false;
        boolean persistWert = false;
        try {
            int koeffizientAnzahl = (int) Utility.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText());
            if (koeffizientAnzahl < 0) {
                Validation.showNegativErrorMessage(guiEinstellungen, Konfiguration.KOEFFIZIENT_ANZAHL);
            } else {
                Konfiguration.setKoeffizientAnzahl(koeffizientAnzahl);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.KOEFFIZIENT_ANZAHL);
        }
        String wertSpalte = guiEinstellungen.wertTextfeld.getText().toUpperCase();
        if (checkValidity(wertSpalte)) {
            persistWert = true;
        } else {
            Validation.showSpaltenErrorMessage(guiEinstellungen, "Wert");
        }
        String mengeSpalte = guiEinstellungen.mengeTextfeld.getText().toUpperCase();
        if (checkValidity(mengeSpalte)) {
            persistMenge = true;
        } else {
            Validation.showSpaltenErrorMessage(guiEinstellungen, "Menge");
        }
        if (checkDuplicates(mengeSpalte, wertSpalte)) {
            // TODO: Wenn in beiden Feldern ein Fehler auftritt, soll nicht behauptet werden, dass eine Spalte eingefüllt werden
            Validation.showDuplicateErrorMessage(guiEinstellungen);
        } else {
            if (persistMenge) {
                Konfiguration.setMengeSpalte(mengeSpalte);
            }
            if (persistWert) {
                Konfiguration.setWertSpalte(wertSpalte);
            }
        }
        try {
            double minimalSummand = Utility.parseDouble(guiEinstellungen.minSummandTextfeld.getText());
            Konfiguration.setMinimalSummand(minimalSummand);
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.MINIMAL_SUMMAND);
        }
        try {
            double standardSummand = Utility.parseDouble(guiEinstellungen.standardSummandTextfeld.getText());
            Konfiguration.setStandardSummand(standardSummand);
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.STANDARD_SUMMAND);
        }
        // TODO: [Prio] Hier Validierung der Summanden wieder implementieren
        try {
            double maximalSummand = Utility.parseDouble(guiEinstellungen.maxSummandTextfeld.getText());
            Konfiguration.setMaximalSummand(maximalSummand);
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.MAXIMAL_SUMMAND);
        }
        try {
            double buchungKoeffizient = Utility.parseDouble(guiEinstellungen.buchungKoeffizientTextfeld.getText());
            if (buchungKoeffizient < 0.0) {
                Validation.showNegativErrorMessage(guiEinstellungen, Konfiguration.BUCHUNG_KOEFFIZIENT);
            } else {
                Konfiguration.setBuchungKoeffizient(buchungKoeffizient);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.BUCHUNG_KOEFFIZIENT);
        }
        int zeileAnfang = 1;
        try {
            zeileAnfang = (int) Utility.parseDouble(guiEinstellungen.zeileAnfangTextfeld.getText());
            if (zeileAnfang < 1) {
                zeileAnfang = 1;
            }
            Konfiguration.setZeileAnfang(zeileAnfang);
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.ZEILE_ANFANG);
        }
        try {
            int zeileEnde = (int) Utility.parseDouble(guiEinstellungen.zeileEndeTextfeld.getText());
            if (zeileEnde < 1) {
                zeileEnde = -1;
            }
            if (zeileEnde < zeileAnfang && zeileEnde != -1) {
                Validation.showZeileErrorMessage(guiEinstellungen);
            } else {
                Konfiguration.setZeileEnde(zeileEnde);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.ZEILE_ENDE);
        }
        // TODO: [Prio] Beim Start des Rechners Fehlermeldung anzeigen, falls Zeilenanfang/Zeilenende größer als Nummer der Zeilen
        guiEinstellungen.fillView();
    }
}
