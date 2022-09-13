package mavenizer.staticPO;

import mavenizer.helpers.Ducks;
import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;

public class CataloguePage {
    public static String getCartQuantityOnRightTopCorner(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CataloguePage.cartQuantity")).getText();
    }

    public static String getCartAmountOnRightTopCorner(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CataloguePage.cartAmount")).getText();
    }

    public static void goToCartPage(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CataloguePage.cartLink")).click();
    }

    public static void addDucksToCart(WebDriver driver, Ducks duckType, String ducksQuantity) throws InterruptedException {
        switch (duckType) {
            case RED:
                driver.findElement(LocatorHelper.getLocator("CataloguePage.redDuckLink")).click();
                break;
            case BLUE:
                driver.findElement(LocatorHelper.getLocator("CataloguePage.blueDuckLink")).click();
                break;
            case GREEN:
                driver.findElement(LocatorHelper.getLocator("CataloguePage.greenDuckLink")).click();
                break;
            case PURPLE:
                driver.findElement(LocatorHelper.getLocator("CataloguePage.purpleDuckLink")).click();
                break;
            case YELLOW:
                driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/subcategory-c-2/");
                driver.findElement(LocatorHelper.getLocator("CataloguePage.yellowDuckLink")).click();
                break;
        }
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartInputQuantity")).clear();
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartInputQuantity")).sendKeys(ducksQuantity);
        if (duckType==Ducks.YELLOW){ //Fill size
            driver.findElement(LocatorHelper.getLocator("CataloguePage.yellowDuckSizeCombo")).sendKeys("Small");
        }
        driver.findElement(LocatorHelper.getLocator("CataloguePage.addToCartButton")).click();
        Thread.sleep(2000); //ajax call wait only method :(
    }

}
