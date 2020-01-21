package Main;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Excel {
    public XSSFSheet[] ExcelSheetListe;

    public Excel() throws IOException, InvalidFormatException {
        // TODO: Singleton-Prinzip möglich, sodass mehrere Klassen Excel-Datei nicht immer wieder neu öffnen müssen?
        Konfiguration konfiguration = new Konfiguration();
        File ExcelDatei = new File(konfiguration.getPfad());
        XSSFWorkbook wb = new XSSFWorkbook(ExcelDatei);
        ExcelSheetListe = new XSSFSheet[wb.getNumberOfSheets()];
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            ExcelSheetListe[i] = wb.getSheetAt(i);
        }
        // TODO: (optional) Angabe der Zeilen
    }

    /**
     * @param i Zeile
     * @param j Spalte
     * @return double
     */
    public double lesen(int i, int j, int sheet) {
        double cellValue = 0.0;
        XSSFCell cell = ExcelSheetListe[sheet].getRow(i).getCell(j);
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            cellValue = cell.getNumericCellValue();
        }
        return cellValue;
    }
}
