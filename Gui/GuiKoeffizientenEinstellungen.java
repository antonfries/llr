package Gui;

import Action.CloseAction;
import Action.KoeffizientenEinstellungenSpeichernAction;
import Listener.GeneralStartListener;
import Listener.KoeffizientenSpeichernListener;
import Main.ExcelFileChecker;
import Main.FocusTextField;
import Main.Konfiguration;
import Main.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GuiKoeffizientenEinstellungen extends JFrame {

    public static final String TITEL = "Koeffizienten-Einstellungen";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 300;
    public static final int SCROLL_WIDTH = 700;
    public static final int SCROLL_HEIGHT = 200;

    public FocusTextField[] grenzeTextfeldListe;
    public FocusTextField[] koeffizientTextfeldListe;
    private JScrollPane jScrollPane;
    private JPanel jPanel;
    private JLabel koeffizientText;
    private JLabel grenzeText;
    private JButton speichernButton;
    private JButton startButton;

    public GuiKoeffizientenEinstellungen() {
        init();
        initComponents();
        addComponents();
        getRootPane().setDefaultButton(startButton);
        setVisible(true);
    }

    private void init() {
        setTitle(TITEL);
        setLayout(new FlowLayout());
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        jPanel.add(grenzeText);
        for (FocusTextField grenzeTextfeld : grenzeTextfeldListe) {
            jPanel.add(grenzeTextfeld);
        }
        jPanel.add(koeffizientText);
        for (FocusTextField koeffizienttextfeld : koeffizientTextfeldListe) {
            jPanel.add(koeffizienttextfeld);
        }
        add(jScrollPane);
        add(speichernButton);
        add(startButton);
    }

    private void initComponents() {
        grenzeText = new JLabel("Grenzen:");
        koeffizientText = new JLabel("Koeffizienten:");
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(new KoeffizientenSpeichernListener(this));
        startButton = new JButton("Start");
        startButton.setEnabled(ExcelFileChecker.checkExcelFile(Konfiguration.getDateiPfad(), this));
        startButton.addActionListener(new GeneralStartListener(this));
        grenzeTextfeldListe = new FocusTextField[Konfiguration.getKoeffizientAnzahl() + 1];
        koeffizientTextfeldListe = new FocusTextField[Konfiguration.getKoeffizientAnzahl()];
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            grenzeTextfeldListe[i] = new FocusTextField(4);
            if (i == Konfiguration.getKoeffizientAnzahl()) {
                grenzeTextfeldListe[i].setToolTipText("Wert -1 setzen, falls es keine obere Grenze geben soll");
            }
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i] = new FocusTextField(4);
        }
        jPanel = new JPanel();
        jPanel.setLayout(new WrapLayout());
        jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.add(jPanel);
        jScrollPane.setViewportView(jPanel);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setPreferredSize(new Dimension(SCROLL_WIDTH, SCROLL_HEIGHT));

        JLayeredPane jLayeredPane = getLayeredPane();

        String saveAction = "Save";
        KeyStroke speichernStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(speichernStroke, saveAction);
        jLayeredPane.getActionMap().put(saveAction, new KoeffizientenEinstellungenSpeichernAction(this));

        String closeAction = "Close";
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        jLayeredPane.getActionMap().put(closeAction, new CloseAction(this));
        jLayeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, closeAction);
        fillView();
    }

    public void fillView() {
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            grenzeTextfeldListe[i].setText(Konfiguration.grenzeNode.get(String.valueOf(i), String.valueOf(0.0)));
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i].setText(Konfiguration.koeffizientNode.get(String.valueOf(i), String.valueOf(Konfiguration.getStandardKoeffizient())));
        }
    }
}
