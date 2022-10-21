package mavenizer.staticPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;

public class ElementsPage {

    public ListOfElements check1;

    WebDriver driver;
    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        check1 = new ListOfElements(driver.findElement(LocatorHelper.getLocator("ElementsPage")));
    }

}
