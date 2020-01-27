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
    private Konfiguration konfiguration;

    public GuiKoeffizientenEinstellungen() {
        konfiguration = new Konfiguration();
        init();
        initComponents();
        addComponents();
        setVisible(true);
    }

    private void init() {
        setTitle("Koeffizienten-Einstellungen");
        setLayout(new FlowLayout());
        setSize(800, 300);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        add(grenzeText);
        for (JTextField grenzeTextfeld : grenzeTextfeldListe) {
            add(grenzeTextfeld);
        }
        add(koeffizientText);
        for (JTextField koeffizienttextfeld : koeffizientTextfeldListe) {
            add(koeffizienttextfeld);
        }
        add(speichernButton);
    }

    private void initComponents() {
        grenzeText = new JLabel("Grenzen:");
        koeffizientText = new JLabel("Koeffizienten:");
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen.this));
        grenzeTextfeldListe = new JTextField[konfiguration.getKoeffizientAnzahl() + 1];
        koeffizientTextfeldListe = new JTextField[konfiguration.getKoeffizientAnzahl()];
        for (int i = 0; i < konfiguration.grenzeListe.length; i++) {
            grenzeTextfeldListe[i] = new JTextField(4);
        }
        for (int i = 0; i < konfiguration.koeffizientListe.length; i++) {
            koeffizientTextfeldListe[i] = new JTextField(4);
        }
        fillView();
    }

    public void fillView() {
        konfiguration = new Konfiguration();
        for (int i = 0; i < konfiguration.grenzeListe.length; i++) {
            grenzeTextfeldListe[i].setText(String.valueOf(konfiguration.grenzeListe[i]));
        }
        for (int i = 0; i < konfiguration.koeffizientListe.length; i++) {
            koeffizientTextfeldListe[i].setText(String.valueOf(konfiguration.koeffizientListe[i]));
        }
    }
}
