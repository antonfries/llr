package Main;

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
}
