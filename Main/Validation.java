package Main;

import javax.swing.*;

public class Validation {

    public static void showErrorMessage(JFrame jframe, String nachricht, String titel) {
        JOptionPane.showMessageDialog(
                jframe,
                nachricht,
                titel,
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void showZahlenErrorMessage(JFrame jFrame, String spalte) {
        showErrorMessage(
                jFrame,
                "Bitte geben Sie Zahlen ein!",
                spalte + "-Validation"
        );
    }

    public static void showNegativErrorMessage(JFrame jFrame, String spalte) {
        showErrorMessage(
                jFrame,
                "Bitte geben Sie positive Zahlen ein!",
                spalte + "-Validation"
        );
    }

    public static void showSpaltenErrorMessage(JFrame jFrame, String spalte) {
        showErrorMessage(
                jFrame,
                "Bitte geben Sie für alle Spalten Buchstaben von A-Z an!",
                spalte + "-Validation"
        );
    }

    public static void showZeileErrorMessage(JFrame jFrame) {
        showErrorMessage(
                jFrame,
                "Das Zeilenende darf nicht kleiner als der Zeilenanfang!",
                "Zeilen-Validation"
        );
    }

    public static void showDuplicateErrorMessage(JFrame jFrame) {
        showErrorMessage(
                jFrame,
                "Menge und Wert können nicht aus der selben Spalte ausgelesen werden!",
                "Menge/Wert-Validation"
        );
    }
}
