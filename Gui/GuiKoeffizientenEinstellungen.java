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
        speichernButton = new JButton("Speichern"); // TODO: Start-Button hinzufügen, falls neue Settings ausprobiert werden wollen
        speichernButton.addActionListener(new KoeffizientenSpeichernListener(GuiKoeffizientenEinstellungen.this));
        grenzeTextfeldListe = new JTextField[Konfiguration.getKoeffizientAnzahl() + 1];
        koeffizientTextfeldListe = new JTextField[Konfiguration.getKoeffizientAnzahl()];
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            grenzeTextfeldListe[i] = new JTextField(4);
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i] = new JTextField(4);
        }
        fillView();
    }

    public void fillView() {
        for (int i = 1; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            if (Konfiguration.grenzeNode.getDouble(String.valueOf(i), 0.0) <= Konfiguration.grenzeNode.getDouble(String.valueOf(i - 1), 0.0)) {
                Konfiguration.grenzeNode.putDouble(String.valueOf(i), KoeffizientenSpeichernListener.parseDouble2Digits(Konfiguration.grenzeNode.getDouble(String.valueOf(i - 1), 0.0) + 0.01));
            } // TODO: nach +0.01 muss noch mal gerundet werden, aber das geht auch schöner
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            grenzeTextfeldListe[i].setText(Konfiguration.grenzeNode.get(String.valueOf(i), "0.0"));
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i].setText(Konfiguration.koeffizientNode.get(String.valueOf(i), String.valueOf(Konfiguration.getStandardKoeffizient())));
        }
    }
}
