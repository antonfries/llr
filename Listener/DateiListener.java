package Listener;

import Gui.Gui;
import Main.Excel;
import Main.Konfiguration;
import Main.SheetHelper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateiListener implements ActionListener {
    private Gui gui;

    public DateiListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Konfiguration konfiguration = new Konfiguration();
        JFileChooser jFileChooser = new JFileChooser("C:\\antonfries\\projects\\llr\\files");
        FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
        jFileChooser.setFileFilter(excelFilter);
        int r = jFileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            Excel excel = new Excel();
            konfiguration.setSheetIndex(excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui)));
            konfiguration.persistSheetEinstellungen();
            konfiguration.setPfad(jFileChooser.getSelectedFile().getAbsolutePath());
            konfiguration.persistPfadEinstellungen();
            gui.fillView();
        }
    }
}
