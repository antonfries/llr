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
                "Das Zeilenende darf nicht kleiner als der Zeilenanfang sein!",
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

    public static void showMaximalErrorMessage(JFrame jframe) {
        showErrorMessage(
                jframe,
                "Die Minimal-Menge darf nicht kleiner als die Standard-Menge sein!",
                Konfiguration.MAXIMAL_MENGE + "-Validation"
        );
    }

    public static void showZeileAnfangErrorMessage(JFrame jframe) {
        showErrorMessage(
                jframe,
                "Der Zeilenanfang darf nicht größer als die Anzahl der Zeilen sein!",
                "Rechner-Validation"
        );
    }

    public static void showStrictErrorMessage(JFrame jFrame) {
        showErrorMessage(
                jFrame,
                "Speichern Sie die Mappe bitte nicht als Strict Open XML-Arbeitsmappe!",
                "Excel-Validation"
        );
    }

    public static void showSelectionErrorMessage(JFrame jFrame) {
        showErrorMessage(
                jFrame,
                "Bitte nutzen Sie den vorgeschlagenen Excel-Filter zur Selektion von Arbeitsmappen!",
                "Datei-Validation"
        );
    }
}
