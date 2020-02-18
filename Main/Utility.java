package Main;

import java.util.prefs.BackingStoreException;

public class Utility {

    public static double round2Digits(double value) {
        return Math.round(value * 100d) / 100d;
    }

    public static double parseDouble(String value) {
        return Double.parseDouble(value.replace(',', '.'));
    }

    public static double parseDoubleIgnoreError(String value) {
        try {
            return parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static void removeOldGrenzen(int koeffizientAnzahl) {
        boolean unlimited = Konfiguration.grenzeNode.getDouble(String.valueOf(Konfiguration.getKoeffizientAnzahl()), 0.0) == -1;
        if (unlimited && koeffizientAnzahl != 0) {
            Konfiguration.grenzeNode.putDouble(String.valueOf(koeffizientAnzahl), -1.0);
        }
        try {
            for (String g : Konfiguration.grenzeNode.keys()) {
                if (Integer.parseInt(g) < 0 || Integer.parseInt(g) > koeffizientAnzahl) {
                    Konfiguration.grenzeNode.remove(g);
                }
            }
            for (String k : Konfiguration.koeffizientNode.keys()) {
                if (Integer.parseInt(k) < 0 || Integer.parseInt(k) >= koeffizientAnzahl) {
                    Konfiguration.koeffizientNode.remove(k);
                }
            }
        } catch (BackingStoreException ignored) {
        }
    }

    public static void adjustGrenzen() {
        for (int i = 1; i < Konfiguration.getKoeffizientAnzahl() + 1; i++) {
            double current = Konfiguration.grenzeNode.getDouble(String.valueOf(i), 0.0);
            double previous = Konfiguration.grenzeNode.getDouble(String.valueOf(i - 1), 0.0);
            if (current <= previous && i != Konfiguration.getKoeffizientAnzahl()) {
                Konfiguration.grenzeNode.putDouble(String.valueOf(i), Utility.round2Digits(previous + 0.01));
            }
            // TODO: falls letzte Grenze nicht -1 ist, wird diese nicht automatisch erhöht
            // Das funktioniert, weil die Anpassung erst nach der Abfrage ausgeführt wird
            if (i == Konfiguration.getKoeffizientAnzahl() && current == 0.0) {
                Konfiguration.grenzeNode.putDouble(String.valueOf(i), -1.0);
            }
        }
    }
}
