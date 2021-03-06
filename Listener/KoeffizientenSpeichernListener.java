package Listener;

import Class.KoeffizientenEinstellungenSpeichern;
import Gui.GuiKoeffizientenEinstellungen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoeffizientenSpeichernListener implements ActionListener {
    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    public KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new KoeffizientenEinstellungenSpeichern(guiKoeffizientenEinstellungen);
    }
}
