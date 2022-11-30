package mavenizer.objectPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class LogInForm {

    public final String URL_MAIN_PAGE = "https://litecart.stqa.ru/en/";
    public By loginForm = By.cssSelector("#box-account-login");
    public By inputEmail = By.cssSelector("#box-account-login input[name='email']");
    public By inputPassword = By.cssSelector("#box-account-login input[name='password']");
    public By buttonLogIn = By.cssSelector("#box-account-login button[name='login']");
    public By sectionAccountForLoggedUser = By.cssSelector("#box-account");
    public By resultMessageSuccess = By.cssSelector("div.notice.success");
    public By rememberMeButton = By.cssSelector("#box-account-login input[type=checkbox]");


    public String expectedResultMessageSuccessText = "You are now logged in as";



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

}
