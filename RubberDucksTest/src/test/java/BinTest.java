import io.qameta.allure.Description;
import mavenizer.TestBase;
import mavenizer.helpers.LocatorHelper;
import mavenizer.helpers.StringHelper;
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
    @Description("Method adds categories(3) of ducks(3) to bin. Then removes it step by step " +
            "by clicking shortcut button. Checks " +
            "amounts in bin and order table for every step.")
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
     * Test case #1
     */
    @Description("Method adds ducks to bin. Then increase it by " +
            "clicking up arrow some times(tunable). Checks " +
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
        CataloguePage.addDucksToCart(driver, RED, String.valueOf(initialAmountOfDucks));
        unitPriceFromCatalogue = CataloguePage.getUnitPrice(driver);
        CataloguePage.goToCartPage(driver);
        CartPage.clickIncreaseArrowGivenTimes(driver, ducksToAdd);
        CartPage.updateButtonClick(driver);
        Assert.assertEquals(driver.findElement(LocatorHelper.getLocator("CartPage.inputField")).getAttribute("value"),
                String.valueOf(initialAmountOfDucks + ducksToAdd));
        Assert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().get(0).getQuantity(),
                String.valueOf(initialAmountOfDucks + ducksToAdd));
        Assert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().get(0).getUnitCost(),
                unitPriceFromCatalogue);
        Assert.assertEquals(CartPage.getOrderSummaryTable(driver).getOrderSummaryRecords().get(0).getTotal().
                        replace("$", "").replace("€", "")
                        .replace("?", "").trim(),
                StringHelper.calculateTotals(unitPriceFromCatalogue, initialAmountOfDucks + ducksToAdd));
    }

    /**
     * Test case #13
     */
    @Description("Method adds 1 duck to bin. Then decrease it by " +
            "clicking up arrow 5 times. Checks " +
            "validation works well, input field stays '0' and ducks removed after update clicked")
    @Test
    public void decreaseElementsLessThanZeroByClickingArrowsTest() {
        CataloguePage.addDucksToCart(driver, GREEN, "1");
        CataloguePage.goToCartPage(driver);
        Assert.assertEquals(driver.findElement(LocatorHelper.getLocator("CartPage.inputField")).getAttribute("value"),"1");
        CartPage.clickDecreaseArrowGivenTimes(driver, 5);
        Assert.assertEquals(driver.findElement(LocatorHelper.getLocator("CartPage.inputField")).getAttribute("value"),"0");
        CartPage.updateButtonClick(driver);
        Assert.assertEquals(CartPage.getEmptyBinMessage(driver), "There are no items in your cart.");
    }
}
