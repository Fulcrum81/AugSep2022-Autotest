package mavenizer.helpers;

import java.text.NumberFormat;
import java.util.Formatter;

public class StringHelper {
    public static String calculateTotals(String unitPrice, int quantity) {
        String clearedString = unitPrice.replace("$", "").replace("€", "")
                .replace("?", "").trim();
        float unitPriceFloatValue = Float.parseFloat(clearedString);
        Formatter formatter = new Formatter();
        formatter.format("%.2f", unitPriceFloatValue * quantity);
        return formatter.toString().replace(",", ".");
    }
}
