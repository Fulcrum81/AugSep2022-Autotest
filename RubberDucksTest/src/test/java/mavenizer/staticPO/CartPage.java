package mavenizer.staticPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;

public class CartPage {
    public static String getEmptyBinMessage(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CartPage.emptyBinMessage")).getText();
    }

}
