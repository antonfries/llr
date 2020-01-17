import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartListener implements ActionListener {
    private Gui gui;

    StartListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Einstellungen einstellungen = new Einstellungen();
            einstellungen.setStunden(Double.parseDouble(gui.arbeitszeitTextfeld.getText()));
            einstellungen.persistArbeitszeitEinstellungen();
            einstellungen.setPfad(gui.dateipfadTextfeld.getText());
            einstellungen.persistPfadEinstellungen();
            double endergebnis = Rechner.rechnen();
            JOptionPane.showMessageDialog(gui, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
