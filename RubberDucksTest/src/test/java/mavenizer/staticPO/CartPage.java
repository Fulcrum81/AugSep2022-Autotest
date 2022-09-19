package mavenizer.staticPO;

import mavenizer.helpers.Zarytski.Builder;
import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;

public class CartPage {
    public static void reduceTheAmountToOne(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).click();
        Builder.selectAll(driver,"CartPage.quantityOfElements");
        Builder.deleteValue(driver,"CartPage.quantityOfElements");
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).sendKeys("1");
        driver.findElement(LocatorHelper.getLocator("CartPage.buttonUpdate")).click();

    }

    public static void increaseItemsInTheCartByFive(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).click();
        Builder.selectAll(driver,"CartPage.quantityOfElements");
        Builder.deleteValue(driver,"CartPage.quantityOfElements");
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).sendKeys("5");
        driver.findElement(LocatorHelper.getLocator("CartPage.buttonUpdate")).click();
    }
}
