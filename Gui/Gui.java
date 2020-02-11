package Gui;

import Action.DateiAction;
import Action.EinstellungenAction;
import Action.ResetAction;
import Listener.*;
import Main.ExcelFileChecker;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Gui extends JFrame {

    public JTextField dateipfadTextfeld;
    public JTextField arbeitszeitTextfeld;
    public JButton startButton;
    public ButtonGroup sheetListe;
    public JPanel sheetContainer;
    public JScrollPane jScrollPane;
    private JLabel dateipfad;
    private JLabel arbeitszeit;
    private JButton einstellungsButton;
    private JButton dateiButton;
    private JButton resetButton;

    public Gui() {
        // TODO: [Prio] Konzept für Anzeige jedweder Fehlermeldung in Dialog
        init();
        initComponents();
        addComponents();
        getRootPane().setDefaultButton(startButton);
        setVisible(true);
    }

    private void init() {
        setTitle("LLR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 315);
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
        dateipfadTextfeld = new JTextField(20);
        arbeitszeitTextfeld = new JTextField(30);
        einstellungsButton = new JButton("Einstellungen öffnen");
        einstellungsButton.addActionListener(e1 -> new GuiEinstellungen(Gui.this));
        startButton = new JButton("Start");
        dateipfadTextfeld.getDocument().addDocumentListener(new PfadListener(Gui.this));
        startButton.addActionListener(new StartListener(Gui.this));
        dateiButton = new JButton("Datei auswählen...");
        dateiButton.addActionListener(new DateiListener(Gui.this));
        sheetContainer = new JPanel();
        sheetContainer.setLayout(new BoxLayout(sheetContainer, BoxLayout.PAGE_AXIS));
        jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.add(sheetContainer);
        jScrollPane.setViewportView(sheetContainer);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setPreferredSize(new Dimension(230, 174));
        resetButton = new JButton("Einstellungen zurücksetzen");
        resetButton.addActionListener(new ResetListener(Gui.this));

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