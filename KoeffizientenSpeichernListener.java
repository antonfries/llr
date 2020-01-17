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
            PrintWriter grenzenPw = new PrintWriter(new FileWriter(new File(Einstellungen.GrenzenEinstellungen)));
            PrintWriter koeffizientenPw = new PrintWriter(new FileWriter(new File(Einstellungen.KoeffizientenEinstellungen)));
            String[] grenzenTextfelder = new String[21];
            String[] koeffizientenTextfelder = new String[20];
            for (int i = 0; i < einstellungen.getKoeffizientAnzahl(); i++) {
                grenzenTextfelder[i] = guiKoeffizientenEinstellungen.grenzenTextfelder[i].getText();
                grenzenPw.println(grenzenTextfelder[i]);
            }
            for (int j = 0; j < einstellungen.getKoeffizientAnzahl(); j++) {
                koeffizientenTextfelder[j] = guiKoeffizientenEinstellungen.koeffizientenTextfelder[j].getText();
                koeffizientenPw.println(koeffizientenTextfelder[j]);
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
