package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;
import Main.Rechner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Konfiguration konfiguration = new Konfiguration();
        char wertSpalte = Character.toUpperCase(guiEinstellungen.wertTextfeld.getText().charAt(0));
        char mengeSpalte = Character.toUpperCase(guiEinstellungen.mengeTextfeld.getText().charAt(0));
        konfiguration.setKoeffizientAnzahl((int) Double.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
        konfiguration.setMaximalMenge((int) Double.parseDouble(guiEinstellungen.maxMengeTextfeld.getText()));
        konfiguration.setBuchungKoeffizient((int) Double.parseDouble(guiEinstellungen.buchungKoeffizientTextfeld.getText()));
        int wertNumerisch = Rechner.charZuExcelSpalte(wertSpalte);
        int mengeNumerisch = Rechner.charZuExcelSpalte(mengeSpalte);
        if (wertSpalte == mengeSpalte) {
            JOptionPane.showMessageDialog(guiEinstellungen,
                    "Menge und Wert können bisher noch nicht in der selben Spalte sein!",
                    "Excel-Spalten-Validation",
                    JOptionPane.ERROR_MESSAGE);
        } else if (wertNumerisch < 0 || wertNumerisch > 25 || mengeNumerisch < 0 || mengeNumerisch > 25) {
            JOptionPane.showMessageDialog(guiEinstellungen,
                    "Bitte geben Sie einen Buchstaben von A-Z an!",
                    "Excel-Spalten-Validation",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            konfiguration.setWertSpalte(wertSpalte);
            konfiguration.setMengeSpalte(mengeSpalte);
        }
        konfiguration.persistEinstellungen();
        guiEinstellungen.fillView();
    }
}
