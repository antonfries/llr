package Listener;

import Class.Reset;
import Gui.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
    private Gui gui;

    public ResetListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new Reset(gui);
    }
}
