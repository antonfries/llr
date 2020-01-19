package Gui;

import Listener.StartListener;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Gui extends JFrame {

    public JTextField dateipfadTextfeld;
    public JTextField arbeitszeitTextfeld;
    private JLabel dateipfad;
    private JLabel arbeitszeit;
    private JButton einstellungsButton;
    private JButton startButton;

    public Gui() throws IOException {
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
        setVisible(true);
    }

    private void initComponents() {
        Konfiguration konfiguration = new Konfiguration();
        dateipfad = new JLabel("Dateipfad:");
        arbeitszeit = new JLabel("Arbeitszeit:");
        dateipfadTextfeld = new JTextField(25);
        dateipfadTextfeld.setText(Konfiguration.Mappe);
        arbeitszeitTextfeld = new JTextField(30);
        arbeitszeitTextfeld.setText(String.valueOf(konfiguration.getStunden()));
        einstellungsButton = new JButton("Einstellungen öffnen");
        einstellungsButton.addActionListener(e1 -> new GuiEinstellungen("Einstellungen"));
        startButton = new JButton("Start");
        startButton.addActionListener(new StartListener(Gui.this));
    }
}