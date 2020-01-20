package Main;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class Rechner {

    private static int charZuExcelSpalte(char c) {
        return Character.getNumericValue(c) - 10;
    }

    public static double rechnen() throws IOException, InvalidFormatException {
        Konfiguration konfiguration = new Konfiguration();
        Excel mappe = new Excel();
        double ergebnis = 0.0;
        for (int i = 0; i < mappe.ExcelSheetListe[konfiguration.getSheetIndex()].getLastRowNum() + 1; i++) {
            double wert = mappe.lesen(i, charZuExcelSpalte(konfiguration.getWertSpalte()), konfiguration.getSheetIndex());
            int menge = (int) mappe.lesen(i, charZuExcelSpalte(konfiguration.getMengeSpalte()), konfiguration.getSheetIndex());
            Buchung buchung = new Buchung(menge, wert, konfiguration);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis / konfiguration.getStunden();
    }
}
