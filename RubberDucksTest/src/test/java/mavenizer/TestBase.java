package mavenizer;

import mavenizer.helpers.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class TestBase {

    protected static Logger LOG = Logger.getLogger(String.valueOf(TestBase.class));

    protected WebDriver driver;

    private final String baseURL = "https://litecart.stqa.ru/en/rubber-ducks-c-1/";

    @BeforeMethod
    public void setup() {
        Browser browser = Browser.getEnumByLabel(System.getProperty("browser", Browser.CHROME.getBrowserName()));
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
