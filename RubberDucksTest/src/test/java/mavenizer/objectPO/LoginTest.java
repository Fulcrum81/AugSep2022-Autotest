package mavenizer.objectPO;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertTrue;


public class LoginTest extends TestBaseLogin {

    @Test()
    public void logInFormIsDisplayedTest () {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(login.loginForm)).isDisplayed(),
                "LogIn Form is not displayed");
    }


    @Test(dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithValidCredentialsTest (String email, String password) {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.attemptToLogIn(email, password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(login.resultMessageSuccess))
                        .getText().contains(login.expectedResultMessageSuccessText),
                "Text message about success authorization is wrong");
        softAssert.assertTrue(wait.until(ExpectedConditions
                .visibilityOfElementLocated(login.sectionAccountForLoggedUser)).isDisplayed(),
                "Section Account is not displayed");
        softAssert.assertAll();
    }




}
