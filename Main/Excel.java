package Main;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Excel {
    private XSSFSheet ExcelSheet;

    public Excel() throws IOException {
        InputStream ExcelDatei = new FileInputStream(Konfiguration.Mappe);
        // TODO: FileSelector einbauen
        XSSFWorkbook wb = new XSSFWorkbook(ExcelDatei);
        ExcelSheet = wb.getSheetAt(0);
        // TODO: Wie handelt man mehrere Sheets?
    }

    /**
     * @param i Zeile
     * @param j Spalte
     * @return double
     */
    public double lesen(int i, int j) {
        XSSFCell cell = ExcelSheet.getRow(i).getCell(j);
        if ((cell.getCellType() == CellType.NUMERIC)
                && (cell.getNumericCellValue() != 0)) {
            // TODO: Refactoring
            return cell.getNumericCellValue();
        } else {
            return 0.0;
        }
    }
}
