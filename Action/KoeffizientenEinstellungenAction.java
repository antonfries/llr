package Action;

import Class.KoeffizientenEinstellungen;
import Gui.GuiEinstellungen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KoeffizientenEinstellungenAction extends AbstractAction {
    private GuiEinstellungen guiEinstellungen;

    public KoeffizientenEinstellungenAction(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new KoeffizientenEinstellungen(guiEinstellungen);
    }
}
