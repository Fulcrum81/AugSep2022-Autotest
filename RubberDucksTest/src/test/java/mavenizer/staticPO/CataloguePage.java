package mavenizer.staticPO;

import io.qameta.allure.Step;
import mavenizer.helpers.Ducks;
import mavenizer.helpers.Zarytski.Actions;
import mavenizer.helpers.LocatorHelper;
import mavenizer.helpers.Waits;
import mavenizer.helpers.Zarytski.WaitsZarytski;
import org.openqa.selenium.WebDriver;

public class CataloguePage {
    @Step("Return amount of ducks in the bin with currency sign")
    public static String getCartQuantityOnRightTopCorner(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CataloguePage.cartQuantity")).getText();
    }

    @Step("Return amount of ducks in the bin")
    public static String getCartAmountOnRightTopCorner(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CataloguePage.cartAmount")).getText();
    }

    @Step("Return unit price string include currency")
    public static String getUnitPrice(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CataloguePage.unitPrice")).getText();
    }

    @Step("Navigate to bin page")
    public static void goToCartPage(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CataloguePage.cartLink")).click();
    }

    public static void addOneElementToBin(WebDriver driver) {
        WaitsZarytski.implicitWaitPageLoadTimeout(driver);
        driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
    }

    public static void addTreeElementsToBin(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", "1");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.goToHome")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.redDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", "2");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.goToHome")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.blueDuck")).click();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
    }

    public static void addFiveSameElements(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuck")).click();
        Actions.selectAll(driver, "CataloguePage.quantityOfElements");
        Actions.deleteValue(driver, "CataloguePage.quantityOfElements");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.quantityOfElements")).sendKeys("5");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.buttonAddToCart")).click();
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity","5");
        driver.findElement(LocatorHelper.getLocator("CataloguePage.cartLink")).click();
    }

    @Step("Add some amount some category of ducks")
    public static void addDucksToCart(WebDriver driver, Ducks duckType, int ducksQuantity) {
        switch (duckType) {
            case RED:
                driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
                driver.findElement(LocatorHelper.getLocator("CataloguePage.redDuckLink")).click();
                break;
            case BLUE:
                driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
                driver.findElement(LocatorHelper.getLocator("CataloguePage.blueDuckLink")).click();
                break;
            case GREEN:
                driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
                driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuckLink")).click();
                break;
            case PURPLE:
                driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
                driver.findElement(LocatorHelper.getLocator("CataloguePage.purpleDuckLink")).click();
                break;
            case YELLOW:
                driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/");
                driver.findElement(LocatorHelper.getLocator("CataloguePage.yellowDuckLink")).click();
                break;
        }
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartInputQuantity")).clear();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartInputQuantity")).sendKeys(String.valueOf(ducksQuantity));
        if (duckType == Ducks.YELLOW) { //Fill size
            driver.findElement(LocatorHelper.getLocator("CataloguePage.yellowDuckSizeCombo")).sendKeys("Small");
        }
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartButton")).click();
        Waits.waitForAnimationEnds(driver, 5);
    }

}
