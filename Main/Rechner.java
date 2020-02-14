package Main;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;

public class Rechner {

    public static final String TITEL = "Rechen-Operationen";
    public static final int WIDTH = 300;
    public static final int HEIGHT = 100;

    public static int charZuExcelSpalte(char c) {
        return Character.getNumericValue(c) - 10;
    }

    public static void rechnen(JFrame jFrame) {
        Excel excel = new Excel();
        Sheet sheet = excel.ExcelSheetListe[Konfiguration.getSheetPosition()];
        String wertSpalte = new StringBuilder(Konfiguration.getWertSpalte()).reverse().toString();
        String mengeSpalte = new StringBuilder(Konfiguration.getMengeSpalte()).reverse().toString();
        char[] wertSpalteListe = wertSpalte.toCharArray();
        char[] mengeSpalteListe = mengeSpalte.toCharArray();
        int min = Konfiguration.getZeileAnfang();
        int max = Konfiguration.getZeileEnde();
        int zeilenAnzahl = sheet.getLastRowNum() + 1;
        int progressLength = max == -1 ? zeilenAnzahl - min : max - min;
        if (zeilenAnzahl < min) { // TODO: +-1 überprüfen bei zeilenanzahl - min
            Validation.showZeileAnfangErrorMessage(jFrame);
            return;
        }
        JFrame rechenFrame = new JFrame(); // TODO: Auslagern in GUI-Klasse mit Schnittstelle
        rechenFrame.setTitle(TITEL);
        rechenFrame.setSize(WIDTH, HEIGHT);
        JProgressBar jProgressBar = new JProgressBar(0, progressLength);
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(0); // TODO: notwendig?
        Thread t = new Thread(() -> {
            double wert, menge, summand, ergebnis = 0.0; // TODO: ist das guter Codestyle?
            int counter = 0, erfolgCounter = 0;
            for (Row r : sheet) {
                final int percent = counter;
                SwingUtilities.invokeLater(() -> jProgressBar.setValue(percent));
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
                summand = buchung.getSummand();
                if (summand > 0.0) {
                    erfolgCounter++;
                }
                ergebnis += summand;
            }
                excel.close();
            ergebnis *= Konfiguration.getBuchungKoeffizient() / Konfiguration.getArbeitszeit();
            rechenFrame.dispatchEvent(new WindowEvent(rechenFrame, WindowEvent.WINDOW_CLOSING));
            StringSelection stringSelection = new StringSelection(String.valueOf(ergebnis));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(jFrame, "Lager-Leistung:    " + ergebnis,
                    "Ergebnis über " + erfolgCounter + " Zeilen",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        rechenFrame.add(jProgressBar);
        rechenFrame.setVisible(true);
        t.start();
    }

    private static double getEntitaet(char[] entitaetSpalteListe, Row r) {
        double einzelEntitaet, entitaet = 0.0;
        for (char entitaetSpalte : entitaetSpalteListe) {
            Cell entitaetZelle = r.getCell(charZuExcelSpalte(entitaetSpalte));
            einzelEntitaet = entitaetZelle != null
                    ? Utility.parseDoubleIgnoreError(entitaetZelle.getStringCellValue()) : 0.0;
            if (einzelEntitaet != 0.0) { // TODO: Reihenfolge des Auslesens der Spalten rausfinden
                entitaet = einzelEntitaet;
            }
        }
        return Math.abs(entitaet); // Es gibt manchmal negative Mengen
    }
}
