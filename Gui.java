import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Gui extends JFrame {

    private JLabel dateipfad;
    private JLabel arbeitszeit;

    private JButton einstellungsButton;
    private JButton startButton;

    private JTextField dateipfadTextfeld;
    private JTextField arbeitszeitTextfeld;

    public Gui(String titel) {
        setTitle(titel);
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
        dateipfad = new JLabel("Dateipfad:");
        arbeitszeit = new JLabel("Arbeitszeit:");

        dateipfadTextfeld = new JTextField(20);
        dateipfadTextfeld.setText(Einstellungen.Mappe);
        arbeitszeitTextfeld = new JTextField(30);
        arbeitszeitTextfeld.setText("10");

        startButton = new JButton("Start");
        einstellungsButton = new JButton("Einstellungen öffnen");

        einstellungsButton.addActionListener(e1 -> new GuiEinstellungen("Einstellungen"));
        startButton.addActionListener(e2 -> {
            double endergebnis = 0.0;
            try {
                endergebnis = Rechner.rechnen();
                JOptionPane.showMessageDialog(Gui.this, "Lager-Leistung:    " + endergebnis, "Ergebnis",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                // TODO: hardcoded
                File arbeitszeitenDatei = new File("C://ilyabykov//Spaß//Arbeitszeit.txt");
                PrintWriter pw = new PrintWriter(new FileWriter(arbeitszeitenDatei));
                String arbeitszeit = Gui.this.arbeitszeitTextfeld.getText();
                pw.println(arbeitszeit);
                pw.flush();
                pw.close();
            } catch (IOException fe) {
                fe.printStackTrace();
            }
        });
    }
}