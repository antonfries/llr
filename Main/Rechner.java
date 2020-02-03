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
        double wert, menge;
        int counter = 0;
        int min = Konfiguration.getZeileAnfang();
        int max = Konfiguration.getZeileEnde();
        for (Row r : sheet) {
            counter++;
            if (counter < min) {
                continue;
            }
            if (max != -1) {
                if (counter > max) {
                    break;
                }
            }
            wert = getEntitaet(wertSpalteListe, r);
            menge = getEntitaet(mengeSpalteListe, r);
            Buchung buchung = new Buchung(menge, wert);
            ergebnis += buchung.getProdukt();
        }
        // TODO: Anzahl der evaluierten Zellen durchgehen, um Fehlermeldung anzuzeigen
        return ergebnis * Konfiguration.getBuchungKoeffizient() / Konfiguration.getArbeitszeit();
    }

    private static double getEntitaet(char[] entitaetSpalteListe, Row r) {
        double einzelEntitaet, entitaet = 0.0;
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
