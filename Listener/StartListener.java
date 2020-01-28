package Listener;

import Gui.Gui;
import Main.Excel;
import Main.Konfiguration;
import Main.Rechner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class StartListener implements ActionListener {
    private Gui gui;

    public StartListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        boolean continueFlag = true;
        String selected = "";
        for (Enumeration<AbstractButton> buttons = gui.sheetListe.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                selected = button.getText();
            }
        }
        Excel excel = new Excel();
        Konfiguration konfiguration = new Konfiguration();
        konfiguration.setSheetIndex(excel.getSheetPosition(selected));
        konfiguration.persistSheetEinstellungen();
        // TODO: Das ausgewählte Sheet speichern bei Öffnen der Einstellungen (auch Pfadselektor?)
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
        if (continueFlag) {
            double endergebnis = Rechner.rechnen();
            JOptionPane.showMessageDialog(gui, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
