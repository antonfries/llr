package Main;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.POIXMLException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static Main.Excel.BUFFER_SIZE;
import static Main.Excel.ROW_CACHE_SIZE;

public class ExcelFileChecker {

    public static final String EXCEL_ENDUNG = "xlsx";

    public static boolean checkExcelFile(String dateiPfad, JFrame jFrame) {
        File datei = new File(dateiPfad);
        boolean fileExistence = datei.exists();
        boolean fileIsExcel = getExtensionByStringHandling(dateiPfad).equals(EXCEL_ENDUNG);
        if (fileExistence && fileIsExcel) {
            try {
                new StreamingReader.Builder()
                        .rowCacheSize(ROW_CACHE_SIZE)
                        .bufferSize(BUFFER_SIZE)
                        .open(datei).close();
                return true;
            } catch (POIXMLException e) {
                Validation.showStrictErrorMessage(jFrame);
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static String getExtensionByStringHandling(String dateiPfad) {
        Optional<String> optional = Optional.ofNullable(dateiPfad)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(dateiPfad.lastIndexOf(".") + 1));
        return optional.orElse("");
    }
}
