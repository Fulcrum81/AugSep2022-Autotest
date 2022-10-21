package mavenizer.helpers.Zarytski;
import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Actions {
    public static void selectAll(WebDriver driver, String locator) {

        WebElement place = driver.findElement(LocatorHelper.getLocator(locator));
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        builder.doubleClick(place).perform();
    }

    public static void deleteValue(WebDriver driver, String locator) {
        WebElement place = driver.findElement(LocatorHelper.getLocator(locator));
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        builder.sendKeys(Keys.BACK_SPACE).perform();
    }

    public static void enterText(WebDriver driver,String locator) {
        WebElement field = driver.findElement(LocatorHelper.getLocator(locator));
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        builder.sendKeys(Keys.ENTER);
    }
}
