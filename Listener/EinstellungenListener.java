package Listener;

import Gui.GuiEinstellungen;
import Main.Einstellungen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Einstellungen einstellungen = new Einstellungen();
            einstellungen.setKoeffizientAnzahl(Integer.parseInt(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
            einstellungen.setWertSpalte(Integer.parseInt(guiEinstellungen.wertTextfeld.getText()));
            einstellungen.setMengeSpalte(Integer.parseInt(guiEinstellungen.mengeTextfeld.getText()));
            einstellungen.setMaximalMenge(Integer.parseInt(guiEinstellungen.maxMengeTextfeld.getText()));
            einstellungen.persistEinstellungen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
