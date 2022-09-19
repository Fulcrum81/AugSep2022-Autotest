package mavenizer.helpers.Zarytski;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Builder {


    public static void selectAll(WebDriver driver, String locator) {

        WebElement place = driver.findElement(LocatorHelper.getLocator(locator));
        Actions builder = new Actions(driver);
        builder.doubleClick(place).perform();
    }

    public static void deleteValue(WebDriver driver, String locator) {
        WebElement place = driver.findElement(LocatorHelper.getLocator(locator));
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.BACK_SPACE).perform();
    }
}
