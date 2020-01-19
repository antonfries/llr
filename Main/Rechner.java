package Main;

import java.io.IOException;

public class Rechner {

    public static double rechnen() throws IOException {
        Einstellungen einstellungen = new Einstellungen();
        Excel mappe = new Excel();
        double ergebnis = 0.0;
        // TODO: Algorithmus, bei dem Excel-Einlese-Operation aufhört
        for (int i = 0; i < 100; i++) {
            double wert = mappe.lesen(i, einstellungen.getWertSpalte());
            int menge = (int) mappe.lesen(i, einstellungen.getMengeSpalte());
            Buchung buchung = new Buchung(menge, wert, einstellungen);
            ergebnis += buchung.getProdukt();
        }
        return ergebnis / einstellungen.getStunden();
    }
}
