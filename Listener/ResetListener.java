package Listener;

import Gui.Gui;
import Main.Konfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ResetListener implements ActionListener {
    private Gui gui;

    public ResetListener(Gui gui) {
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        int entscheidung = JOptionPane.showConfirmDialog(gui,
                "Einstellungen wirklich zurücksetzen?",
                "Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE);
        try {
            if (entscheidung == JOptionPane.YES_OPTION) {
                Konfiguration.basisNode.removeNode();
                Konfiguration.grenzeNode.removeNode();
                Konfiguration.koeffizientNode.removeNode();
                Konfiguration.basisNode = Preferences.userRoot().node("Basis");
                Konfiguration.grenzeNode = Preferences.userRoot().node("Grenzen");
                Konfiguration.koeffizientNode = Preferences.userRoot().node("Koeffizienten");
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }
}
