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
                "Bitte geben Sie Buchstaben von A-Z an!",
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

    public static void showMinimalErrorMessage(JFrame jframe) {
        showErrorMessage(
                jframe,
                "Der Minimal-Summand darf nicht größer als der Standard-Summand sein!",
                Konfiguration.MINIMAL_SUMMAND + "-Validation"
        );
    }

    public static void showMaximalErrorMessage(JFrame jframe) {
        showErrorMessage(
                jframe,
                "Der Maximal-Summand darf nicht kleiner als der Standard-Summand sein!",
                Konfiguration.MAXIMAL_SUMMAND + "-Validation"
        );
    }

    public static void showStandardErrorMessage(JFrame jframe) {
        showErrorMessage(
                jframe,
                "Der Standard-Summand muss zwischen Minimal- und Maximal-Summand liegen!",
                Konfiguration.STANDARD_SUMMAND + "-Validation"
        );
    }

    public static void showZeilenEndeErrorMessage(JFrame jframe) {
        showErrorMessage(
                jframe,
                "Das Zeilenende darf nicht größer als die Anzahl der Zeilen sein!",
                "Rechner-Validation"
        );
    }
}
