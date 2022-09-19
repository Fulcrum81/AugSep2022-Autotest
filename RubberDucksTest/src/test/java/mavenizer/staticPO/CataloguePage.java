package mavenizer.staticPO;

import mavenizer.helpers.Zarytski.Builder;
import mavenizer.helpers.LocatorHelper;
import mavenizer.helpers.Zarytski.Waits;
import org.openqa.selenium.WebDriver;

public class CataloguePage {
    public static String getCartQuantityOnRightTopCorner(WebDriver driver){
        return driver.findElement(LocatorHelper.getLocator("CataloguePage.cartQuantity")).getText();
    }
    public static String getCartAmountOnRightTopCorner(WebDriver driver){
        return driver.findElement(LocatorHelper.getLocator("CataloguePage.cartAmount")).getText();
    }

    public static void goToCartPage(WebDriver driver){
        driver.findElement(LocatorHelper.getLocator("CataloguePage.cartLink")).click();
    }

    public static void addOneElementToBin(WebDriver driver) {
        Waits.implicitWaitPageLoadTimeout(driver);
        driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
    }

    public static void addTreeElementsToBin(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", "1");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.goToHome")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.redDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", "2");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.goToHome")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.blueDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
    }

    public static void addFiveSameElements(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuck")).click();
        Builder.selectAll(driver, "CataloguePage.quantityOfElements");
        Builder.deleteValue(driver, "CataloguePage.quantityOfElements");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.quantityOfElements")).sendKeys("5");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity","5");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.cartLink")).click();
    }

}
