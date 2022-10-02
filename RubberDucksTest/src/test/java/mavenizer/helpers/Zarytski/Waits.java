package mavenizer.helpers.Zarytski;
import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DataTruncation;
import java.time.Duration;

public class Waits {
    public static void implicitWaitPageLoadTimeout(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public static void explicitWaitTextToBe(WebDriver driver, String locator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe((LocatorHelper.getLocator(locator)), value));
    }

    public static void explicitWaitNumberOfElementsToBe(WebDriver driver, String locator, int value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe((LocatorHelper.getLocator(locator)),  value));
    }

    public static void testic(WebDriver driver, String locator ) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(LocatorHelper.getLocator(locator)));

    }

    public static void explicitWaitInvisibilityOf(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.invisibilityOf(locator));
        WebElement text = driver.findElement(LocatorHelper.getLocator(locator));
        wait.until(ExpectedConditions.invisibilityOf(text));

    }

    public static void elementToBeClickable(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(LocatorHelper.getLocator(locator)));
    }

    public static void element(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement text = driver.findElement(By.xpath("//div[@class='warning']"));
        wait.until(ExpectedConditions.invisibilityOf(text));
    }



}

