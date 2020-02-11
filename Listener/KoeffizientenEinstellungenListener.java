package Listener;

import Class.KoeffizientenEinstellungen;
import Gui.GuiEinstellungen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoeffizientenEinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public KoeffizientenEinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new KoeffizientenEinstellungen(guiEinstellungen);
    }
}
