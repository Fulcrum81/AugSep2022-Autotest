package mavenizer.staticPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TablesPage {
    public CartPageTable example1;


    WebDriver driver;
    public TablesPage(WebDriver driver) {
        this.driver = driver;
        example1 = new CartPageTable (driver.findElement(LocatorHelper.getLocator("CartPage.tableOrderSummary")));
    }


}
