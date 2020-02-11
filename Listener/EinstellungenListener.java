package Listener;

import Class.Einstellungen;
import Gui.GuiEinstellungen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new Einstellungen(guiEinstellungen);
    }
}
