package Action;

import Class.Einstellungen;
import Gui.GuiEinstellungen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EinstellungenAction extends AbstractAction {


    private GuiEinstellungen guiEinstellungen;

    public EinstellungenAction(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new Einstellungen(guiEinstellungen);
    }
}
