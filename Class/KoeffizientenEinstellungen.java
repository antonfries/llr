package Class;

import Gui.GuiEinstellungen;
import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;
import Main.Utility;
import Main.Validation;

public class KoeffizientenEinstellungen {
    public KoeffizientenEinstellungen(GuiEinstellungen guiEinstellungen) {
        boolean continueFlag = true;
        try {
            int koeffizientAnzahl = (int) Utility.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText());
            if (koeffizientAnzahl < 0 || koeffizientAnzahl > Konfiguration.MAXIMAL_KOEFFIZIENT_ANZAHL) {
                Validation.showKoeffizientErrorMessage(guiEinstellungen);
                continueFlag = false;
            } else {
                Utility.removeOldGrenzen(koeffizientAnzahl);
                Konfiguration.setKoeffizientAnzahl(koeffizientAnzahl);
                Utility.adjustGrenzen();
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.KOEFFIZIENT_ANZAHL);
            continueFlag = false;
        }
        guiEinstellungen.fillView();
        if (continueFlag
                && Konfiguration.getKoeffizientAnzahl() > 0
                && Konfiguration.getKoeffizientAnzahl() <= Konfiguration.MAXIMAL_KOEFFIZIENT_ANZAHL) {
            new GuiKoeffizientenEinstellungen();
        }
        // TODO: Hinweis anzeigen, warum Koeffizienten-Einstellungen bei 0 Koeffizienten nicht aufrufbar
    }
}
