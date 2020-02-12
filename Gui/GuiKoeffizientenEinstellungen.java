package Gui;

import Action.CloseAction;
import Action.KoeffizientenEinstellungenSpeichernAction;
import Listener.GeneralStartListener;
import Listener.KoeffizientenSpeichernListener;
import Main.ExcelFileChecker;
import Main.Konfiguration;
import Main.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GuiKoeffizientenEinstellungen extends JFrame {

    public JTextField[] grenzeTextfeldListe;
    public JTextField[] koeffizientTextfeldListe;
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
        setTitle("Koeffizienten-Einstellungen");
        setLayout(new FlowLayout());
        setSize(800, 300);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        jPanel.add(grenzeText);
        for (JTextField grenzeTextfeld : grenzeTextfeldListe) {
            jPanel.add(grenzeTextfeld);
        }
        jPanel.add(koeffizientText);
        for (JTextField koeffizienttextfeld : koeffizientTextfeldListe) {
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
        grenzeTextfeldListe = new JTextField[Konfiguration.getKoeffizientAnzahl() + 1];
        koeffizientTextfeldListe = new JTextField[Konfiguration.getKoeffizientAnzahl()];
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            grenzeTextfeldListe[i] = new JTextField(4);
            if (i == Konfiguration.getKoeffizientAnzahl()) {
                grenzeTextfeldListe[i].setToolTipText("Wert -1 setzen, falls es keine obere Grenze gibt");
            }
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i] = new JTextField(4);
        }
        jPanel = new JPanel();
        jPanel.setLayout(new WrapLayout());
        jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.add(jPanel);
        jScrollPane.setViewportView(jPanel);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setPreferredSize(new Dimension(700, 200));

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
            grenzeTextfeldListe[i].setText(Konfiguration.grenzeNode.get(String.valueOf(i), "0.0"));
        }
        for (int i = 0; i < Konfiguration.getKoeffizientAnzahl(); i++) {
            koeffizientTextfeldListe[i].setText(Konfiguration.koeffizientNode.get(String.valueOf(i), String.valueOf(Konfiguration.getStandardKoeffizient())));
        }
    }
}
