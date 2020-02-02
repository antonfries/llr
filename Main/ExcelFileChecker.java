package Main;

import java.io.File;
import java.util.Optional;

public class ExcelFileChecker {

    public static final String EXCEL_ENDUNG = "xlsx";

    public static boolean checkExcelFile(String dateiPfad) {
        return new File(dateiPfad).exists() &&
                getExtensionByStringHandling(dateiPfad).equals(EXCEL_ENDUNG);
    }

    private static String getExtensionByStringHandling(String dateiPfad) {
        Optional<String> optional = Optional.ofNullable(dateiPfad)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(dateiPfad.lastIndexOf(".") + 1));
        return optional.orElse("");
    }
}
