package Listener;

import Gui.GuiEinstellungen;
import Gui.GuiKoeffizientenEinstellungen;
import Main.Konfiguration;
import Main.Validation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoeffizientenEinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    public KoeffizientenEinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        boolean continueFlag = true;
        try {
            int koeffizientAnzahl = (int) Double.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText());
            if (koeffizientAnzahl < 0) {
                Validation.showNegativErrorMessage(guiEinstellungen);
                continueFlag = false;
            } else {
                Konfiguration.setKoeffizientAnzahl((int) Double.parseDouble(guiEinstellungen.koeffizientAnzahlTextfeld.getText()));
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(guiEinstellungen);
            continueFlag = false;
        }
        guiEinstellungen.fillView();
        if (continueFlag) {
            new GuiKoeffizientenEinstellungen();
        }
    }
}
