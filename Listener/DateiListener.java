package Listener;

import Gui.Gui;
import Main.Excel;
import Main.ExcelFileChecker;
import Main.Konfiguration;
import Main.SheetHelper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateiListener implements ActionListener {
    private Gui gui;

    public DateiListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser("C:\\antonfries\\projects\\llr\\files");
        // TODO: Nach Entwicklung auf Home-Verzeichnis legen
        FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
        jFileChooser.setFileFilter(excelFilter);
        int r = jFileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            String dateiPfad = jFileChooser.getSelectedFile().getAbsolutePath();
            boolean ordentlicheExcelDatei = ExcelFileChecker.checkExcelFile(dateiPfad);
            if (ordentlicheExcelDatei) {
                Excel excel = new Excel();
                Konfiguration.setSheetPosition(excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui)));
                Konfiguration.setDateiPfad(dateiPfad);
                gui.fillView();
            }
        }
    }
}
