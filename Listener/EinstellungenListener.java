package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Konfiguration konfiguration = new Konfiguration();
        konfiguration.setKoeffizientAnzahl((int) Double.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
        konfiguration.setWertSpalte(guiEinstellungen.wertTextfeld.getText());
        konfiguration.setMengeSpalte(guiEinstellungen.mengeTextfeld.getText());
        konfiguration.setMaximalMenge((int) Double.parseDouble(guiEinstellungen.maxMengeTextfeld.getText()));
        konfiguration.setBuchungKoeffizient((int) Double.parseDouble(guiEinstellungen.buchungKoeffizientTextfeld.getText()));
        konfiguration.setZeilenAnfang((int) Double.parseDouble(guiEinstellungen.zeilenAnfangTextfeld.getText()));
        konfiguration.setZeilenEnde((int) Double.parseDouble(guiEinstellungen.zeilenEndeTextfeld.getText()));
        konfiguration.persistEinstellungen();
        guiEinstellungen.fillView();
    }
}
