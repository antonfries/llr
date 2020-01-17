import javax.swing.*;
import java.awt.*;

public class GuiEinstellungen extends JFrame {

    private JLabel wertFrage;
    private JLabel mengeFrage;
    private JLabel maxMengeFrage;
    private JLabel anzahlDerKoeffizientenFrage;

    private JButton koeffizientenEinstellungenButton;
    private JButton speichernButton;

    public JTextField wertTextfeld;
    public JTextField mengeTextfeld;
    public JTextField maxMengeTextfeld;
    public JTextField anzahlDerKoeffizientenTextfeld;

    public GuiEinstellungen(String titel) {
        setTitle(titel);

        setLayout(new FlowLayout());
        setSize(300, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponents();

        add(anzahlDerKoeffizientenFrage);
        add(anzahlDerKoeffizientenTextfeld);
        add(koeffizientenEinstellungenButton);
        add(wertFrage);
        add(wertTextfeld);
        add(mengeFrage);
        add(mengeTextfeld);
        add(maxMengeFrage);
        add(maxMengeTextfeld);
        add(speichernButton);

        setVisible(true);
    }

    private void initComponents() {
        Einstellungen einstellungen = new Einstellungen();

        wertFrage = new JLabel("In welcher Spalte steht Wert?");
        mengeFrage = new JLabel("In welcher Spalte steht Menge?");
        maxMengeFrage = new JLabel("Maximale sinnvolle Menge:");
        anzahlDerKoeffizientenFrage = new JLabel("Anzahl Koeffizienten:");

        wertTextfeld = new JTextField(3);
        wertTextfeld.setText(String.valueOf(einstellungen.getWertSpalte()));
        mengeTextfeld = new JTextField(3);
        mengeTextfeld.setText(String.valueOf(einstellungen.getMengeSpalte()));
        maxMengeTextfeld = new JTextField(5);
        maxMengeTextfeld.setText(String.valueOf(einstellungen.getMaximalMenge()));
        anzahlDerKoeffizientenTextfeld = new JTextField(3);
        anzahlDerKoeffizientenTextfeld.setText(String.valueOf(einstellungen.getKoeffizientAnzahl()));

        speichernButton = new JButton("Speichern");
        koeffizientenEinstellungenButton = new JButton("Koeffizienten-Einstellungen");
        koeffizientenEinstellungenButton.addActionListener(new KoeffizientenEinstellungenListener(GuiEinstellungen.this));
        speichernButton.addActionListener(new EinstellungenListener(GuiEinstellungen.this));
    }
}