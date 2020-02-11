package Action;

import Class.Reset;
import Gui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ResetAction extends AbstractAction {
    private Gui gui;

    public ResetAction(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        new Reset(gui);
    }
}
