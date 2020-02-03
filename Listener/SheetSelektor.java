package Listener;

import Gui.Gui;
import Main.Excel;
import Main.Konfiguration;

import javax.swing.*;

public class SheetSelektor {
    public SheetSelektor(Gui gui) {
        Excel mappe = new Excel();
        gui.sheetContainer.removeAll();
        gui.sheetListe = new ButtonGroup();
        if (Konfiguration.getSheetPosition() >= mappe.ExcelSheetListe.length) {
            Konfiguration.setSheetPosition(0);
        }
        int counter = 0;
        for (int i = 0; i < mappe.ExcelSheetListe.length; i++) {
            JRadioButton sheetButton = new JRadioButton(mappe.ExcelSheetListe[i].getSheetName());
            int zeilenAnzahl = mappe.ExcelSheetListe[i].getLastRowNum();
            if (zeilenAnzahl == 0) {
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
        if (counter == 0) {
            gui.sheetContainer.add(new JLabel("<html>Die Excel-Datei enthält<br>keine nicht leeren Sheets!</html>"));
        }
        gui.repaint(); // Sheetcontainer enthält sonst manchmal noch alten Text
        // TODO: Beim Eingeben von Dezimalzeichen Komma zulassen?
        gui.revalidate();
    }
}
