package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;

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
        // TODO: Überprüfung, ob Spalten innerhalb A-Z bzw. später AA - ZZ
        if (wertSpalte == mengeSpalte) {
            JOptionPane.showMessageDialog(guiEinstellungen,
                    "Menge und Wert können bisher noch nicht in der selben Spalte sein!",
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
