package Listener;

import Gui.Gui;
import Main.Konfiguration;
import Main.Rechner;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

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
            Konfiguration konfiguration = new Konfiguration();
            konfiguration.setPfad(gui.dateipfadTextfeld.getText());
            konfiguration.setStunden(Double.parseDouble(gui.arbeitszeitTextfeld.getText()));
            konfiguration.persistPfadEinstellungen();
            konfiguration.persistArbeitszeitEinstellungen();
            // Hier muss man von der Excel-Klasse wissen, welcher Index zu welchem Sheet-Namen gehört
            // TODO: Das aktuell ausgewählte Sheet speichern
            double endergebnis = Rechner.rechnen();
            JOptionPane.showMessageDialog(gui, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
