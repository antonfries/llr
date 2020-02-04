package Main;

import Gui.Gui;

import javax.swing.*;
import java.util.Enumeration;

public class SheetHelper {

    public static String getSelectedSheetName(Gui gui) {
        if (gui.sheetListe == null) {
            return ""; // Initial ist die Sheet-Liste noch nicht befüllt
        }
        for (Enumeration<AbstractButton> buttons = gui.sheetListe.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return ""; // Sollte nie auftreten
    }
}
