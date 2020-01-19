import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class KoeffizientenSpeichernListener implements ActionListener {

    private GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen;

    KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen guiKoeffizientenEinstellungen) {
        this.guiKoeffizientenEinstellungen = guiKoeffizientenEinstellungen;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Einstellungen einstellungen = new Einstellungen();
            // TODO: Singular/Plural generalisieren
            PrintWriter grenzenPw = new PrintWriter(new FileWriter(new File(Einstellungen.GrenzenEinstellungen)));
            PrintWriter koeffizientenPw = new PrintWriter(new FileWriter(new File(Einstellungen.KoeffizientenEinstellungen)));
            for (int i = 0; i < einstellungen.getKoeffizientAnzahl(); i++) {
                grenzenPw.println(guiKoeffizientenEinstellungen.grenzeTextfeldListe[i].getText());
            }
            for (int j = 0; j < einstellungen.getKoeffizientAnzahl(); j++) {
                koeffizientenPw.println(guiKoeffizientenEinstellungen.koeffizientTextfeldListe[j]);
            }
            grenzenPw.flush();
            grenzenPw.close();
            koeffizientenPw.flush();
            koeffizientenPw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
