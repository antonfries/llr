package Class;

import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;
import Main.Utility;
import Main.Validation;

public class KoeffizientenEinstellungenSpeichern {

    public KoeffizientenEinstellungenSpeichern(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            try {
                double grenze = Utility.round2Digits(Utility.parseDouble(guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText()));
                if (grenze < 0.0 && i != Konfiguration.getKoeffizientAnzahl()) {
                    Validation.showNegativErrorMessage(guiKoeffizientenEinstellungen, (i + 1) + ". " + Konfiguration.GRENZEN);
                } else { // TODO: hier zwischen -1 und Wert abfangen
                    grenze = Math.max(-1.0, grenze);
                    Konfiguration.grenzeNode.putDouble(String.valueOf(i), grenze);
                }
            } catch (NumberFormatException e) {
                Validation.showZahlenErrorMessage(guiKoeffizientenEinstellungen, (i + 1) + ". " + Konfiguration.GRENZEN);
            }
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl(); i++) {
            try {
                double koeffizient = Utility.parseDouble(guiKoeffizientenEinstellungen.koeffizientTextfeldListe[i].getText());
                if (koeffizient <= 0.0) {
                    Validation.showNegativErrorMessage(guiKoeffizientenEinstellungen, (i + 1) + ". " + Konfiguration.KOEFFIZIENTEN);
                } else {
                    Konfiguration.koeffizientNode.putDouble(String.valueOf(i), koeffizient);
                }
            } catch (NumberFormatException e) {
                Validation.showZahlenErrorMessage(guiKoeffizientenEinstellungen, (i + 1) + ". " + Konfiguration.KOEFFIZIENTEN);
            }
        }
        Utility.adjustGrenzen();
        guiKoeffizientenEinstellungen.fillView();
    }
}
