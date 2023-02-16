package mavenizer.objectPO;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;

public class LoginTest extends TestBaseLogin {

    @Test()
    public void logInFormIsDisplayedTest () {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.waitForPageLoadMethod();
        assertTrue(login.loginFormIsDisplayed(), "LogIn Form is not displayed");
    }

    @Test
    public void loginAndPasswordFieldsDisplayed() {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.waitForPageLoadMethod();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(login.inputEmailFieldIsDisplayed(), "Input Email field is not displayed");
        softAssert.assertTrue(login.inputPasswordFieldIsDisplayed(), "Password field is not displayed");
        softAssert.assertAll();
    }

    @Test(dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithValidCredentialsTest (String email, String password) {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.attemptToLogIn(email, password);
        login.waitForPageLoadMethod();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(login.successAuthorizationMessageAfterLoggedInIsDisplayed(),
                "Text message about success authorization is wrong");
        softAssert.assertTrue(login.sectionAccountForLoggedUserIsDisplayed(),
                "Section Account is not displayed");
        softAssert.assertAll();
    }

    @Test (dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithRememberMeTest(String email, String password) {
        LogInForm login = new LogInForm(driver);
        driver.get(login.URL_MAIN_PAGE);
        login.rememberMeButtonClick();
        login.attemptToLogIn(email, password);
        login.waitForPageLoadMethod();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(login.successAuthorizationMessageAfterLoggedInIsDisplayed(),
                "Text message about success authorization is wrong");
        softAssert.assertTrue(login.sectionAccountForLoggedUserIsDisplayed(),
                "Section Account is not displayed");
        softAssert.assertAll();
    }

}
