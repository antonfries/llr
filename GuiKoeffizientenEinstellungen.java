import javax.swing.*;
import java.awt.*;

public class GuiKoeffizientenEinstellungen extends JFrame {

    private JLabel koeffizientenText;
    private JLabel grenzenText;
    public JTextField[] grenzenTextfelder;
    public JTextField[] koeffizientenTextfelder;
    private JButton speichernButton;

    public GuiKoeffizientenEinstellungen() {
        Einstellungen einstellungen = new Einstellungen();
        setTitle("Koeffizienten-Einstellungen");

        setLayout(new FlowLayout());
        setSize(800, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponents(einstellungen);

        add(grenzenText);
        for (int i = 0; i <= einstellungen.getKoeffizientAnzahl(); i++) {
            add(grenzenTextfelder[i]);
        }
        add(koeffizientenText);
        for (int i = 0; i < einstellungen.getKoeffizientAnzahl(); i++) {
            add(koeffizientenTextfelder[i]);
        }
        add(speichernButton);
        setVisible(true);
    }

    private void initComponents(Einstellungen einstellungen) {
        grenzenText = new JLabel("Grenzen:");
        koeffizientenText = new JLabel("Koeffizienten:");
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen.this));
        grenzenTextfelder = new JTextField[einstellungen.getKoeffizientAnzahl() +1];
        koeffizientenTextfelder = new JTextField[einstellungen.getKoeffizientAnzahl()];
        for (int i = 0; i <= einstellungen.getKoeffizientAnzahl(); i++) {
            grenzenTextfelder[i] = new JTextField(4);
            grenzenTextfelder[i].setText(String.valueOf(einstellungen.grenzeListe[i]));
        }
        for (int i = 0; i < einstellungen.getKoeffizientAnzahl(); i++) {
            koeffizientenTextfelder[i] = new JTextField(4);
            koeffizientenTextfelder[i].setText(String.valueOf(einstellungen.koeffizientListe[i]));
        }
    }
}
