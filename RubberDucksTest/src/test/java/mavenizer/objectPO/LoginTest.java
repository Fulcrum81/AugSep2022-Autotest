package mavenizer.objectPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        assertTrue(driver.findElement(login.loginForm).isDisplayed(), "LogIn Form is not displayed");
    }

    @Test
    public void loginAndPasswordFieldsDisplayed() {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.waitForPageLoadMethod();
        assertTrue(driver.findElement(login.inputEmail).isDisplayed(), "Login field is not displayed");
        assertTrue(driver.findElement(login.inputPassword).isDisplayed(), "Password field is not displayed");

    }

    @Test(dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithValidCredentialsTest (String email, String password) {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.attemptToLogIn(email, password);
        login.waitForPageLoadMethod();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(login.resultMessageSuccess)
                        .getText().contains(login.returnMessageIfLoggedIn()),
                "Text message about success authorization is wrong");
        softAssert.assertTrue(driver.findElement(login.sectionAccountForLoggedUser).isDisplayed(),
                "Section Account is not displayed");
        softAssert.assertAll();
    }

    @Test (dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithRememberMeTest(String email, String password) {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        driver.findElement(login.rememberMeButton).click();
        login.attemptToLogIn(email, password);
        login.waitForPageLoadMethod();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(login.resultMessageSuccess)
                        .getText().contains(login.returnMessageIfLoggedIn()),
                "Text message about success authorization is wrong");
        softAssert.assertTrue(driver.findElement(login.sectionAccountForLoggedUser).isDisplayed(),
                "Section Account is not displayed");
        softAssert.assertAll();

    }

}
