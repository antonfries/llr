package Listener;

import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;
import Main.Validation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;

public class KoeffizientenSpeichernListener implements ActionListener {

    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    public KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    private static double parseDouble2Digits(String valueInText) {
        double value = Double.parseDouble(valueInText);
        return Math.round(value * 100d) / 100d;
    }

    public static double parseDouble2Digits(double value) {
        return Math.round(value * 100d) / 100d;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Konfiguration.grenzeNode.flush();
            Konfiguration.koeffizientNode.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < guiKoeffizientenEinstellungen.grenzeTextfeldListe.length; i++) {
            try {
                Konfiguration.grenzeNode.putDouble(String.valueOf(i), parseDouble2Digits(guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText()));
            } catch (NumberFormatException e) {
                Validation.showZahlenErrorMessage(guiKoeffizientenEinstellungen);
            }
        }
        for (int i = 0; i < guiKoeffizientenEinstellungen.koeffizientTextfeldListe.length; i++) {
            try {
                Konfiguration.koeffizientNode.putDouble(String.valueOf(i), Double.parseDouble(guiKoeffizientenEinstellungen.koeffizientTextfeldListe[i].getText()));
            } catch (NumberFormatException e) {
                Validation.showZahlenErrorMessage(guiKoeffizientenEinstellungen);
            }
        }
        guiKoeffizientenEinstellungen.fillView();
    }
}
