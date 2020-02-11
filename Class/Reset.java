package Class;

import Gui.Gui;
import Main.Konfiguration;

import javax.swing.*;
import java.util.Locale;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Reset {
    public Reset(Gui gui) {
        Locale locale = Locale.getDefault();
        Object[] options = {
                UIManager.getString("OptionPane.yesButtonText", locale),
                UIManager.getString("OptionPane.noButtonText", locale)
        };
        int entscheidung = JOptionPane.showOptionDialog(gui,
                "Einstellungen wirklich zurücksetzen?",
                "Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[1]);
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
