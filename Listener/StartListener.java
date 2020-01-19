package Listener;

import Gui.Gui;
import Main.Einstellungen;
import Main.Rechner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartListener implements ActionListener {
    private Gui gui;

    public StartListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Einstellungen einstellungen = new Einstellungen();
            einstellungen.setPfad(gui.dateipfadTextfeld.getText());
            einstellungen.setStunden(Double.parseDouble(gui.arbeitszeitTextfeld.getText()));
            einstellungen.persistPfadEinstellungen();
            einstellungen.persistArbeitszeitEinstellungen();
            double endergebnis = Rechner.rechnen();
            JOptionPane.showMessageDialog(gui, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
