package Class;

import Gui.GuiEinstellungen;
import Main.Konfiguration;
import Main.Utility;
import Main.Validation;

public class Einstellungen {
    public Einstellungen(GuiEinstellungen guiEinstellungen) {
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
        double standardMenge = Konfiguration.getStandardMenge();
        double maximalMenge = Konfiguration.getMaximalMenge();
        try {
            double minimalSummand = Utility.parseDouble(guiEinstellungen.minSummandTextfeld.getText());
            if (minimalSummand < 0.0) {
                Validation.showNegativErrorMessage(guiEinstellungen, Konfiguration.MINIMAL_SUMMAND);
            } else {
                Konfiguration.setMinimalSummand(minimalSummand);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.MINIMAL_SUMMAND);
        }
        try {
            standardMenge = Utility.parseDouble(guiEinstellungen.standardMengeTextfeld.getText());
            if (standardMenge <= 0.0) {
                Validation.showNegativErrorMessage(guiEinstellungen, Konfiguration.STANDARD_MENGE);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.STANDARD_MENGE);
        }
        try {
            maximalMenge = Utility.parseDouble(guiEinstellungen.maxMengeTextfeld.getText());
            if (maximalMenge <= 0.0) {
                Validation.showNegativErrorMessage(guiEinstellungen, Konfiguration.MAXIMAL_MENGE);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.MAXIMAL_MENGE);
        }
        if (standardMenge > maximalMenge) {
            Validation.showMaximalErrorMessage(guiEinstellungen);
        } else {
            Konfiguration.setStandardMenge(standardMenge);
            Konfiguration.setMaximalMenge(maximalMenge);
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
            zeileAnfang = Math.max(1, zeileAnfang);
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.ZEILE_ANFANG);
        }
        try {
            zeileEnde = (int) Utility.parseDouble(guiEinstellungen.zeileEndeTextfeld.getText());
            zeileEnde = Math.max(-1, zeileEnde);
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
}
