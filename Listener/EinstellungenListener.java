package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Konfiguration konfiguration = new Konfiguration();
            char wertSpalte = Character.toUpperCase(guiEinstellungen.wertTextfeld.getText().charAt(0));
            char mengeSpalte = Character.toUpperCase(guiEinstellungen.mengeTextfeld.getText().charAt(0));
            // TODO: Fehler, falls Nutzer ungerade Anzahl an Koeffizienten eingibt
            konfiguration.setKoeffizientAnzahl(Integer.parseInt(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
            // TODO: Fehler, falls Nutzer hier 500.3 eingibt
            konfiguration.setMaximalMenge(Integer.parseInt(guiEinstellungen.maxMengeTextfeld.getText()));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
