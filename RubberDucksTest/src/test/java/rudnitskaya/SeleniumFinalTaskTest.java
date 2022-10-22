package rudnitskaya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumFinalTaskTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void openRubberDucksPageFromHeaderMenuTest(){
        driver.get("https://litecart.stqa.ru/en/");
        WebElement headerMenu = driver.findElement(By.id("site-menu"));
        WebElement rubberDucksLink = headerMenu.findElement(By.linkText("Rubber Ducks"));
        rubberDucksLink.click();
        driver.getTitle();

        Assert.assertEquals("Rubber Ducks | My Store1", driver.getTitle());
    }

    @Test
    public void openSubcategoryPageFromRubberDucksPageTest() {
        driver.get("https://litecart.stqa.ru/en/rubber-ducks-c-1/");
        WebElement subCategoryLink = driver.findElement(By.xpath("//div[@class='title']/..//div[text()='Subcategory']"));
        subCategoryLink.click();
        driver.getTitle();

        Assert.assertEquals("Subcategory | My Store1", driver.getTitle());
    }
}
