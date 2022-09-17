import mavenizer.TestBase;
import mavenizer.staticPO.CartPage;
import mavenizer.staticPO.CataloguePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static mavenizer.helpers.Ducks.*;

public class BinTest extends TestBase {
    /**
     * Test case #4
     */
    @Test
    public void serialRemoveAllCategoriesTest() throws InterruptedException {
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
            Thread.sleep(800);

            CartPage.getRemoveButtonByIndex(driver, i).click();
            Thread.sleep(800);
            shortCutsLinksList = CartPage.getCategoriesLiList(driver); //Reload links after remove
        }
        Assert.assertEquals(shortCutsLinksList.size(), 0);
        Assert.assertEquals(CartPage.getEmptyBinMessage(driver), "There are no items in your cart.");

        CartPage.goToMainPage(driver);
        Assert.assertEquals(CataloguePage.getCartAmountOnRightTopCorner(driver), "0");
    }

    /**
     * Just a showcase to table mapper to reuse at testcases. No asserts
     *
     * @throws InterruptedException
     */
    @Test
    public void tableTest() throws InterruptedException {
        CataloguePage.addDucksToCart(driver, YELLOW, "8");
        CataloguePage.goToCartPage(driver);
        CataloguePage.addDucksToCart(driver, RED, "12");
        CataloguePage.goToCartPage(driver);
        System.out.println(CartPage.getOrderSummaryTable(driver));
    }
}
