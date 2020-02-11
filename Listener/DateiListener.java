package Listener;

import Class.Datei;
import Gui.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateiListener implements ActionListener {
    private Gui gui;

    public DateiListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new Datei(gui);
    }
}
