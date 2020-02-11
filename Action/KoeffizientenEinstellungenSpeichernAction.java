package Action;

import Class.KoeffizientenEinstellungenSpeichern;
import Gui.GuiKoeffizientenEinstellungen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KoeffizientenEinstellungenSpeichernAction extends AbstractAction {
    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    public KoeffizientenEinstellungenSpeichernAction(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new KoeffizientenEinstellungenSpeichern(guiKoeffizientenEinstellungen);
    }
}
