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

    public Gui() {
        setTitle("LLR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        add(einstellungsButton);
        add(dateipfad);
        add(dateipfadTextfeld);
        add(arbeitszeit);
        add(arbeitszeitTextfeld);
        add(startButton);
        add(dateiButton);
        setVisible(true);
        sheetContainer = new JPanel();
        add(sheetContainer);
        new SheetSelektor(this);
    }

    private void initComponents() {
        Konfiguration konfiguration = new Konfiguration();
        dateipfad = new JLabel("Dateipfad:");
        arbeitszeit = new JLabel("Arbeitszeit:");
        dateipfadTextfeld = new JTextField(25);
        dateipfadTextfeld.setText(konfiguration.getPfad());
        arbeitszeitTextfeld = new JTextField(30);
        arbeitszeitTextfeld.setText(String.valueOf(konfiguration.getStunden()));
        einstellungsButton = new JButton("Einstellungen öffnen");
        einstellungsButton.addActionListener(e1 -> new GuiEinstellungen("Einstellungen"));
        startButton = new JButton("Start");
        if (konfiguration.getPfad().equals("")) {
            startButton.setEnabled(false);
        }
        dateipfadTextfeld.getDocument().addDocumentListener(new PfadListener(Gui.this));
        startButton.addActionListener(new StartListener(Gui.this));
        dateiButton = new JButton("Datei auswählen...");
        dateiButton.addActionListener(new DateiListener(Gui.this));
    }
}