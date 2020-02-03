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

    public void actionPerformed(ActionEvent actionEvent) {
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
        // TODO: Fehler anzeigen, falls ein Buchstabe in mehreren Textfeldern zugleich eingetragen wurde
        try {
            String wertSpalte = guiEinstellungen.wertTextfeld.getText();
            if (checkValidity(wertSpalte)) {
                Konfiguration.setWertSpalte(wertSpalte);
            } else {
                Validation.showSpaltenErrorMessage(guiEinstellungen, "Wert");
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
        }
        try {
            String mengeSpalte = guiEinstellungen.mengeTextfeld.getText();
            if (checkValidity(mengeSpalte)) {
                Konfiguration.setMengeSpalte(guiEinstellungen.mengeTextfeld.getText());
            } else {
                Validation.showSpaltenErrorMessage(guiEinstellungen, "Menge");
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
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
            zeileAnfang = 1;
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
