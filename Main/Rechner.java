package Main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class Rechner {

    public static int charZuExcelSpalte(char c) {
        return Character.getNumericValue(c) - 10;
    }

    public static void rechnen(JFrame jFrame) {
        Excel excel = new Excel(Konfiguration.getDateiPfad());
        double ergebnis = 0.0;
        Sheet sheet = excel.ExcelSheetListe[Konfiguration.getSheetPosition()];
        char[] wertSpalteListe = Konfiguration.getWertSpalte().toCharArray();
        char[] mengeSpalteListe = Konfiguration.getMengeSpalte().toCharArray();
        double wert, menge;
        int counter = 0;
        int min = Konfiguration.getZeileAnfang();
        int max = Konfiguration.getZeileEnde();
        int erfolgCounter = 0;
        int zellenAnzahl = sheet.getLastRowNum();
        if (zellenAnzahl < max) { // TODO: überprüfen
            Validation.showZeilenEndeErrorMessage(jFrame);
            return;
        }
        for (Row r : sheet) {
            counter++;
            if (counter < min) {
                continue;
            }
            if (max >= 1) {
                if (counter > max) {
                    break;
                }
            }
            wert = getEntitaet(wertSpalteListe, r);
            menge = getEntitaet(mengeSpalteListe, r);
            Buchung buchung = new Buchung(menge, wert);
            ergebnis += buchung.getProdukt();
            if (ergebnis > 0.0) {
                erfolgCounter++;
            }
            if (ergebnis < Konfiguration.getMinimalSummand() && ergebnis != 0.0) {
                ergebnis = Konfiguration.getMinimalSummand();
            }
            if (ergebnis > Konfiguration.getMaximalSummand()) {
                ergebnis = Konfiguration.getStandardSummand();
            }
        }
        try {
            excel.wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ergebnis *= Konfiguration.getBuchungKoeffizient() / Konfiguration.getArbeitszeit();
        StringSelection stringSelection = new StringSelection(String.valueOf(ergebnis));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(jFrame, "Lager-Leistung:    " + ergebnis,
                "Ergebnis über " + erfolgCounter + " Zeilen",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static double getEntitaet(char[] entitaetSpalteListe, Row r) {
        double einzelEntitaet, entitaet = 0.0;
        for (char entitaetSpalte : entitaetSpalteListe) {
            Cell entitaetZelle = r.getCell(charZuExcelSpalte(entitaetSpalte));
            einzelEntitaet = entitaetZelle != null
                    ? Utility.parseDoubleIgnoreError(entitaetZelle.getStringCellValue()) : 0.0;
            if (einzelEntitaet != 0.0) {
                entitaet = einzelEntitaet;
            }
        }
        return Math.abs(entitaet); // Es gibt manchmal negative Mengen
    }
}
