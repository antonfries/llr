package Listener;

import Gui.Gui;
import Main.ExcelFileChecker;
import Main.Konfiguration;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PfadListener implements DocumentListener {

    private Gui gui;

    public PfadListener(Gui gui) {
        this.gui = gui;
    }

    public void changedUpdate(DocumentEvent documentEvent) {
        aktualisiereStartButton();
    }

    public void insertUpdate(DocumentEvent documentEvent) {
        aktualisiereStartButton();
    }

    public void removeUpdate(DocumentEvent documentEvent) {
        aktualisiereStartButton();
    }

    private void aktualisiereStartButton() {
        String dateiPfad = gui.dateipfadTextfeld.getText();
        boolean ordentlicheExcelDatei = ExcelFileChecker.checkExcelFile(dateiPfad);
        gui.startButton.setEnabled(ordentlicheExcelDatei);
        if (ordentlicheExcelDatei) {
            Konfiguration.setDateiPfad(dateiPfad);
            new SheetSelektor(gui);
        }
    }


}
