package Listener;

import Main.Rechner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralStartListener implements ActionListener {
    private JFrame jFrame;

    public GeneralStartListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        double endergebnis = Rechner.rechnen(); // TODO: eventuell Speichern + Start-Button implementieren?
        JOptionPane.showMessageDialog(jFrame, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
