package mavenizer.staticPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsPage {

    public ListOfElements check1;

    WebDriver driver;
    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        check1 = new ListOfElements(driver.findElement(By.xpath("//*[@class='shortcuts']")));
    }

}
