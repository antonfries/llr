import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GuiKoeffizientenEinstellungen extends JFrame {

    private static int anzahlDerKoeffizienten;
    private JLabel koeffizienten;
    private JLabel grenzen;
    private JTextField[] grenzenTextfelder = new JTextField[21];
    private JTextField[] koeffizientenTextfelder = new JTextField[20];
    private JButton speichernButton;

    public GuiKoeffizientenEinstellungen(String titel, int anzahlDerKoeffizienten) {
        setTitle(titel);
        GuiKoeffizientenEinstellungen.anzahlDerKoeffizienten = anzahlDerKoeffizienten;

        setLayout(new FlowLayout());
        setSize(800, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponents();

        add(grenzen);
        for (int i = 0; i < anzahlDerKoeffizienten; i++) {
            add(grenzenTextfelder[i]);
        }
        add(koeffizienten);
        for (int i = 0; i < anzahlDerKoeffizienten; i++) {
            add(koeffizientenTextfelder[i]);
        }
        add(speichernButton);
        setVisible(true);
    }

    private void initComponents() {
        grenzen = new JLabel("Grenzen:");
        koeffizienten = new JLabel("Koeffizienten:");
        speichernButton = new JButton("Speichern");
        speichernButton.addActionListener(e2 -> {
            try {
                // TODO: hardcoded
                File grenzenDatei = new File("C://ilyabykov//Spaß//GrenzenEinst.txt");
                File koeffizientenDatei = new File("C://ilyabykov//Spaß//KoeffEinst.txt");
                PrintWriter grenzenPw = new PrintWriter(new FileWriter(grenzenDatei));
                PrintWriter koeffizientenPw = new PrintWriter(new FileWriter(koeffizientenDatei));
                String[] grenzenTextfelder = new String[21];
                String[] koeffizientenTextfelder = new String[20];
                for (int i = 0; i < anzahlDerKoeffizienten; i++) {
                    grenzenTextfelder[i] = GuiKoeffizientenEinstellungen.this.grenzenTextfelder[i].getText();
                    grenzenPw.println(grenzenTextfelder[i]);
                }
                for (int j = 0; j < anzahlDerKoeffizienten; j++) {
                    koeffizientenTextfelder[j] = GuiKoeffizientenEinstellungen.this.koeffizientenTextfelder[j].getText();
                    koeffizientenPw.println(koeffizientenTextfelder[j]);
                }
                grenzenPw.flush();
                grenzenPw.close();
                koeffizientenPw.flush();
                koeffizientenPw.close();
            } catch (IOException fe) {
                fe.printStackTrace();
            }
        });
        for (int i = 0; i < anzahlDerKoeffizienten; i++) {
            grenzenTextfelder[i] = new JTextField(4);
        }
        for (int i = 0; i < anzahlDerKoeffizienten; i++) {
            koeffizientenTextfelder[i] = new JTextField(4);
        }
    }
}
