import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Gui extends JFrame {

    private JLabel dateipfad;
    private JLabel arbeitszeit;

    private JButton einstellungsButton;
    private JButton startButton;

    public JTextField dateipfadTextfeld;
    public JTextField arbeitszeitTextfeld;

    public Gui(String titel) throws IOException {
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

    private void initComponents() throws IOException {
        Einstellungen einstellungen = new Einstellungen();
        dateipfad = new JLabel("Dateipfad:");
        arbeitszeit = new JLabel("Arbeitszeit:");

        dateipfadTextfeld = new JTextField(25);
        dateipfadTextfeld.setText(Einstellungen.Mappe);
        arbeitszeitTextfeld = new JTextField(30);
        arbeitszeitTextfeld.setText(String.valueOf(einstellungen.getStunden()));

        startButton = new JButton("Start");
        einstellungsButton = new JButton("Einstellungen öffnen");

        einstellungsButton.addActionListener(e1 -> new GuiEinstellungen("Einstellungen"));
        startButton.addActionListener(new StartListener(Gui.this));
    }
}