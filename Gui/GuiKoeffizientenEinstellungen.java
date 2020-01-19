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
        for (int i = 0; i < konfiguration.getKoeffizientAnzahl() + 1; i++) {
            add(grenzeTextfeldListe[i]);
        }
        add(koeffizientText);
        for (int i = 0; i < konfiguration.getKoeffizientAnzahl(); i++) {
            add(koeffizientTextfeldListe[i]);
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
        for (int i = 0; i <= konfiguration.getKoeffizientAnzahl(); i++) {
            grenzeTextfeldListe[i] = new JTextField(4);
            // TODO: Hier die Nachkommastellen auf 2 begrenzen
            grenzeTextfeldListe[i].setText(String.valueOf(konfiguration.grenzeListe[i]));
        }
        for (int i = 0; i < konfiguration.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i] = new JTextField(4);
            koeffizientTextfeldListe[i].setText(String.valueOf(konfiguration.koeffizientListe[i]));
        }
    }
}
