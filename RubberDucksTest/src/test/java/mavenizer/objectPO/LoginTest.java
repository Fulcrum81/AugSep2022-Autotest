package mavenizer.objectPO;


import org.testng.annotations.Test;

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
