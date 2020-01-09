import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelInput {
    /**
     * @param i Zeile
     * @param j Spalte
     * @return double
     * @throws IOException Standard
     */
    public static double Lesen(int i, int j) throws IOException {
        InputStream ExcelDatei = new FileInputStream(Einstellungen.Mappe);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelDatei);
        XSSFSheet ExcelSheet = wb.getSheetAt(0);

        if ((ExcelSheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC)
                && (ExcelSheet.getRow(i).getCell(j).getNumericCellValue() != 0)) {
            return ExcelSheet.getRow(i).getCell(j).getNumericCellValue();
        } else {
            return 0.0;
        }
    }
}
