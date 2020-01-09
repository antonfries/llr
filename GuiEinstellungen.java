import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GuiEinstellungen extends JFrame {

    private JLabel wertFrage;
    private JLabel mengeFrage;
    private JLabel maxMengeFrage;
    private JLabel anzahlDerKoeffizientenFrage;

    private JButton koeffizientenEinstellungenButton;
    private JButton speichernButton;

    private JTextField wertTextfeld;
    private JTextField mengeTextfeld;
    private JTextField maxMengeTextfeld;
    private JTextField anzahlDerKoeffizientenTextfeld;

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
        // TODO: (Allgemein Default aus Einstellungsklasse und später momentane Settings auslesen)
        wertFrage = new JLabel("In welcher Spalte steht Wert?");
        mengeFrage = new JLabel("In welcher Spalte steht Menge?");
        maxMengeFrage = new JLabel("Maximale sinnvolle Menge:");
        anzahlDerKoeffizientenFrage = new JLabel("Anzahl Koeffizienten:");

        wertTextfeld = new JTextField(3);
        wertTextfeld.setText("0");
        mengeTextfeld = new JTextField(3);
        mengeTextfeld.setText("1");
        maxMengeTextfeld = new JTextField(5);
        maxMengeTextfeld.setText("500");
        anzahlDerKoeffizientenTextfeld = new JTextField(3);
        anzahlDerKoeffizientenTextfeld.setText("5");

        speichernButton = new JButton("Speichern");
        koeffizientenEinstellungenButton = new JButton("Koeffizienten-Einstellungen");
        koeffizientenEinstellungenButton.addActionListener(e1 -> {
            String anzahlDerKoeffizientenString = GuiEinstellungen.this.anzahlDerKoeffizientenTextfeld.getText();
            int anzahlDerKoeffizienten = Integer.parseInt(anzahlDerKoeffizientenString);
            new GuiKoeffizientenEinstellungen("Koeffizienten-Einstellungen", anzahlDerKoeffizienten);
        });
        speichernButton.addActionListener(e2 -> {
            try {
                // TODO: hardcoded
                File einstellungenDatei = new File("C://ilyabykov//Spaß//Einstellungen.txt");
                PrintWriter pw = new PrintWriter(new FileWriter(einstellungenDatei));
                pw.println(GuiEinstellungen.this.anzahlDerKoeffizientenTextfeld.getText());
                pw.println(GuiEinstellungen.this.wertTextfeld.getText());
                pw.println(GuiEinstellungen.this.mengeTextfeld.getText());
                pw.println(GuiEinstellungen.this.maxMengeTextfeld.getText());
                pw.flush();
                pw.close();
            } catch (IOException fe) {
                fe.printStackTrace();
            }
        });
    }
}