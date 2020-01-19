package Main;

import java.io.IOException;

public class Rechner {

    private static int charZuExcelSpalte(char c) {
        return Character.getNumericValue(c) - 10;
    }

    public static double rechnen() throws IOException {
        Konfiguration konfiguration = new Konfiguration();
        Excel mappe = new Excel();
        double ergebnis = 0.0;
        for (int i = 0; i < mappe.ExcelSheet.getLastRowNum() + 1; i++) {
            double wert = mappe.lesen(i, charZuExcelSpalte(konfiguration.getWertSpalte()));
            int menge = (int) mappe.lesen(i, charZuExcelSpalte(konfiguration.getMengeSpalte()));
            Buchung buchung = new Buchung(menge, wert, konfiguration);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis / konfiguration.getStunden();
    }
}
