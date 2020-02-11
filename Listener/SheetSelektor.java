package Listener;

import Gui.Gui;
import Main.Excel;
import Main.Konfiguration;

import javax.swing.*;
import java.io.IOException;

public class SheetSelektor {
    public SheetSelektor(Gui gui) {
        Excel excel = new Excel();
        gui.sheetContainer.removeAll();
        gui.sheetListe = new ButtonGroup();
        if (Konfiguration.getSheetPosition() >= excel.ExcelSheetListe.length) { // TODO: macht das Sinn?
            Konfiguration.setSheetPosition(0);
        }
        int counter = 0;
        for (int i = 0; i < excel.ExcelSheetListe.length; i++) {
            JRadioButton sheetButton = new JRadioButton(excel.ExcelSheetListe[i].getSheetName());
            int zeilenAnzahl = excel.ExcelSheetListe[i].getLastRowNum() + 1;
            if (zeilenAnzahl <= 1) {
                // TODO: Sheetposition kann nicht übernommen werden bei geskippten Zeilen
                continue;
            } else {
                counter++;
            }
            sheetButton.setToolTipText(zeilenAnzahl + " Zeilen");
            if (Konfiguration.getSheetPosition() == i) {
                sheetButton.setSelected(true);
            }
            gui.sheetListe.add(sheetButton);
            gui.sheetContainer.add(sheetButton);
        }
        try {
            excel.wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (counter == 0) {
            gui.sheetContainer.add(new JLabel("<html>Die Excel-Datei enthält<br>keine nicht leeren Sheets!</html>"));
        }
        gui.repaint(); // Sheet-Container enthält sonst manchmal noch alten Text
        gui.revalidate();
    }
}
