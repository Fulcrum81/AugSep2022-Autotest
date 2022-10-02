package mavenizer.objectPO;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;


public class LoginTest extends TestBaseLogin {

    @Test()
    public void logInFormIsDisplayedTest () {
        assertTrue(login.wait.until(ExpectedConditions.visibilityOfElementLocated(login.loginForm)).isDisplayed(),
                "LogIn Form is not displayed");
    }


    @Test(dataProvider = "ValidCredentials", dataProviderClass = DataProviders.class)
    public void logInWithValidCredentialsTest (String email, String password) {
        login.attemptToLogIn(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(login.wait.until(ExpectedConditions.visibilityOfElementLocated(login.resultMessageSuccess))
                        .getText().contains(login.expectedResultMessageSuccessText),
                "Text message about success authorization is wrong");
        softAssert.assertTrue(login.wait.until(ExpectedConditions
                .visibilityOfElementLocated(login.sectionAccountForLoggedUser)).isDisplayed(),
                "Section Account is not displayed");
        softAssert.assertAll();
    }




}
