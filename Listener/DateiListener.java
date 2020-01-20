package Listener;

import Gui.Gui;
import Main.Konfiguration;

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
        // JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        // TODO: nach der Entwicklung wieder HomeDirectory aktivieren
        JFileChooser jFileChooser = new JFileChooser("C:\\antonfries\\projects\\llr");
        FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
        jFileChooser.setFileFilter(excelFilter);
        int r = jFileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            String absoluterPfad = jFileChooser.getSelectedFile().getAbsolutePath();
            gui.dateipfadTextfeld.setText(absoluterPfad);
            new SheetSelektor(gui);
        }
    }
}
