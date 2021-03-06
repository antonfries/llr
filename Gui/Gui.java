package Gui;

import Action.DateiAction;
import Action.EinstellungenAction;
import Action.ResetAction;
import Listener.*;
import Main.ExcelFileChecker;
import Main.FocusTextField;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Gui extends JFrame {

    public static final String TITEL = "LLR";
    public static final int WIDTH = 500;
    public static final int HEIGHT = 315;
    public static final int SCROLL_WIDTH = 230;
    public static final int SCROLL_HEIGHT = 174;

    public static final int DATEIPFAD_BREITE = 20;
    public static final int ARBEITSZEIT_BREITE = 30;

    public FocusTextField dateipfadTextfeld;
    public FocusTextField arbeitszeitTextfeld;
    public JButton startButton;
    public ButtonGroup sheetListe;
    public JPanel jPanel;
    public JScrollPane jScrollPane;
    private JLabel dateipfad;
    private JLabel arbeitszeit;
    private JButton einstellungsButton;
    private JButton dateiButton;
    private JButton resetButton;

    public Gui() {
        init();
        initComponents();
        addComponents();
        getRootPane().setDefaultButton(startButton);
        setVisible(true);
    }

    private void init() {
        setTitle(TITEL);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(WIDTH, HEIGHT);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        add(einstellungsButton);
        add(dateipfad);
        add(dateipfadTextfeld);
        add(arbeitszeit);
        add(arbeitszeitTextfeld);
        add(startButton);
        add(dateiButton);
        add(jScrollPane);
        add(resetButton);
    }

    private void initComponents() {
        dateipfad = new JLabel("Dateipfad:");
        arbeitszeit = new JLabel("Arbeitszeit:");
        dateipfadTextfeld = new FocusTextField(DATEIPFAD_BREITE);
        arbeitszeitTextfeld = new FocusTextField(ARBEITSZEIT_BREITE);
        einstellungsButton = new JButton("Einstellungen öffnen");
        einstellungsButton.addActionListener(new EinstellungenListener(this));
        startButton = new JButton("Start");
        dateipfadTextfeld.getDocument().addDocumentListener(new PfadListener(this));
        startButton.addActionListener(new StartListener(this));
        dateiButton = new JButton("Datei auswählen...");
        dateiButton.addActionListener(new DateiListener(this));
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
        jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.add(jPanel);
        jScrollPane.setViewportView(jPanel);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setPreferredSize(new Dimension(SCROLL_WIDTH, SCROLL_HEIGHT));
        resetButton = new JButton("Einstellungen zurücksetzen");
        resetButton.addActionListener(new ResetListener(this));

        JLayeredPane jLayeredPane = getLayeredPane();

        String einstellungenAktion = "Settings";
        KeyStroke einstellungenStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(einstellungenStroke, einstellungenAktion);
        jLayeredPane.getActionMap().put(einstellungenAktion, new EinstellungenAction(this));

        String resetAktion = "Reset";
        KeyStroke resetStroke = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(resetStroke, resetAktion);
        jLayeredPane.getActionMap().put(resetAktion, new ResetAction(this));

        String openAction = "Open";
        KeyStroke openStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        jLayeredPane.getActionMap().put(openAction, new DateiAction(this));
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(openStroke, openAction);

        fillView();
    }

    public void fillView() {
        arbeitszeitTextfeld.setText(String.valueOf(Konfiguration.getArbeitszeit()));
        boolean ordentlicheExcelDatei = ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad(), this);
        startButton.setEnabled(ordentlicheExcelDatei);
        if (ordentlicheExcelDatei) {
            dateipfadTextfeld.setText(Konfiguration.getDateiPfad());
            new SheetSelektor(this);
        }
    }
}