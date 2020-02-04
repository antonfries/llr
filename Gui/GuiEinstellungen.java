package Gui;

import Listener.EinstellungenListener;
import Listener.GeneralStartListener;
import Listener.KoeffizientListener;
import Listener.KoeffizientenEinstellungenListener;
import Main.*;

import javax.swing.*;
import java.awt.*;

public class GuiEinstellungen extends JFrame {

    public JTextField koeffizientAnzahlTextfeld;
    public JTextField wertTextfeld;
    public JTextField mengeTextfeld;
    public JTextField maxMengeTextfeld;
    public JTextField buchungKoeffizientTextfeld;
    public JTextField zeileAnfangTextfeld;
    public JTextField zeileEndeTextfeld;
    private JLabel koeffizientAnzahlFrage;
    private JLabel wertFrage;
    private JLabel mengeFrage;
    private JLabel maxMengeFrage;
    private JLabel buchungKoeffizientFrage;
    private JLabel zeileAnfangFrage;
    private JLabel zeileEndeFrage;
    public JButton koeffizientEinstellungenButton;
    private JButton speichernButton;
    private JButton startButton;
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
        if (ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad())) {
            Excel excel = new Excel(); // Initial sollen die Einstellungen geöffnet werden können
            Konfiguration.setSheetPosition(excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui)));
        }
        Excel excel = new Excel();
        Konfiguration.setSheetPosition(excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui)));
        try {
            double arbeitszeit = Double.parseDouble(gui.arbeitszeitTextfeld.getText());
            if (arbeitszeit <= 0.0) {
                Validation.showNegativErrorMessage(gui);
            } else {
                Konfiguration.setArbeitszeit(arbeitszeit);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(gui);
        }
        // TODO: Evaluation, ob falsche Settings in der Registry zu handeln sind
        // TODO: BoxLayout für Einstellungen, sodass Textfelder-Breiten nicht immer individuell angepasst werden müssen
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
        add(zeileAnfangFrage);
        add(zeileAnfangTextfeld);
        add(zeileEndeFrage);
        add(zeileEndeTextfeld);
        add(speichernButton);
        add(startButton);
    }

    private void initComponents() {
        koeffizientAnzahlFrage = new JLabel("Anzahl Koeffizienten:");
        wertFrage = new JLabel("In welcher Spalte steht Wert?");
        wertFrage.setToolTipText("Möglich sind beliebig viele aneinandergeheftete Spalten (Bsp. FZ,R,PQR");
        mengeFrage = new JLabel("In welcher Spalte steht Menge?");
        mengeFrage.setToolTipText("Möglich sind beliebig viele aneinandergeheftete Spalten (Bsp. FZ,R,PQR");
        maxMengeFrage = new JLabel("Maximale sinnvolle Menge:");
        buchungKoeffizientFrage = new JLabel("Buchungs-Koeffizient:");
        zeileAnfangFrage = new JLabel("Zeilenanfang:");
        zeileAnfangFrage.setToolTipText("Wert gleich 1 setzen, falls keine Einschränkung auftreten soll");
        zeileEndeFrage = new JLabel("Zeilenende:");
        zeileEndeFrage.setToolTipText("Wert -1 setzen, falls keine EInschränkung auftreten soll");
        koeffizientAnzahlTextfeld = new JTextField(3);
        koeffizientAnzahlTextfeld.getDocument().addDocumentListener(new KoeffizientListener(GuiEinstellungen.this));
        wertTextfeld = new JTextField(3);
        mengeTextfeld = new JTextField(3);
        maxMengeTextfeld = new JTextField(5);
        buchungKoeffizientTextfeld = new JTextField(7);
        zeileAnfangTextfeld = new JTextField(15);
        zeileEndeTextfeld = new JTextField(15);
        koeffizientEinstellungenButton = new JButton("Koeffizienten-Einstellungen");
        koeffizientEinstellungenButton.addActionListener(new KoeffizientenEinstellungenListener(GuiEinstellungen.this));
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new EinstellungenListener(GuiEinstellungen.this));
        startButton = new JButton("Start");
        startButton.setEnabled(ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad()));
        startButton.addActionListener(new GeneralStartListener(GuiEinstellungen.this));
        gui.fillView();
        fillView();
    }

    public void fillView() {
        koeffizientAnzahlTextfeld.setText(String.valueOf(Konfiguration.getKoeffizientAnzahl()));
        wertTextfeld.setText(String.valueOf(Konfiguration.getWertSpalte()));
        mengeTextfeld.setText(String.valueOf(Konfiguration.getMengeSpalte()));
        maxMengeTextfeld.setText(String.valueOf(Konfiguration.getMaximalMenge()));
        buchungKoeffizientTextfeld.setText(String.valueOf(Konfiguration.getBuchungKoeffizient()));
        zeileAnfangTextfeld.setText(String.valueOf(Konfiguration.getZeileAnfang()));
        zeileEndeTextfeld.setText(String.valueOf(Konfiguration.getZeileEnde()));
    }
}