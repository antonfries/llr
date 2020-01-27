package Gui;

import Listener.DateiListener;
import Listener.PfadListener;
import Listener.SheetSelektor;
import Listener.StartListener;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    public JTextField dateipfadTextfeld;
    public JTextField arbeitszeitTextfeld;
    public JButton startButton;
    public ButtonGroup sheetListe;
    public JPanel sheetContainer;
    private JLabel dateipfad;
    private JLabel arbeitszeit;
    private JButton einstellungsButton;
    private JButton dateiButton;
    private Konfiguration konfiguration;

    public Gui() {
        konfiguration = new Konfiguration();
        init();
        initComponents();
        addComponents();
        setVisible(true);
    }

    private void init() {
        setTitle("LLR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);
        setResizable(false);
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
        add(sheetContainer);
    }

    private void initComponents() {
        dateipfad = new JLabel("Dateipfad:");
        arbeitszeit = new JLabel("Arbeitszeit:");
        dateipfadTextfeld = new JTextField(25);
        arbeitszeitTextfeld = new JTextField(30);
        einstellungsButton = new JButton("Einstellungen öffnen");
        einstellungsButton.addActionListener(e1 -> new GuiEinstellungen(Gui.this));
        startButton = new JButton("Start");
        dateipfadTextfeld.getDocument().addDocumentListener(new PfadListener(Gui.this));
        startButton.addActionListener(new StartListener(Gui.this));
        dateiButton = new JButton("Datei auswählen...");
        dateiButton.addActionListener(new DateiListener(Gui.this));
        // TODO: Dynamische Komponenten in JPanel flow-artig anordnen
        sheetContainer = new JPanel();
        fillView();
    }

    public void fillView() {
        konfiguration = new Konfiguration();
        arbeitszeitTextfeld.setText(String.valueOf(konfiguration.getStunden()));
        dateipfadTextfeld.setText(konfiguration.getPfad());
        startButton.setEnabled(!konfiguration.getPfad().equals(""));
        new SheetSelektor(this);
    }
}