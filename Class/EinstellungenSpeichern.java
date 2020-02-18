package Class;

import Gui.GuiEinstellungen;
import Main.Konfiguration;
import Main.Utility;
import Main.Validation;

public class EinstellungenSpeichern {
    public EinstellungenSpeichern(GuiEinstellungen guiEinstellungen) {
        boolean persistMenge = false;
        boolean persistWert = false;
        try {
            int koeffizientAnzahl = (int) Utility.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText());
            if (koeffizientAnzahl < 0 || koeffizientAnzahl > Konfiguration.MAXIMAL_KOEFFIZIENT_ANZAHL) {
                Validation.showKoeffizientErrorMessage(guiEinstellungen);
            } else {
                Utility.removeOldGrenzen(koeffizientAnzahl);
                Konfiguration.setKoeffizientAnzahl(koeffizientAnzahl);
                Utility.adjustGrenzen();
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.KOEFFIZIENT_ANZAHL);
        }
        String wertSpalte = guiEinstellungen.wertTextfeld.getText().toUpperCase();
        if (checkValidity(wertSpalte)) {
            persistWert = true;
        } else {
            Validation.showSpaltenErrorMessage(guiEinstellungen, Konfiguration.WERT);
        }
        String mengeSpalte = guiEinstellungen.mengeTextfeld.getText().toUpperCase();
        if (checkValidity(mengeSpalte)) {
            persistMenge = true;
        } else {
            Validation.showSpaltenErrorMessage(guiEinstellungen, Konfiguration.MENGE);
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
        boolean persistStandardMenge = false;
        boolean persistMaximalMenge = false;
        try {
            standardMenge = Utility.parseDouble(guiEinstellungen.standardMengeTextfeld.getText());
            if (standardMenge <= 0.0) {
                Validation.showNegativErrorMessage(guiEinstellungen, Konfiguration.STANDARD_MENGE);
            } else {
                persistStandardMenge = true;
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.STANDARD_MENGE);
        }
        try {
            maximalMenge = Utility.parseDouble(guiEinstellungen.maxMengeTextfeld.getText());
            maximalMenge = Math.max(-1.0, maximalMenge);
            if (maximalMenge <= 0.0 && maximalMenge != -1) {
                Validation.showNegativOrUnlimitedErrorMessage(guiEinstellungen, Konfiguration.MAXIMAL_MENGE);
            } else {
                persistMaximalMenge = true;
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.MAXIMAL_MENGE);
        }
        if (standardMenge > maximalMenge && maximalMenge != -1) {
            if (persistStandardMenge && persistMaximalMenge) {
                Validation.showMaximalErrorMessage(guiEinstellungen);
            }
        } else {
            if (persistStandardMenge) {
                Konfiguration.setStandardMenge(standardMenge);
            }
            if (persistMaximalMenge) {
                Konfiguration.setMaximalMenge(maximalMenge);
            }
        }
        guiEinstellungen.standardMengeFrage.setToolTipText(
                Konfiguration.getMaximalMenge() == 1
                        ? "Hat aktuell keine Auswirkung, da Maximal-Menge -1" : "");
        try {
            double buchungKoeffizient = Utility.parseDouble(guiEinstellungen.buchungKoeffizientTextfeld.getText());
            if (buchungKoeffizient <= 0.0) {
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
            // TODO: -0.5 bei Zeilenende und maximaler Grenze gescheite Validierung implementieren
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
