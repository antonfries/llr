package Action;

import Gui.Gui;
import Gui.GuiEinstellungen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EinstellungenAction extends AbstractAction {

    private Gui gui;

    public EinstellungenAction(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new GuiEinstellungen(gui);
    }
}
