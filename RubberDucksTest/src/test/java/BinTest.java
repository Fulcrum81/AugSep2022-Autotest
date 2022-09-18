import mavenizer.TestBase;
import mavenizer.staticPO.CartPage;
import mavenizer.staticPO.CataloguePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static mavenizer.helpers.Ducks.*;

public class BinTest extends TestBase {
    /**
     * Test case #4
     */
    @Test
    public void serialRemoveAllCategoriesTest() {
        int categoriesInBinCount = 0;

        CataloguePage.addDucksToCart(driver, PURPLE, "3");
        categoriesInBinCount++;
        CataloguePage.addDucksToCart(driver, YELLOW, "3");
        categoriesInBinCount++;
        CataloguePage.addDucksToCart(driver, GREEN, "3");
        categoriesInBinCount++;

        CataloguePage.goToCartPage(driver);

        List<WebElement> shortCutsLinksList = CartPage.getCategoriesLiList(driver);
        Assert.assertNotNull(shortCutsLinksList, "Shortcuts is empty");
        Assert.assertEquals(shortCutsLinksList.size(), categoriesInBinCount);

        for (int i = categoriesInBinCount; i > 0; i--) {
            if (shortCutsLinksList.size() > 1) { //Click all shortcuts except last one cause shortcut link absence
                shortCutsLinksList.get(i - 1).click();
                Assert.assertEquals(shortCutsLinksList.size(), i);
                Assert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().size(), i);
            }

            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.jsReturnsValue("return jQuery.active == 0"));

            CartPage.getRemoveButtonByIndex(driver, i).click();

            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.jsReturnsValue("return jQuery.active == 0"));

            shortCutsLinksList = CartPage.getCategoriesLiList(driver); //Reload links after remove
        }
        Assert.assertEquals(shortCutsLinksList.size(), 0);
        Assert.assertEquals(CartPage.getEmptyBinMessage(driver), "There are no items in your cart.");

        CartPage.goToMainPage(driver);
        Assert.assertEquals(CataloguePage.getCartAmountOnRightTopCorner(driver), "0");
    }

    /**
     * Just a showcase to table mapper. No asserts yet
     *
     *
     */
    @Test
    public void tableTest() {
        CataloguePage.addDucksToCart(driver, YELLOW, "8");
        CataloguePage.goToCartPage(driver);
        CataloguePage.addDucksToCart(driver, RED, "12");
        CataloguePage.goToCartPage(driver);
        System.out.println(CartPage.getOrderSummaryTable(driver));
    }
}
