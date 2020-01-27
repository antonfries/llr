package Listener;

import Gui.GuiEinstellungen;
import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoeffizientenEinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public KoeffizientenEinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Konfiguration konfiguration = new Konfiguration();
        konfiguration.setKoeffizientAnzahl((int) Double.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
        konfiguration.persistEinstellungen();
        guiEinstellungen.fillView();
        new GuiKoeffizientenEinstellungen();
    }
}
