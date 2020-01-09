import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Rechner {

    public static double rechnen() throws IOException {
        Einstellungen einstellungen = new Einstellungen();
        int reglerAnzahl = 0;
        try {
            double[] ausgeleseneEinstellungen = new double[4];
            File einstellungsDatei = new File(Einstellungen.Einstellungen);
            Scanner einstellungsStream = new Scanner(einstellungsDatei);
            for (int i = 0; i < 4; i++) {
                ausgeleseneEinstellungen[i] = Double.parseDouble(einstellungsStream.nextLine());
            }
            int maximaleMenge = (int) ausgeleseneEinstellungen[3];
            einstellungen.setMaximaleMenge(maximaleMenge);
            reglerAnzahl = (int) ausgeleseneEinstellungen[0];
            einstellungsStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            double stunden = 0.0;
            File arbeitszeitDatei = new File(Einstellungen.ArbeitszeitEinstellungen);
            Scanner arbeitszeitStream = new Scanner(arbeitszeitDatei);
            while (arbeitszeitStream.hasNextLine()) {
                stunden = Double.parseDouble(arbeitszeitStream.nextLine());
            }
            einstellungen.setStunden(stunden);
            arbeitszeitStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double[] KoeffizientenEinstellungen = new double[reglerAnzahl];
        double[] GrenzenEinstellungen = new double[20];
        try {
            File koeffizientenDatei = new File(Einstellungen.KoeffizientenEinstellungen);
            Scanner koeffizientenStream = new Scanner(koeffizientenDatei);
            for (int i = 0; i < reglerAnzahl; i++) {
                KoeffizientenEinstellungen[i] = Double.parseDouble(koeffizientenStream.nextLine());
            }
            koeffizientenStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            File grenzenDatei = new File(Einstellungen.GrenzenEinstellungen);
            Scanner grenzenStream = new Scanner(grenzenDatei);
            for (int i = 0; i < reglerAnzahl; i++) {
                GrenzenEinstellungen[i] = Double.parseDouble(grenzenStream.nextLine());

            }
            grenzenStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Regler[] regler = new Regler[reglerAnzahl];
        for (int i = 0; i < reglerAnzahl; i++) {
            regler[i] = new Regler(GrenzenEinstellungen[i], GrenzenEinstellungen[i + 1], KoeffizientenEinstellungen[i]);
            einstellungen.addRegler(regler[i], i);
        }
        double ergebnis = 0.0;
        ExcelInput mappe = new ExcelInput();
        for (int i = 0; i < 100; i++) {
            double wert = mappe.lesen(i, 0);
            int menge = (int) mappe.lesen(i, 1);
            Buchung buchung = new Buchung(menge, wert, einstellungen);
            ergebnis = ergebnis + (buchung.getKoeffizient() * buchung.getMenge());
        }
        return ergebnis / einstellungen.getStunden();
    }
}
