package Listener;

import Gui.GuiEinstellungen;
import Gui.GuiKoeffizientenEinstellungen;
import Main.Einstellungen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class KoeffizientenEinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public KoeffizientenEinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Einstellungen einstellungen = new Einstellungen();
            einstellungen.setKoeffizientAnzahl(Integer.parseInt(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
            einstellungen.persistEinstellungen();
            new GuiKoeffizientenEinstellungen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
