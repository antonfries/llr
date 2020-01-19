package Main;

import java.io.IOException;

public class Rechner {

    public static double rechnen() throws IOException {
        Konfiguration konfiguration = new Konfiguration();
        Excel mappe = new Excel();
        double ergebnis = 0.0;
        // TODO: Algorithmus, bei dem Excel-Einlese-Operation aufhört
        for (int i = 0; i < 100; i++) {
            double wert = mappe.lesen(i, konfiguration.getWertSpalte());
            int menge = (int) mappe.lesen(i, konfiguration.getMengeSpalte());
            Buchung buchung = new Buchung(menge, wert, konfiguration);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis / konfiguration.getStunden();
    }
}
