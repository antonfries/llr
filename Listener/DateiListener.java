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
            // Hier muss sowohl der Konfigurationspfad als auch der ausgewählte Pfad überprüft werden
            boolean ordentlicheExcelDatei = ExcelFileChecker.checkExcelFile(dateiPfad);
            if (ordentlicheExcelDatei) {
                boolean konfigurationOrdentlicheExcelDatei = ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad());
                if (konfigurationOrdentlicheExcelDatei) {
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
                }
                Konfiguration.setDateiPfad(dateiPfad);
                // TODO: Fehlermeldung anzeigen, falls Nutzer irgendeine Datei öffnet?
                // TODO: Fehlermeldung zu Strict OOXML anzeigen
                // TODO: Prominente Shortcuts binden (Strg+s, Enter, Escape)
                // TODO: Progressbar implementieren
                // TODO: Evaluation, ob Fehlermeldung angezeigt werden soll, Verbesserung oder ein Mix von beidem
                gui.fillView();
            }
        }
    }
}
