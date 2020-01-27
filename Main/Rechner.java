package Main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Rechner {

    private static int charZuExcelSpalte(char c) {
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
        for (Row r : sheet) {
            Cell wertZelle = r.getCell(wertIndex);
            Cell mengeZelle = r.getCell(mengeIndex);
            if (wertZelle.getCellTypeEnum() == CellType.NUMERIC) {
                wert = wertZelle.getNumericCellValue();
            } else {
                wert = 0.0; // TODO: mehrere Spalten nach Werten/Mengen durchsuchen
            }
            if (mengeZelle.getCellTypeEnum() == CellType.NUMERIC) {
                menge = (int) mengeZelle.getNumericCellValue();
            } else {
                menge = 0;
            }
            Buchung buchung = new Buchung(menge, wert, konfiguration);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis / konfiguration.getStunden();
        // TODO: Ergebnis nochmal mit separatem Koeffizienten multiplizieren
    }
}
