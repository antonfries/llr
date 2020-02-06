package Listener;

import Main.Rechner;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralStartListener implements ActionListener {
    private JFrame jFrame;

    public GeneralStartListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        double endergebnis = Rechner.rechnen(); // Speichern + Start-Button für faule Leute?
        StringSelection stringSelection = new StringSelection(String.valueOf(endergebnis));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(jFrame, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
