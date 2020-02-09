package Listener;

import Gui.Gui;
import Main.Excel;
import Main.ExcelFileChecker;
import Main.Konfiguration;
import Main.SheetHelper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DateiListener implements ActionListener {
    private Gui gui;

    public DateiListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: Nach Entwicklung Home-Directory nutzen
        File currentDirectoryPath = FileSystemView.getFileSystemView().getHomeDirectory();
        String developerPath = "C:\\antonfries\\projects\\llr\\files";
        JFileChooser jFileChooser = new JFileChooser(developerPath);
        FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
        jFileChooser.setFileFilter(excelFilter);
        int r = jFileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            String dateiPfad = jFileChooser.getSelectedFile().getAbsolutePath();
            boolean ordentlicheExcelDatei = ExcelFileChecker.checkExcelFile(dateiPfad);
            if (ordentlicheExcelDatei) {
                Excel excel = new Excel(Konfiguration.getDateiPfad());
                Konfiguration.setDateiPfad(dateiPfad);
                int sheetPosition = excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui));
                if (sheetPosition != -1) {
                    Konfiguration.setSheetPosition(sheetPosition);
                }
                try {
                    excel.wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gui.fillView();
            }
        }
    }
}
