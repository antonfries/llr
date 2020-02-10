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
        if (persistMenge && persistWert) {
            if (checkDuplicates(mengeSpalte, wertSpalte)) {
                Validation.showDuplicateErrorMessage(guiEinstellungen);
            } else {
                Konfiguration.setMengeSpalte(mengeSpalte);
                Konfiguration.setWertSpalte(wertSpalte);
            }
        } else {
            if (persistMenge) {
                Konfiguration.setMengeSpalte(mengeSpalte);
            }
            if (persistWert) {
                Konfiguration.setWertSpalte(wertSpalte);
            }
        }
        double minimalSummand = Konfiguration.getMinimalSummand();
        double standardMenge = Konfiguration.getStandardMenge();
        double maximalMenge = Konfiguration.getMaximalMenge();
        // TODO: Validation von Summanden und Mengen wieder entkoppeln
        try {
            minimalSummand = Utility.parseDouble(guiEinstellungen.minSummandTextfeld.getText());
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.MINIMAL_SUMMAND);
        }
        try {
            standardMenge = Utility.parseDouble(guiEinstellungen.standardMengeTextfeld.getText());
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.STANDARD_MENGE);
        }
        try {
            maximalMenge = Utility.parseDouble(guiEinstellungen.maxMengeTextfeld.getText());
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.MAXIMAL_MENGE);
        }
        boolean showedMindestensOneError = false;
        if (minimalSummand > standardMenge) {
            Validation.showMinimalErrorMessage(guiEinstellungen);
            showedMindestensOneError = true;
        } else {
            Konfiguration.setMinimalSummand(minimalSummand);
        }
        if (maximalMenge < standardMenge) {
            Validation.showMaximalErrorMessage(guiEinstellungen);
            showedMindestensOneError = true;
        } else {
            Konfiguration.setMaximalMenge(maximalMenge);
        }
        if (standardMenge < minimalSummand || standardMenge > maximalMenge) {
            if (!showedMindestensOneError) {
                Validation.showStandardErrorMessage(guiEinstellungen);
            }
        } else {
            Konfiguration.setStandardMenge(standardMenge);
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
        int zeileAnfang = Konfiguration.getZeileAnfang();
        int zeileEnde = Konfiguration.getZeileEnde();
        try {
            zeileAnfang = (int) Utility.parseDouble(guiEinstellungen.zeileAnfangTextfeld.getText());
            if (zeileAnfang < 1) {
                zeileAnfang = 1;
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.ZEILE_ANFANG);
        }
        try {
            zeileEnde = (int) Utility.parseDouble(guiEinstellungen.zeileEndeTextfeld.getText());
            if (zeileEnde < 1) {
                zeileEnde = -1;
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.ZEILE_ENDE);
        }
        if (zeileEnde < zeileAnfang && zeileEnde != -1) {
            Validation.showZeileErrorMessage(guiEinstellungen);
        } else {
            Konfiguration.setZeileAnfang(zeileAnfang);
            Konfiguration.setZeileEnde(zeileEnde);
        }
        guiEinstellungen.fillView();
    }
}
