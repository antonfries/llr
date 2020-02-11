package Action;

import Class.KoeffizientenEinstellungen;
import Gui.GuiKoeffizientenEinstellungen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KoeffizientenEinstellungenAction extends AbstractAction {
    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    public KoeffizientenEinstellungenAction(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new KoeffizientenEinstellungen(guiKoeffizientenEinstellungen);
    }
}
