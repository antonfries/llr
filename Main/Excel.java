package Main;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Excel {
    public XSSFSheet ExcelSheet;

    public Excel() throws IOException {
        InputStream ExcelDatei = new FileInputStream(Konfiguration.Mappe);
        // TODO: JFileChooser einbauen
        XSSFWorkbook wb = new XSSFWorkbook(ExcelDatei);
        ExcelSheet = wb.getSheetAt(0);
        // TODO: Angabe des Sheets in Hauptbildschirm
        // TODO: (optional) Angabe der Zeilen
    }

    /**
     * @param i Zeile
     * @param j Spalte
     * @return double
     */
    public double lesen(int i, int j) {
        XSSFCell cell = ExcelSheet.getRow(i).getCell(j);
        return cell.getNumericCellValue();
    }
}
