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
        Rechner.rechnen(jFrame); // Speichern + Start-Button für faule Leute?
    }
}
