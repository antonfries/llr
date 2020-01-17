import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EinstellungenListener implements ActionListener {
    private GuiEinstellungen guiEinstellungen;

    EinstellungenListener(GuiEinstellungen guiEinstellungen) {
        this.guiEinstellungen = guiEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Einstellungen einstellungen = new Einstellungen();
            einstellungen.setKoeffizientAnzahl(Integer.parseInt(guiEinstellungen.anzahlDerKoeffizientenTextfeld.getText()));
            einstellungen.setMaximalMenge(Integer.parseInt(guiEinstellungen.maxMengeTextfeld.getText()));
            einstellungen.setWertSpalte(Integer.parseInt(guiEinstellungen.wertTextfeld.getText()));
            einstellungen.setMengeSpalte(Integer.parseInt(guiEinstellungen.mengeTextfeld.getText()));
            einstellungen.persistEinstellungen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
