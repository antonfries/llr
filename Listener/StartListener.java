package Listener;

import Gui.Gui;
import Main.Konfiguration;
import Main.Rechner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener {
    private Gui gui;

    public StartListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        boolean continueFlag = true;
        Konfiguration konfiguration = new Konfiguration();
        konfiguration.setPfad(gui.dateipfadTextfeld.getText());
        try {
            konfiguration.setStunden(Double.parseDouble(gui.arbeitszeitTextfeld.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(gui,
                    "Bitte geben Sie Zahlen ein!",
                    "Arbeitszeit-Validation",
                    JOptionPane.ERROR_MESSAGE);
            continueFlag = false;
        }
        konfiguration.persistPfadEinstellungen();
        konfiguration.persistArbeitszeitEinstellungen();
        gui.fillView();
        // TODO: Das aktuell ausgewählte Sheet speichern
        if (continueFlag) {
            double endergebnis = Rechner.rechnen();
            JOptionPane.showMessageDialog(gui, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
