package Gui;

import Listener.EinstellungenListener;
import Listener.KoeffizientenEinstellungenListener;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.*;

public class GuiEinstellungen extends JFrame {

    public JTextField wertTextfeld;
    public JTextField mengeTextfeld;
    public JTextField maxMengeTextfeld;
    public JTextField koeffizientAnzahlTextfeld;
    private JLabel wertFrage;
    private JLabel mengeFrage;
    private JLabel maxMengeFrage;
    private JLabel koeffizientAnzahlFrage;
    private JButton koeffizientEinstellungenButton;
    private JButton speichernButton;

    public GuiEinstellungen(String titel) {
        setTitle(titel);
        setLayout(new FlowLayout());
        setSize(300, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        add(koeffizientAnzahlFrage);
        add(koeffizientAnzahlTextfeld);
        add(koeffizientEinstellungenButton);
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
        Konfiguration konfiguration = new Konfiguration();
        wertFrage = new JLabel("In welcher Spalte steht Wert?");
        mengeFrage = new JLabel("In welcher Spalte steht Menge?");
        maxMengeFrage = new JLabel("Maximale sinnvolle Menge:");
        koeffizientAnzahlFrage = new JLabel("Anzahl Koeffizienten:");
        wertTextfeld = new JTextField(3);
        wertTextfeld.setText(String.valueOf(konfiguration.getWertSpalte()));
        mengeTextfeld = new JTextField(3);
        mengeTextfeld.setText(String.valueOf(konfiguration.getMengeSpalte()));
        maxMengeTextfeld = new JTextField(5);
        maxMengeTextfeld.setText(String.valueOf(konfiguration.getMaximalMenge()));
        koeffizientAnzahlTextfeld = new JTextField(3);
        koeffizientAnzahlTextfeld.setText(String.valueOf(konfiguration.getKoeffizientAnzahl()));
        koeffizientEinstellungenButton = new JButton("Koeffizienten-Einstellungen");
        koeffizientEinstellungenButton.addActionListener(new KoeffizientenEinstellungenListener(GuiEinstellungen.this));
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new EinstellungenListener(GuiEinstellungen.this));
    }
}