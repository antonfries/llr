package Main;

import Gui.GuiProgress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Rechner {
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
        int progressLength;
        if (max == -1) {
            progressLength = zeilenAnzahl - min;
        } else {
            progressLength = max - min;
            // Falls das Maximum größer ist als das angegebene Zeilen-Ende:
            progressLength = Math.min(progressLength, zeilenAnzahl - min);
        }
        progressLength++;
        if (zeilenAnzahl < min) {
            Validation.showZeileAnfangErrorMessage(jFrame);
            return;
        }
        GuiProgress guiProgress = new GuiProgress(progressLength);
        new Thread(() -> {
            double wert, menge, summand, ergebnis = 0.0;
            int counter = 0, erfolgCounter = 0;
            for (Row r : sheet) {
                counter++;
                final int nextValue = counter;
                SwingUtilities.invokeLater(() -> guiProgress.setValueOfProgressBar(nextValue));
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
                ergebnis += summand;
                if (summand > 0.0) {
                    erfolgCounter++;
                }
            }
            excel.close();
            ergebnis *= Konfiguration.getBuchungKoeffizient() / Konfiguration.getArbeitszeit();
            guiProgress.closeProgressBar();
            StringSelection stringSelection = new StringSelection(String.valueOf(ergebnis));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(jFrame, "Lager-Leistung:    " + ergebnis,
                    "Ergebnis über " + erfolgCounter + " Zeilen",
                    JOptionPane.INFORMATION_MESSAGE);
        }).start();
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
