package Listener;

import Class.EinstellungenSpeichern;
import Gui.GuiEinstellungen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EinstellungenSpeichernListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenSpeichernListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new EinstellungenSpeichern(guiEinstellungen);
    }
}
