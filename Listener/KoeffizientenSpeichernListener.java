package Listener;

import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;
import Main.Utility;
import Main.Validation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class KoeffizientenSpeichernListener implements ActionListener {

    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    public KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Konfiguration.grenzeNode.removeNode();
            Konfiguration.grenzeNode = Preferences.userRoot().node("Grenzen");
            Konfiguration.koeffizientNode.removeNode();
            Konfiguration.koeffizientNode = Preferences.userRoot().node("Koeffizienten");
            // TODO: [Prio] Nur überflüssige Werte löschen beim Verkleinern der Koeffizient-Anzahl
            // Dadurch, dass diese Logik erst beim Speichern ausgeführt wird, ist es möglich, alte Werte beizubehalten
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        // TODO: [Prio] Diese Logik auslagern für valide Grenzen und Koeffizienten bei Anpassung nur der Koeffizient-Anzahl
        for (int i = 0; i < guiKoeffizientenEinstellungen.grenzeTextfeldListe.length; i++) {
            try {
                double grenze = Utility.round2Digits(Utility.parseDouble(guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText()));
                if (i == guiKoeffizientenEinstellungen.grenzeTextfeldListe.length - 1) {
                    grenze = Math.max(-1.0, grenze);
                } else {
                    grenze = Math.max(0.0, grenze);
                }
                Konfiguration.grenzeNode.putDouble(String.valueOf(i), grenze);
            } catch (NumberFormatException e) {
                Validation.showZahlenErrorMessage(guiKoeffizientenEinstellungen, "Grenzen");
            }
        }
        for (int i = 0; i < guiKoeffizientenEinstellungen.koeffizientTextfeldListe.length; i++) {
            try {
                double koeffizient = Utility.parseDouble(guiKoeffizientenEinstellungen.koeffizientTextfeldListe[i].getText());
                if (koeffizient <= 0.0) {
                    Validation.showNegativErrorMessage(guiKoeffizientenEinstellungen, "Koeffizienten");
                } else {
                    Konfiguration.koeffizientNode.putDouble(String.valueOf(i), koeffizient);
                }
            } catch (NumberFormatException e) {
                Validation.showZahlenErrorMessage(guiKoeffizientenEinstellungen, "Koeffizienten");
            }
        }
        guiKoeffizientenEinstellungen.fillView();
    }
}
