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
        for (int i = 0; i < mappe.ExcelSheetListe.length; i++) {
            JRadioButton sheetButton = new JRadioButton(mappe.ExcelSheetListe[i].getSheetName());
            sheetButton.setToolTipText(mappe.ExcelSheetListe[i].getLastRowNum() + " Zeilen");
            if (Konfiguration.getSheetPosition() == i) {
                sheetButton.setSelected(true);
            }
            gui.sheetListe.add(sheetButton);
            gui.sheetContainer.add(sheetButton);
        }
        gui.revalidate();
    }
}
