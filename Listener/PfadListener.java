package Listener;

import Gui.Gui;
import Main.Excel;
import Main.ExcelFileChecker;
import Main.Konfiguration;
import Main.SheetHelper;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;

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
        boolean ordentlicheExcelDatei = ExcelFileChecker.checkExcelFile(dateiPfad, gui);
        gui.startButton.setEnabled(ordentlicheExcelDatei);
        // Bugfix: Falls Gui erneut gefüllt wird, macht es keinen Sinn, die Sheet-Position neu zu berechnen
        if (ordentlicheExcelDatei && !Konfiguration.getDateiPfad().equals(dateiPfad)) {
            Excel excel = new Excel();
            int sheetPosition = excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui));
            if (sheetPosition != -1) {
                Konfiguration.setSheetPosition(sheetPosition);
            }
            try {
                excel.wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Konfiguration.setDateiPfad(dateiPfad);
            new SheetSelektor(gui);
        }
    }
}
