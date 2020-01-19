package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;

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
            char wertSpalte = Character.toUpperCase(guiEinstellungen.wertTextfeld.getText().charAt(0));
            char mengeSpalte = Character.toUpperCase(guiEinstellungen.mengeTextfeld.getText().charAt(0));
            Konfiguration konfiguration = new Konfiguration();
            konfiguration.setKoeffizientAnzahl(Integer.parseInt(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
            konfiguration.setWertSpalte(wertSpalte);
            konfiguration.setMengeSpalte(mengeSpalte);
            konfiguration.setMaximalMenge(Integer.parseInt(guiEinstellungen.maxMengeTextfeld.getText()));
            konfiguration.persistEinstellungen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
