package mavenizer.staticPO;

import io.qameta.allure.Step;
import mavenizer.helpers.Ducks;
import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @Step("Add some amount some category of ducks")
    public static void addDucksToCart(WebDriver driver, Ducks duckType, String ducksQuantity) {
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
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartInputQuantity")).sendKeys(ducksQuantity);
        if (duckType == Ducks.YELLOW) { //Fill size
            driver.findElement(LocatorHelper.getLocator("CataloguePage.yellowDuckSizeCombo")).sendKeys("Small");
        }
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartButton")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(LocatorHelper.getLocator("CataloguePage.cartAmountStyle")));
    }

}
