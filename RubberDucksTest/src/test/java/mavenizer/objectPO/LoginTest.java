package mavenizer.objectPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class LoginTest extends TestBaseLogin {

    @Test()
    public void logInFormIsDisplayedTest () {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.waitForPageLoadMethod();
        login.loginFormIsDisplayed();
    }

    @Test
    public void loginAndPasswordFieldsDisplayed() {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.waitForPageLoadMethod();
        login.inputEmailFieldIsDisplayed();
        login.inputPasswordFieldIsDisplayed();
    }

    @Test(dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithValidCredentialsTest (String email, String password) {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.attemptToLogIn(email, password);
        login.waitForPageLoadMethod();
        login.successAuthorizationMessageAfterLoggedInIsDisplayed();
        login.sectionAccountForLoggedUserIsDisplayed();
    }

    @Test (dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithRememberMeTest(String email, String password) {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.rememberMeButtonClick();
        login.attemptToLogIn(email, password);
        login.waitForPageLoadMethod();
        login.successAuthorizationMessageAfterLoggedInIsDisplayed();
        login.sectionAccountForLoggedUserIsDisplayed();
    }

}
