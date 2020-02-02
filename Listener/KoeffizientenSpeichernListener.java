package Listener;

import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;

public class KoeffizientenSpeichernListener implements ActionListener {

    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    public KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Konfiguration.grenzeNode.flush();
            Konfiguration.koeffizientNode.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < guiKoeffizientenEinstellungen.grenzeTextfeldListe.length; i++) {
            Konfiguration.grenzeNode.putDouble(String.valueOf(i), Double.parseDouble(guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText()));
        }
        for (int i = 0; i < guiKoeffizientenEinstellungen.koeffizientTextfeldListe.length; i++) {
            Konfiguration.koeffizientNode.putDouble(String.valueOf(i), Double.parseDouble(guiKoeffizientenEinstellungen.koeffizientTextfeldListe[i].getText()));
        }
        guiKoeffizientenEinstellungen.fillView();
    }
}
