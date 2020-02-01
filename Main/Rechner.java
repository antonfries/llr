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
        Excel mappe = new Excel();
        double ergebnis = 0.0;
        Sheet sheet = mappe.ExcelSheetListe[Konfiguration.getSheetPosition()];
        char[] wertSpalteListe = Konfiguration.getWertSpalte().toCharArray();
        char[] mengeSpalteListe = Konfiguration.getMengeSpalte().toCharArray();
        double einzelWert, wert = 0.0;
        int einzelMenge, menge = 0;
        int counter = 0;
        int min = Konfiguration.getZeileAnfang();
        int max = Konfiguration.getZeileEnde();
        for (Row r : sheet) {
            counter++; // TODO: Randfälle testen
            if (counter < min) {
                continue;
            }
            if (max != -1) {
                if (counter > max) {
                    break;
                }
            }
            for (char wertSpalte : wertSpalteListe) {
                Cell wertZelle = r.getCell(charZuExcelSpalte(wertSpalte));
                einzelWert = wertZelle != null && wertZelle.getCellTypeEnum() == CellType.NUMERIC
                        ? wertZelle.getNumericCellValue() : 0.0;
                if (einzelWert != 0.0) {
                    wert = einzelWert;
                }
            }
            for (char mengeSpalte : mengeSpalteListe) {
                Cell mengeZelle = r.getCell(charZuExcelSpalte(mengeSpalte));
                einzelMenge = mengeZelle != null && mengeZelle.getCellTypeEnum() == CellType.NUMERIC
                        ? (int) mengeZelle.getNumericCellValue() : 0;
                if (einzelMenge != 0) {
                    menge = einzelMenge;
                }
            }
            Buchung buchung = new Buchung(menge, wert);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis * Konfiguration.getBuchungKoeffizient() / Konfiguration.getArbeitszeit();
    }
}
