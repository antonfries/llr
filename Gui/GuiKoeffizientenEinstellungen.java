package Gui;

import Listener.KoeffizientenSpeichernListener;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.*;

public class GuiKoeffizientenEinstellungen extends JFrame {

    public JTextField[] grenzeTextfeldListe;
    public JTextField[] koeffizientTextfeldListe;
    private JLabel koeffizientText;
    private JLabel grenzeText;
    private JButton speichernButton;

    public GuiKoeffizientenEinstellungen() {
        // TODO: Nach dem Speichern immer direkt die Settings neu einpflegen (Validierung anzeigen)
        Konfiguration konfiguration = new Konfiguration();
        setTitle("Koeffizienten-Einstellungen");
        setLayout(new FlowLayout());
        setSize(800, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents(konfiguration);
        add(grenzeText);
        for (JTextField grenzeTextfeld : grenzeTextfeldListe) {
            add(grenzeTextfeld);
        }
        add(koeffizientText);
        for (JTextField koeffizienttextfeld : koeffizientTextfeldListe) {
            add(koeffizienttextfeld);
        }
        add(speichernButton);
        setVisible(true);
    }

    private void initComponents(Konfiguration konfiguration) {
        grenzeText = new JLabel("Grenzen:");
        koeffizientText = new JLabel("Koeffizienten:");
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen.this));
        grenzeTextfeldListe = new JTextField[konfiguration.getKoeffizientAnzahl() + 1];
        koeffizientTextfeldListe = new JTextField[konfiguration.getKoeffizientAnzahl()];
        for (int i = 0; i < konfiguration.grenzeListe.length; i++) {
            grenzeTextfeldListe[i] = new JTextField(4);
            grenzeTextfeldListe[i].setText(String.valueOf(konfiguration.grenzeListe[i]));
        }
        for (int i = 0; i < konfiguration.koeffizientListe.length; i++) {
            koeffizientTextfeldListe[i] = new JTextField(4);
            koeffizientTextfeldListe[i].setText(String.valueOf(konfiguration.koeffizientListe[i]));
        }
    }
}
