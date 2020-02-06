package Listener;

import Gui.Gui;
import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartListener implements ActionListener {
    private Gui gui;

    public StartListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: da der untere Teil doppelt ist, kann man das hier in Abhängigkeit einer bestimmten Gui ausführen?
        boolean continueFlag = true;
        Excel excel = new Excel();
        Konfiguration.setSheetPosition(excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui)));
        try {
            excel.wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            double arbeitszeit = Double.parseDouble(gui.arbeitszeitTextfeld.getText());
            if (arbeitszeit <= 0.0) {
                Validation.showNegativErrorMessage(gui);
                continueFlag = false;
            } else {
                Konfiguration.setArbeitszeit(Double.parseDouble(gui.arbeitszeitTextfeld.getText()));
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(gui);
            continueFlag = false;
        }
        gui.fillView();
        if (continueFlag) {
            double endergebnis = Rechner.rechnen();
            StringSelection stringSelection = new StringSelection(String.valueOf(endergebnis));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(gui, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
