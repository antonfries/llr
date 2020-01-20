package Listener;

import Gui.Gui;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SheetSelektor {
    public SheetSelektor(Gui gui) {
        try {
            File ExcelDatei = new File(gui.dateipfadTextfeld.getText());
            // TODO: Close ZipFile for archive
            XSSFWorkbook wb = new XSSFWorkbook(ExcelDatei);
            gui.sheetContainer.removeAll();
            gui.sheetListe = new ButtonGroup();
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                JRadioButton sheet = new JRadioButton(wb.getSheetName(i));
                gui.sheetListe.add(sheet);
                gui.sheetContainer.add(sheet);
            }
            // TODO: das gespeicherte Sheet vorselektieren
            gui.sheetListe.getElements().nextElement().setSelected(true);
            gui.revalidate();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
