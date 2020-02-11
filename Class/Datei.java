package Class;

import Gui.Gui;
import Main.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class Datei {
    public Datei(Gui gui) {
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
            boolean ordentlicheExcelDatei = ExcelFileChecker.checkExcelFile(dateiPfad, gui);
            if (ordentlicheExcelDatei) {
                boolean konfigurationOrdentlicheExcelDatei = ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad(), gui);
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
                // TODO: Evaluation, ob Fehlermeldung angezeigt werden soll, Verbesserung oder ein Mix von beidem
                gui.fillView();
            } else {
                Validation.showSelectionErrorMessage(gui);
            }
        }
    }
}
