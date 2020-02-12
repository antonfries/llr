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
            if (koeffizientAnzahl < 0) {
                Validation.showNegativErrorMessage(guiEinstellungen, Konfiguration.KOEFFIZIENT_ANZAHL);
                continueFlag = false;
            } else {
                Konfiguration.setKoeffizientAnzahl(koeffizientAnzahl);
                Utility.removeOldGrenzen();
                Utility.adjustGrenzen();
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen, Konfiguration.KOEFFIZIENT_ANZAHL);
            continueFlag = false;
        }
        guiEinstellungen.fillView();
        if (continueFlag && Konfiguration.getKoeffizientAnzahl() > 0) { // damit nicht über Shortcut aufrufbar
            new GuiKoeffizientenEinstellungen();
        }
    }
}
