package Class;

import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;
import Main.Utility;
import Main.Validation;

public class KoeffizientenEinstellungenSpeichern {

    public KoeffizientenEinstellungenSpeichern(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        for (int i = 0; i < guiKoeffizientenEinstellungen.grenzeTextfeldListe.length; i++) {
            try {
                double grenze = Utility.round2Digits(Utility.parseDouble(guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText()));
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
        Utility.adjustGrenzen();
        guiKoeffizientenEinstellungen.fillView();
    }
}
