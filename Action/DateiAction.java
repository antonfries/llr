package Action;

import Class.Datei;
import Gui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DateiAction extends AbstractAction {
    private Gui gui;

    public DateiAction(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new Datei(gui);
    }
}
