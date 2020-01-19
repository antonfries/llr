import javax.swing.*;
import java.awt.*;

public class GuiKoeffizientenEinstellungen extends JFrame {

    private JLabel koeffizientText;
    private JLabel grenzeText;
    public JTextField[] grenzeTextfeldListe;
    public JTextField[] koeffizientTextfeldListe;
    private JButton speichernButton;

    public GuiKoeffizientenEinstellungen() {
        // TODO: Nach dem Speichern immer direkt die Settings neu einpflegen (Validierung anzeigen)
        Einstellungen einstellungen = new Einstellungen();
        setTitle("Koeffizienten-Einstellungen");
        setLayout(new FlowLayout());
        setSize(800, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents(einstellungen);
        add(grenzeText);
        for (int i = 0; i < einstellungen.getKoeffizientAnzahl() + 1; i++) {
            add(grenzeTextfeldListe[i]);
        }
        add(koeffizientText);
        for (int i = 0; i < einstellungen.getKoeffizientAnzahl(); i++) {
            add(koeffizientTextfeldListe[i]);
        }
        add(speichernButton);
        setVisible(true);
    }

    private void initComponents(Einstellungen einstellungen) {
        grenzeText = new JLabel("Grenzen:");
        koeffizientText = new JLabel("Koeffizienten:");
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen.this));
        grenzeTextfeldListe = new JTextField[einstellungen.getKoeffizientAnzahl() + 1];
        koeffizientTextfeldListe = new JTextField[einstellungen.getKoeffizientAnzahl()];
        for (int i = 0; i <= einstellungen.getKoeffizientAnzahl(); i++) {
            grenzeTextfeldListe[i] = new JTextField(4);
            // TODO: Hier die Nachkommastellen auf 2 begrenzen
            grenzeTextfeldListe[i].setText(String.valueOf(einstellungen.grenzeListe[i]));
        }
        for (int i = 0; i < einstellungen.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i] = new JTextField(4);
            koeffizientTextfeldListe[i].setText(String.valueOf(einstellungen.koeffizientListe[i]));
        }
    }
}
