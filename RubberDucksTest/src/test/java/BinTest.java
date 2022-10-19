import io.qameta.allure.Description;
import mavenizer.TestBase;
import mavenizer.helpers.LocatorHelper;
import mavenizer.helpers.StringHelper;
import mavenizer.helpers.Waits;
import mavenizer.helpers.Zarytski.WaitsZarytski;
import mavenizer.staticPO.CartPage;
import mavenizer.staticPO.CataloguePage;
import mavenizer.staticPO.ElementsPage;
import mavenizer.staticPO.TablesPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static mavenizer.helpers.Ducks.*;

//@Listeners (ScreenshotListener.class)

public class BinTest extends TestBase {
    final static Logger logger = Logger.getLogger(String.valueOf(BinTest.class));

    //File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
   //File folderForFiles = new File("C:\\Users\\user\\Desktop\\ScreenShot");
   // Allure.addAttachment("screenshot_name", new FileInputStream(screenshot));



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
    public void checkAddOneElementToBin() throws Exception {

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        String before = CataloguePage.getCartQuantityOnRightTopCorner(driver);

        LOG.info("Add one element to bin");
        CataloguePage.addOneElementToBin(driver);
        LOG.info("Waiting add one element to bin");

        String expectedAmount = "1";
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", expectedAmount);

        String after = CataloguePage.getCartQuantityOnRightTopCorner(driver);

        String expectedResult = "1"; //1_NEW_NEW_NEW
        Assert.assertEquals(after, expectedResult);
    }


    @ Description (" Add tree elements to bin")
    @Test
    public void checkAddTreeElementToBin() {
        logger.info("Step_1");
        String before = CataloguePage.getCartQuantityOnRightTopCorner(driver);
        logger.info("Step_2");
        CataloguePage.addTreeElementsToBin(driver);
        logger.info("Step_3");
        String expectedAmount = "3";
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", expectedAmount);
        logger.info("Step_4");
        String after = CataloguePage.getCartQuantityOnRightTopCorner(driver);
        logger.info("Step_5");
        String expectedResult = "3";  //NEW_NEW_NEW 2
        Assert.assertEquals(after, expectedResult);
    }

    @ Description ("Reduce items in the cart")

    @Test
    public void reduceItemsInTheCart() {
        CataloguePage.addFiveSameElements(driver);

        TablesPage tablesPage1 = new TablesPage(driver);
        String getCellBefore = tablesPage1.example1.getCell(1,0).getText();
        System.out.println(getCellBefore);

        CartPage.reduceTheAmountToOne(driver);

        String expectedSum = "14.80 €";
        WaitsZarytski.explicitWaitTextToBe(driver, "CartPage.paymentDue",expectedSum);

        TablesPage tablesPage2 = new TablesPage(driver);
        String getCellAfter = tablesPage2.example1.getCell(1,0).getText();
        System.out.println(getCellAfter);

        String expectedResult = "1";
        Assert.assertEquals(getCellAfter, expectedResult);

    }

    @Test
    public void increaseItemsInTheCart() {
        CataloguePage.addOneElementToBin(driver);
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity","1");
        CataloguePage.goToCartPage(driver);

        TablesPage tablesPage1 = new TablesPage(driver);
        String getCellBefore = tablesPage1.example1.getCell(1,0).getText();
        System.out.println(getCellBefore);

        CartPage.increaseItemsInTheCartByFive(driver);

        String expectedSum = "74.00 €";
        WaitsZarytski.explicitWaitTextToBe(driver, "CartPage.paymentDue",expectedSum);

        TablesPage tablesPage4 = new TablesPage(driver);
        String getCellAfter = tablesPage4.example1.getCell(1,0).getText();
        System.out.println(getCellAfter);

        String expectedResult = "5"; //NEW 5
        Assert.assertEquals(getCellAfter, expectedResult);

    }


    @Test
    public void removingOneElementFromTheCart() {
        CataloguePage.addTreeElementsToBin(driver);

        String expectedAmount = "3";
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity",expectedAmount);
        CataloguePage.goToCartPage(driver);

        ElementsPage beforeRemove = new ElementsPage(driver);
        int allSumBefore = beforeRemove.check1.getListOfElementsInBin();
        System.out.println(allSumBefore);

        int expectedSum = 3;
        WaitsZarytski.explicitWaitNumberOfElementsToBe(driver, "CartPage.listOfElements", expectedSum);

        ElementsPage myListOfElements = new ElementsPage(driver);
        myListOfElements.check1.selectElementFromListOfElementsInBin(0);

        CartPage.removeElement(driver);

        int sumListOfElements = 2;
        WaitsZarytski.explicitWaitNumberOfElementsToBe(driver, "CartPage.listOfElements", sumListOfElements);

        ElementsPage afterRemove = new ElementsPage(driver);
        int allSumAfter = afterRemove.check1.getListOfElementsInBin();
        System.out.println(allSumAfter);

        int expectedResult = 2;
        Assert.assertEquals(allSumAfter, expectedResult);

    }

    @Test
    public void ordering() {
        //I decided add a new test
        CataloguePage.addTreeElementsToBin(driver);
        WaitsZarytski.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", "3");
        CataloguePage.goToCartPage(driver);

        CartPage.enterValidValueInCustomerDetails(driver);

        WaitsZarytski.elementToBeClickable(driver, "CartPage.btnSaveChanges");
        CartPage.clickBtnSaveChanges(driver);

        WaitsZarytski.element(driver);

        CartPage.clickBtnConfirmOrder(driver);

        WaitsZarytski.explicitWaitTextToBe(driver, "CartPage.successful", "Your order is successfully completed!");

        String textSuccessful = driver.findElement(LocatorHelper.getLocator("CartPage.successful")).getText();
        String expectedText = "Your order is successfully completed!";

        Assert.assertEquals(textSuccessful, expectedText);
    }

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

//    public File getScreenshot() {
//        return screenshot;
//    }
}
