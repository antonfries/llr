package Main;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;

public class Excel {
    public Sheet[] ExcelSheetListe;

    public Excel() {
        Konfiguration konfiguration = new Konfiguration();
        File ExcelDatei = new File(konfiguration.getPfad());
        Workbook wb = new StreamingReader.Builder()
                .rowCacheSize(100)
                .bufferSize(4096)
                .open(ExcelDatei);
        ExcelSheetListe = new Sheet[wb.getNumberOfSheets()];
        for (int i = 0; i < ExcelSheetListe.length; i++) {
            ExcelSheetListe[i] = wb.getSheetAt(i);
        }
    }

    public int getSheetPosition(String sheetName) {
        for (int i = 0; i < ExcelSheetListe.length; i++) {
            if (ExcelSheetListe[i].getSheetName().equals(sheetName)) {
                return i;
            }
        }
        return 0;
    }
}
