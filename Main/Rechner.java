package Main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Rechner {

    public static int charZuExcelSpalte(char c) {
        return Character.getNumericValue(c) - 10;
    }

    public static double rechnen() {
        Konfiguration konfiguration = new Konfiguration();
        Excel mappe = new Excel();
        double ergebnis = 0.0;
        Sheet sheet = mappe.ExcelSheetListe[konfiguration.getSheetIndex()];
        int wertIndex = charZuExcelSpalte(konfiguration.getWertSpalte());
        int mengeIndex = charZuExcelSpalte(konfiguration.getMengeSpalte());
        double wert;
        int menge;
        int counter = 0;
        for (Row r : sheet) {
            counter++;
            System.out.println(counter);
            Cell wertZelle = r.getCell(wertIndex);
            Cell mengeZelle = r.getCell(mengeIndex);
            if (wertZelle == null || mengeZelle == null) {
                continue;
            }
            if (wertZelle.getCellTypeEnum() == CellType.NUMERIC) {
                wert = wertZelle.getNumericCellValue();
            } else {
                wert = 0.0;
            }
            if (mengeZelle.getCellTypeEnum() == CellType.NUMERIC) {
                menge = (int) mengeZelle.getNumericCellValue();
            } else {
                menge = 0;
            }
            Buchung buchung = new Buchung(menge, wert, konfiguration);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis * konfiguration.getBuchungKoeffizient() / konfiguration.getStunden();
    }
}
