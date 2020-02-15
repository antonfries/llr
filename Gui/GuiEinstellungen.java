package Gui;

import Action.CloseAction;
import Action.EinstellungenSpeichernAction;
import Action.KoeffizientenEinstellungenAction;
import Listener.EinstellungenSpeichernListener;
import Listener.GeneralStartListener;
import Listener.KoeffizientListener;
import Listener.KoeffizientenEinstellungenListener;
import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GuiEinstellungen extends JFrame {

    public static final String TITEL = "Einstellungen";
    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;

    public JTextField koeffizientAnzahlTextfeld;
    public JTextField wertTextfeld;
    public JTextField mengeTextfeld;
    public JTextField minSummandTextfeld;
    public JTextField standardMengeTextfeld;
    public JTextField maxMengeTextfeld;
    public JTextField buchungKoeffizientTextfeld;
    public JTextField zeileAnfangTextfeld;
    public JTextField zeileEndeTextfeld;
    private JLabel koeffizientAnzahlFrage;
    private JLabel wertFrage;
    private JLabel mengeFrage;
    private JLabel minSummandFrage;
    public JLabel standardMengeFrage;
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
        getRootPane().setDefaultButton(startButton);
        setVisible(true);
    }

    private void persist() {
        if (ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad(), this)) {
            Excel excel = new Excel(); // Initial sollen die Einstellungen geöffnet werden können
            int sheetPosition = excel.getSheetPosition(SheetHelper.getSelectedSheetName(gui));
            if (sheetPosition != -1) {
                Konfiguration.setSheetPosition(sheetPosition);
            }
            excel.close();
        }
        try {
            double arbeitszeit = Utility.parseDouble(gui.arbeitszeitTextfeld.getText());
            if (arbeitszeit <= 0.0) {
                Validation.showNegativErrorMessage(gui, Konfiguration.ARBEITSZEIT);
            } else {
                Konfiguration.setArbeitszeit(arbeitszeit);
            }
        } catch (NumberFormatException e) {
            Validation.showZahlenErrorMessage(gui, Konfiguration.ARBEITSZEIT);
        }
    }

    private void init() {
        setTitle(TITEL);
        setLayout(new FlowLayout());
        setSize(WIDTH, HEIGHT);
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
        add(minSummandFrage);
        add(minSummandTextfeld);
        add(standardMengeFrage);
        add(standardMengeTextfeld);
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
        wertFrage.setToolTipText("Möglich sind beliebig viele aneinandergeheftete Buchstaben (Bsp. FZ,R,PQR");
        mengeFrage = new JLabel("In welcher Spalte steht Menge?");
        mengeFrage.setToolTipText("Möglich sind beliebig viele aneinandergeheftete Buchstaben (Bsp. FZ,R,PQR");
        minSummandFrage = new JLabel("Minimal-Summand:");
        minSummandFrage.setToolTipText("Wert 0 setzen, falls keine Manipulation auftreten soll");
        standardMengeFrage = new JLabel("Standard-Menge:");
        if (Konfiguration.getMaximalMenge() == -1) {
            standardMengeFrage.setToolTipText("Hat aktuell keine Auswirkung, da Maximal-Menge -1");
        }
        maxMengeFrage = new JLabel("Maximal-Menge:");
        maxMengeFrage.setToolTipText("Wert -1 setzen, falls keine Einschränkung auftreten soll");
        buchungKoeffizientFrage = new JLabel("Buchungs-Koeffizient:");
        buchungKoeffizientFrage.setToolTipText("Wert 1 setzen, falls keine Manipulation auftreten soll");
        zeileAnfangFrage = new JLabel("Zeilenanfang:");
        zeileAnfangFrage.setToolTipText("Wert gleich 1 setzen, falls keine Einschränkung auftreten soll");
        zeileEndeFrage = new JLabel("Zeilenende:");
        zeileEndeFrage.setToolTipText("Wert -1 setzen, falls keine Einschränkung auftreten soll");
        koeffizientAnzahlTextfeld = new JTextField(3);
        koeffizientAnzahlTextfeld.getDocument().addDocumentListener(new KoeffizientListener(this));
        wertTextfeld = new JTextField(3);
        mengeTextfeld = new JTextField(3);
        minSummandTextfeld = new JTextField(7);
        standardMengeTextfeld = new JTextField(8);
        maxMengeTextfeld = new JTextField(6);
        buchungKoeffizientTextfeld = new JTextField(7);
        zeileAnfangTextfeld = new JTextField(15);
        zeileEndeTextfeld = new JTextField(15);
        koeffizientEinstellungenButton = new JButton("Koeffizienten-Einstellungen");
        koeffizientEinstellungenButton.addActionListener(new KoeffizientenEinstellungenListener(this));
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new EinstellungenSpeichernListener(this));
        startButton = new JButton("Start");
        startButton.setEnabled(ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad(), this));
        startButton.addActionListener(new GeneralStartListener(this));

        JLayeredPane jLayeredPane = getLayeredPane();

        String saveAction = "Save";
        KeyStroke speichernStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(speichernStroke, saveAction);
        jLayeredPane.getActionMap().put(saveAction, new EinstellungenSpeichernAction(this));

        String closeAction = "Close";
        KeyStroke closeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        jLayeredPane.getActionMap().put(closeAction, new CloseAction(this));
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(closeStroke, closeAction);

        String koeffizientAction = "Koeffizienten";
        KeyStroke koeffizientStroke = KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK);
        jLayeredPane.getActionMap().put(koeffizientAction, new KoeffizientenEinstellungenAction(this));
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(koeffizientStroke, koeffizientAction);

        gui.fillView();
        fillView();
    }

    public void fillView() {
        koeffizientAnzahlTextfeld.setText(String.valueOf(Konfiguration.getKoeffizientAnzahl()));
        wertTextfeld.setText(String.valueOf(Konfiguration.getWertSpalte()));
        mengeTextfeld.setText(String.valueOf(Konfiguration.getMengeSpalte()));
        minSummandTextfeld.setText(String.valueOf(Konfiguration.getMinimalSummand()));
        standardMengeTextfeld.setText(String.valueOf(Konfiguration.getStandardMenge()));
        maxMengeTextfeld.setText(String.valueOf(Konfiguration.getMaximalMenge()));
        buchungKoeffizientTextfeld.setText(String.valueOf(Konfiguration.getBuchungKoeffizient()));
        zeileAnfangTextfeld.setText(String.valueOf(Konfiguration.getZeileAnfang()));
        zeileEndeTextfeld.setText(String.valueOf(Konfiguration.getZeileEnde()));
    }
}