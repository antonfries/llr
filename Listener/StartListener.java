package Listener;

import Gui.Gui;
import Main.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartListener implements ActionListener {
    private Gui gui;

    public StartListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        boolean continueFlag = true;
        Excel excel = new Excel();
        Konfiguration.setSheetPosition(excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui)));
        try {
            excel.wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            double arbeitszeit = Utility.parseDouble(gui.arbeitszeitTextfeld.getText());
            if (arbeitszeit <= 0.0) {
                Validation.showNegativErrorMessage(gui, Konfiguration.ARBEITSZEIT);
                continueFlag = false;
            } else {
                Konfiguration.setArbeitszeit(arbeitszeit);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(gui, Konfiguration.ARBEITSZEIT);
            continueFlag = false;
        }
        gui.fillView();
        if (continueFlag) {
            Rechner.rechnen(gui);
        }
    }
}
