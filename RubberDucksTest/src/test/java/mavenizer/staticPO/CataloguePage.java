package mavenizer.staticPO;

import mavenizer.helpers.LocatorHelper;
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

}
