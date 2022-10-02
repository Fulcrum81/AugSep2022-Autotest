package mavenizer.objectPO;

import mavenizer.helpers.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public class TestBaseLogin {

    WebDriver driver;
    LogInForm login;

    @BeforeMethod
    protected void startTest () {
        Browser browser = Browser.getEnumByLabel(System.getProperty("browser", Browser.CHROME.getBrowserName()));
        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("lang=en-GB");
                driver = new ChromeDriver(options);
                break;
            case FIREFOX: //driver = new SafariDriver(); break; --> add new Safari driver when it will be necessary, import library, add dependency in pom file
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
    }


    @AfterMethod
    protected void finishTest () {
        driver.quit();
    }


}
