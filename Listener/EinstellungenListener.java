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
        Konfiguration.setKoeffizientAnzahl((int) Double.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
        Konfiguration.setWertSpalte(guiEinstellungen.wertTextfeld.getText());
        Konfiguration.setMengeSpalte(guiEinstellungen.mengeTextfeld.getText());
        Konfiguration.setMaximalMenge((int) Double.parseDouble(guiEinstellungen.maxMengeTextfeld.getText()));
        Konfiguration.setBuchungKoeffizient((int) Double.parseDouble(guiEinstellungen.buchungKoeffizientTextfeld.getText()));
        Konfiguration.setZeileAnfang((int) Double.parseDouble(guiEinstellungen.zeilenAnfangTextfeld.getText()));
        Konfiguration.setZeileEnde((int) Double.parseDouble(guiEinstellungen.zeilenEndeTextfeld.getText()));
        guiEinstellungen.fillView();
    }
}
