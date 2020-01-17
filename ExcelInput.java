import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelInput {
    private XSSFSheet ExcelSheet;

    public ExcelInput() throws IOException {
        InputStream ExcelDatei = new FileInputStream(Einstellungen.Mappe);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelDatei);
        ExcelSheet = wb.getSheetAt(0);
    }

    /**
     * @param i Zeile
     * @param j Spalte
     * @return double
     */
    public double lesen(int i, int j) {
        if ((ExcelSheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC)
                && (ExcelSheet.getRow(i).getCell(j).getNumericCellValue() != 0)) {
            return ExcelSheet.getRow(i).getCell(j).getNumericCellValue();
        } else {
            return 0.0;
        }
    }
}
