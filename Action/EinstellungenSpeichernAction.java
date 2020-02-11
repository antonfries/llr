package Action;

import Class.EinstellungenSpeichern;
import Gui.GuiEinstellungen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EinstellungenSpeichernAction extends AbstractAction {
    private GuiEinstellungen guiEinstellungen;

    public EinstellungenSpeichernAction(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new EinstellungenSpeichern(guiEinstellungen);
    }
}
