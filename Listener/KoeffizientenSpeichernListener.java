package Listener;

import Gui.GuiKoeffizientenEinstellungen;
import Main.Einstellungen;

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
            Einstellungen einstellungen = new Einstellungen();
            for (int i = 0; i < einstellungen.getKoeffizientAnzahl(); i++) {
                einstellungen.grenzeListe[i] = Double.parseDouble(
                        guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText());
            }
            for (int j = 0; j < einstellungen.getKoeffizientAnzahl(); j++) {
                einstellungen.koeffizientListe[j] = Double.parseDouble(
                        guiKoeffizientenEinstellungen.koeffizientTextfeldListe[j].getText());
            }
            einstellungen.persistGrenzenKoeffizientenEinstellungen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
