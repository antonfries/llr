package Gui;

import Listener.EinstellungenListener;
import Listener.KoeffizientenEinstellungenListener;
import Main.Excel;
import Main.Konfiguration;
import Main.SheetHelper;

import javax.swing.*;
import java.awt.*;

public class GuiEinstellungen extends JFrame {

    public JTextField koeffizientAnzahlTextfeld;
    public JTextField wertTextfeld;
    public JTextField mengeTextfeld;
    public JTextField maxMengeTextfeld;
    public JTextField buchungKoeffizientTextfeld;
    public JTextField zeilenAnfangTextfeld;
    public JTextField zeilenEndeTextfeld;
    private JLabel koeffizientAnzahlFrage;
    private JLabel wertFrage;
    private JLabel mengeFrage;
    private JLabel maxMengeFrage;
    private JLabel buchungKoeffizientFrage;
    private JLabel zeilenAnfangFrage;
    private JLabel zeilenEndeFrage;
    private JButton koeffizientEinstellungenButton;
    private JButton speichernButton;
    private Gui gui;

    public GuiEinstellungen(Gui gui) {
        this.gui = gui;
        persist();
        init();
        initComponents();
        addComponents();
        setVisible(true);
    }

    private void persist() {
        Excel excel = new Excel();
        Konfiguration.setSheetPosition(excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui)));
        try {
            Konfiguration.setArbeitszeit(Double.parseDouble(gui.arbeitszeitTextfeld.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(gui,
                    "Bitte geben Sie Zahlen ein!",
                    "Arbeitszeit-Validation",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void init() {
        setTitle("Einstellungen");
        setLayout(new FlowLayout());
        setSize(300, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        add(koeffizientAnzahlFrage);
        add(koeffizientAnzahlTextfeld);
        add(koeffizientEinstellungenButton);
        add(wertFrage);
        add(wertTextfeld);
        add(mengeFrage);
        add(mengeTextfeld);
        add(maxMengeFrage);
        add(maxMengeTextfeld);
        add(buchungKoeffizientFrage);
        add(buchungKoeffizientTextfeld);
        add(zeilenAnfangFrage);
        add(zeilenAnfangTextfeld);
        add(zeilenEndeFrage);
        add(zeilenEndeTextfeld);
        add(speichernButton);
    }

    private void initComponents() {
        koeffizientAnzahlFrage = new JLabel("Anzahl Koeffizienten:");
        wertFrage = new JLabel("In welcher Spalte steht Wert?");
        wertFrage.setToolTipText("Möglich sind beliebig viele aneinandergeheftete Spalten (Bsp. FZ,R,PQR");
        mengeFrage = new JLabel("In welcher Spalte steht Menge?");
        mengeFrage.setToolTipText("Möglich sind beliebig viele aneinandergeheftete Spalten (Bsp. FZ,R,PQR");
        maxMengeFrage = new JLabel("Maximale sinnvolle Menge:");
        buchungKoeffizientFrage = new JLabel("Buchungs-Koeffizient:");
        zeilenAnfangFrage = new JLabel("Zeilenanfang:");
        zeilenAnfangFrage.setToolTipText("Wert kleiner gleich 1 setzen, falls keine Einschränkung auftreten soll");
        zeilenEndeFrage = new JLabel("Zeilenende:");
        zeilenEndeFrage.setToolTipText("Wert -1 setzen, falls keine EInschränkung auftreten soll");
        koeffizientAnzahlTextfeld = new JTextField(3);
        wertTextfeld = new JTextField(3);
        mengeTextfeld = new JTextField(3);
        maxMengeTextfeld = new JTextField(3);
        buchungKoeffizientTextfeld = new JTextField(7);
        zeilenAnfangTextfeld = new JTextField(15);
        zeilenEndeTextfeld = new JTextField(15);
        koeffizientEinstellungenButton = new JButton("Koeffizienten-Einstellungen");
        koeffizientEinstellungenButton.addActionListener(new KoeffizientenEinstellungenListener(GuiEinstellungen.this));
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new EinstellungenListener(GuiEinstellungen.this));
        gui.fillView();
        fillView();
    }

    public void fillView() {
        koeffizientAnzahlTextfeld.setText(String.valueOf(Konfiguration.getKoeffizientAnzahl()));
        wertTextfeld.setText(String.valueOf(Konfiguration.getWertSpalte()));
        mengeTextfeld.setText(String.valueOf(Konfiguration.getMengeSpalte()));
        maxMengeTextfeld.setText(String.valueOf(Konfiguration.getMaximalMenge()));
        buchungKoeffizientTextfeld.setText(String.valueOf(Konfiguration.getBuchungKoeffizient()));
        zeilenAnfangTextfeld.setText(String.valueOf(Konfiguration.getZeileAnfang()));
        zeilenEndeTextfeld.setText(String.valueOf(Konfiguration.getZeileEnde()));
    }
}