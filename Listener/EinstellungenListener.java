package Listener;

import Gui.Gui;
import Gui.GuiEinstellungen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EinstellungenListener implements ActionListener {

    private Gui gui;

    public EinstellungenListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new GuiEinstellungen(gui);
    }
}
