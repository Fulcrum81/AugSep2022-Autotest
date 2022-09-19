package mavenizer.staticPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TablesPage {
    CartPageTable example1;
    CartPageTable example2;

    WebDriver driver;
    public TablesPage(WebDriver driver) {
        this.driver = driver;
        example1 = new CartPageTable (driver.findElement(By.xpath("//div[@id='box-checkout-summary']//tbody")));
    }


}
