package Listener;

import Gui.Gui;
import Main.Excel;
import Main.Konfiguration;

import javax.swing.*;

public class SheetSelektor {
    public SheetSelektor(Gui gui) {
        Konfiguration konfiguration = new Konfiguration();
        Excel mappe = new Excel();
        gui.sheetContainer.removeAll();
        gui.sheetListe = new ButtonGroup();
        if (konfiguration.getSheetIndex() >= mappe.ExcelSheetListe.length) {
            konfiguration.setSheetIndex(0);
            konfiguration.persistSheetEinstellungen();
        }
        for (int i = 0; i < mappe.ExcelSheetListe.length; i++) {
            JRadioButton sheetButton = new JRadioButton(mappe.ExcelSheetListe[i].getSheetName());
            sheetButton.setToolTipText(mappe.ExcelSheetListe[i].getLastRowNum() + " Zeilen");
            if (konfiguration.getSheetIndex() == i) {
                sheetButton.setSelected(true);
            }
            gui.sheetListe.add(sheetButton);
            gui.sheetContainer.add(sheetButton);
        }
        gui.revalidate();
    }
}
