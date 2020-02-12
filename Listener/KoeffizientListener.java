package Listener;

import Gui.GuiEinstellungen;
import Main.Konfiguration;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class KoeffizientListener implements DocumentListener {
    private GuiEinstellungen guiEinstellungen;

    public KoeffizientListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void changedUpdate(DocumentEvent documentEvent) {
        aktualisiereKoeffizientButton();
    }

    public void insertUpdate(DocumentEvent documentEvent) {
        aktualisiereKoeffizientButton();
    }

    public void removeUpdate(DocumentEvent documentEvent) {
        aktualisiereKoeffizientButton();
    }

    private void aktualisiereKoeffizientButton() {
        boolean validKoeffizientAnzahl = false;
        int koeffizientAnzahl = 0;
        try {
            koeffizientAnzahl = Integer.parseInt(guiEinstellungen.koeffizientAnzahlTextfeld.getText());
        } catch (NumberFormatException ignored) {
        }
        if (koeffizientAnzahl > 0 && koeffizientAnzahl <= Konfiguration.MAXIMAL_KOEFFIZIENT_ANZAHL) {
            validKoeffizientAnzahl = true;
        }
        guiEinstellungen.koeffizientEinstellungenButton.setEnabled(validKoeffizientAnzahl);
    }
}
