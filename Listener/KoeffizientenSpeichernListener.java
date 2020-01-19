package Listener;

import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class KoeffizientenSpeichernListener implements ActionListener {

    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    public KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Konfiguration konfiguration = new Konfiguration();
            for (int i = 0; i < konfiguration.getKoeffizientAnzahl(); i++) {
                konfiguration.grenzeListe[i] = Double.parseDouble(
                        guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText());
            }
            for (int j = 0; j < konfiguration.getKoeffizientAnzahl(); j++) {
                konfiguration.koeffizientListe[j] = Double.parseDouble(
                        guiKoeffizientenEinstellungen.koeffizientTextfeldListe[j].getText());
            }
            konfiguration.persistGrenzenKoeffizientenEinstellungen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
