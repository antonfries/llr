package Listener;

import Gui.Gui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;
import java.util.Optional;

public class PfadListener implements DocumentListener {

    private Gui gui;

    public PfadListener(Gui gui) {
        this.gui = gui;
    }

    public void changedUpdate(DocumentEvent documentEvent) {
        aktualisiereStartButton();
    }

    public void insertUpdate(DocumentEvent documentEvent) {
        aktualisiereStartButton();
    }

    public void removeUpdate(DocumentEvent documentEvent) {
        aktualisiereStartButton();
    }

    private void aktualisiereStartButton() {
        String dateipfad = gui.dateipfadTextfeld.getText();
        boolean ordentlicheExcelDatei = new File(dateipfad).exists() && getExtensionByStringHandling(dateipfad).equals("xlsx");
        gui.startButton.setEnabled(ordentlicheExcelDatei);
    }

    public String getExtensionByStringHandling(String filename) {
        Optional<String> optional = Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
        return optional.orElse("");
    }
}
