package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;
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
            int koeffizientAnzahl = (int) Double.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText());
            if (koeffizientAnzahl < 1) {
                Validation.showKoeffizientErrorMessage(guiEinstellungen);
            } else {
                Konfiguration.setKoeffizientAnzahl(koeffizientAnzahl);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
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
            double maximalMenge = Double.parseDouble(guiEinstellungen.maxMengeTextfeld.getText());
            if (maximalMenge < Konfiguration.getMinimalMenge()) {
                Validation.showMengeErrorMessage(guiEinstellungen);
            } else {
                Konfiguration.setMaximalMenge(maximalMenge);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
        }
        try {
            double buchungKoeffizient = Double.parseDouble(guiEinstellungen.buchungKoeffizientTextfeld.getText());
            if (buchungKoeffizient < 0.0) {
                Validation.showNegativErrorMessage(guiEinstellungen);
            } else {
                Konfiguration.setBuchungKoeffizient(buchungKoeffizient);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
        }
        int zeileAnfang = 1;
        try {
            zeileAnfang = (int) Double.parseDouble(guiEinstellungen.zeileAnfangTextfeld.getText());
            if (zeileAnfang < 1) {
                zeileAnfang = 1;
            }
            Konfiguration.setZeileAnfang(zeileAnfang);
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
        }
        try {
            int zeileEnde = (int) Double.parseDouble(guiEinstellungen.zeileEndeTextfeld.getText());
            if (zeileEnde < 1) {
                zeileEnde = -1;
            }
            if (zeileEnde < zeileAnfang) {
                Validation.showZeileErrorMessage(guiEinstellungen);
            } else {
                Konfiguration.setZeileEnde(zeileEnde);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
        }
        guiEinstellungen.fillView();
    }
}
