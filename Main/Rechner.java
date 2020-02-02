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
        double wert = 0.0, menge = 0.0;
        int counter = 0;
        int min = Konfiguration.getZeileAnfang();
        int max = Konfiguration.getZeileEnde();
        for (Row r : sheet) {
            counter++; // TODO: Randfälle einmal durchtesten
            if (counter < min) {
                continue;
            }
            if (max != -1) {
                if (counter > max) {
                    break;
                }
            }
            wert = getEntitaet(wertSpalteListe, wert, r);
            menge = getEntitaet(mengeSpalteListe, menge, r);
            Buchung buchung = new Buchung(menge, wert);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis * Konfiguration.getBuchungKoeffizient() / Konfiguration.getArbeitszeit();
    }

    private static double getEntitaet(char[] entitaetSpalteListe, double entitaet, Row r) {
        double einzelEntitaet;
        for (char entitaetSpalte : entitaetSpalteListe) {
            Cell entitaetZelle = r.getCell(charZuExcelSpalte(entitaetSpalte));
            einzelEntitaet = entitaetZelle != null && entitaetZelle.getCellTypeEnum() == CellType.NUMERIC
                    ? entitaetZelle.getNumericCellValue() : 0.0;
            if (einzelEntitaet != 0.0) {
                entitaet = einzelEntitaet;
            }
        }
        return entitaet;
    }
}
