package mavenizer.helpers.Zarytski;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {


//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(new ExpectedCondition<Boolean>() {
//                       @Override
//                       public Boolean apply(WebDriver webDriver) {
//                           JavascriptExecutor js = (JavascriptExecutor) webDriver;
//                           return (Boolean) js.executeScript("return jQuery.active == 0");
//                       }
//                   });

    public static void implicitWaitPageLoadTimeout(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public static void explicitWaitTextToBe(WebDriver driver, String locator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe((LocatorHelper.getLocator(locator)),  value));
    }



}

