package Main;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;

public class Excel {
    public Sheet[] ExcelSheetListe;
    public static final int BUFFER_SIZE = 4096;
    public static final int ROW_CACHE_SIZE = 100;
    public Workbook wb;

    public Excel() {
        File ExcelDatei = new File(Konfiguration.getDateiPfad());
        wb = new StreamingReader.Builder()
                .rowCacheSize(ROW_CACHE_SIZE)
                .bufferSize(BUFFER_SIZE)
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

    public void close() {

    }
}
