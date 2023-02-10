package mavenizer.objectPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class LogInForm {

    public final String URL_MAIN_PAGE = "https://litecart.stqa.ru/en/";
    private By logotypeImg = By.cssSelector("#logotype-wrapper img");
    private By loginForm = By.cssSelector("#box-account-login");
    private By inputEmail = By.cssSelector("#box-account-login input[name='email']");
    private By inputPassword = By.cssSelector("#box-account-login input[name='password']");
    private By buttonLogIn = By.cssSelector("#box-account-login button[name='login']");
    private By sectionAccountForLoggedUser = By.cssSelector("#box-account");
    private By resultMessageSuccess = By.cssSelector("div.notice.success");
    private By rememberMeButton = By.cssSelector("#box-account-login input[type=checkbox]");
    private String expectedResultMessageSuccessText = "You are now logged in as";

    private final WebDriver driver;

    public LogInForm(WebDriver driver) {
        this.driver = driver;
    }

    
    public void attemptToLogIn (String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword)).sendKeys(password);
        driver.findElement(buttonLogIn).click();
    }

    public void waitForPageLoadMethod() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(logotypeImg)).isDisplayed());
    }

    public boolean successAuthorizationMessageAfterLoggedInIsDisplayed() {
        if (driver.findElement(resultMessageSuccess).getText().contains(expectedResultMessageSuccessText) == true) {
            return true;
        } else
            return false;
    }

    public boolean sectionAccountForLoggedUserIsDisplayed() {
        if (driver.findElement(sectionAccountForLoggedUser).isDisplayed() == true) {
            return true;
        } else
            return false;
    }

    public boolean loginFormIsDisplayed() {
        if (driver.findElement(loginForm).isDisplayed() == true) {
            return true;
        } else
            return false;
    }

    public boolean inputEmailFieldIsDisplayed() {
        if (driver.findElement(inputEmail).isDisplayed() == true) {
            return true;
        } else
            return false;
    }

    public boolean inputPasswordFieldIsDisplayed() {
        if (driver.findElement(inputPassword).isDisplayed() == true) {
            return true;
        } else
            return false;
    }

    public void rememberMeButtonClick() {
        driver.findElement(rememberMeButton).click();
    }

}
