package Listener;

import Gui.Gui;
import Main.Excel;
import org.apache.poi.ss.usermodel.Sheet;

import javax.swing.*;

public class SheetSelektor {
    public SheetSelektor(Gui gui) {
        Excel mappe = new Excel();
        gui.sheetContainer.removeAll();
        gui.sheetListe = new ButtonGroup();
        for (Sheet sheet : mappe.ExcelSheetListe) {
            JRadioButton sheetButton = new JRadioButton(sheet.getSheetName());
            gui.sheetListe.add(sheetButton);
            gui.sheetContainer.add(sheetButton);
        }
        // TODO: das gespeicherte Sheet vorselektieren
        gui.sheetListe.getElements().nextElement().setSelected(true);
        gui.revalidate();
    }
}
