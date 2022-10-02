import io.qameta.allure.Description;
import mavenizer.TestBase;
import mavenizer.helpers.StringHelper;
import mavenizer.helpers.Waits;
import mavenizer.staticPO.CartPage;
import mavenizer.staticPO.CataloguePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static mavenizer.helpers.Ducks.*;

public class BinTest extends TestBase {
    /**
     * Test case #4
     */
    @Description("Add categories(3) of ducks(3) to bin. Then remove it step by step " +
            "by clicking shortcut button. Check " +
            "amounts in bin and order table for every step.")
    @Test
    public void serialRemoveAllCategoriesTest() {
        int ducksToAdd = 3;
        CataloguePage.addDucksToCart(driver, PURPLE, ducksToAdd);
        CataloguePage.addDucksToCart(driver, YELLOW, ducksToAdd);
        CataloguePage.addDucksToCart(driver, GREEN, ducksToAdd);

        CataloguePage.goToCartPage(driver);

        List<WebElement> shortCutsLinksList = CartPage.getCategoriesLinksList(driver);
        Assert.assertNotNull(shortCutsLinksList, "Shortcuts is empty");
        Assert.assertEquals(shortCutsLinksList.size(), ducksToAdd);

        for (int i = ducksToAdd; i > 0; i--) {
            if (shortCutsLinksList.size() > 1) { //Click all shortcuts except last one cause shortcut link absence
                shortCutsLinksList.get(i - 1).click();
                Assert.assertEquals(shortCutsLinksList.size(), i);
                Assert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().size(), i);
            }
            Waits.waitForJsEnds(driver, 2);
            CartPage.getRemoveButtonByIndex(driver, i).click();
            Waits.waitForJsEnds(driver, 2);
            shortCutsLinksList = CartPage.getCategoriesLinksList(driver); //Reload links after remove
        }
        Assert.assertEquals(shortCutsLinksList.size(), 0);
        Assert.assertEquals(CartPage.getEmptyBinMessage(driver), "There are no items in your cart.");
        CartPage.goToMainPage(driver);
        Assert.assertEquals(CataloguePage.getCartAmountOnRightTopCorner(driver), "0");
    }

    /**
     * Test case #1
     */
    @Description("Add ducks to bin. Then increase it by " +
            "clicking up arrow some times(tunable). Check " +
            "amounts in bin and totals in order table.")
    @Test
    public void increaseElementsByClickingArrowsTest() {
        /**
         * You can change initialAmountOfDucks
         * and ducksToAdd vars if you need
         */
        int initialAmountOfDucks = 1;
        int ducksToAdd = 5;

        String unitPriceFromCatalogue;
        CataloguePage.addDucksToCart(driver, RED, initialAmountOfDucks);
        unitPriceFromCatalogue = CataloguePage.getUnitPrice(driver);
        CataloguePage.goToCartPage(driver);
        CartPage.clickIncreaseArrowGivenTimes(driver, ducksToAdd);
        CartPage.updateButtonClick(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(CartPage.getInputFieldValue(driver),
                String.valueOf(initialAmountOfDucks + ducksToAdd));
        softAssert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().get(0).getQuantity(),
                String.valueOf(initialAmountOfDucks + ducksToAdd));
        softAssert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().get(0).getUnitCost(),
                unitPriceFromCatalogue);
        softAssert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().get(0).getTotal().
                        replace("$", "").replace("€", "")
                        .replace("?", "").trim(),
                StringHelper.calculateTotals(unitPriceFromCatalogue, initialAmountOfDucks + ducksToAdd));
        softAssert.assertAll();
    }

    /**
     * Test case #13
     */
    @Description("Adds 1 duck to bin. Then decrease it by " +
            "clicking up arrow 5 times. Check " +
            "validation works well, input field stays '0' and all ducks removed after update button clicked")
    @Test
    public void decreaseElementsLessThanZeroByClickingArrowsTest() {
        CataloguePage.addDucksToCart(driver, GREEN, 1);
        CataloguePage.goToCartPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(CartPage.getInputFieldValue(driver), "1");
        CartPage.clickDecreaseArrowGivenTimes(driver, 5);
        softAssert.assertEquals(CartPage.getInputFieldValue(driver), "0");
        CartPage.updateButtonClick(driver);
        softAssert.assertEquals(CartPage.getEmptyBinMessage(driver), "There are no items in your cart.");
        softAssert.assertAll();
    }
}
