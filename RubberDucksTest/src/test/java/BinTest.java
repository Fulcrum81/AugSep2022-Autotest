import mavenizer.helpers.LocatorHelper;
import mavenizer.helpers.Zarytski.Waits;
import mavenizer.staticPO.CartPage;
import mavenizer.staticPO.CataloguePage;
import mavenizer.TestBase;
import mavenizer.staticPO.ElementsPage;
import mavenizer.staticPO.TablesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class BinTest extends TestBase {


    @Test
    public void checkAddOneElementToBin() throws Exception {

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        String before = CataloguePage.getCartQuantityOnRightTopCorner(driver);

        LOG.info("Add one element to bin");
        CataloguePage.addOneElementToBin(driver);
        LOG.info("Waiting add one element to bin");

        String expectedAmount = "1";
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", expectedAmount);

        String after = CataloguePage.getCartQuantityOnRightTopCorner(driver);

        String expectedResult = "1";
        Assert.assertEquals(after, expectedResult);
    }

    @Test
    public void checkAddTreeElementToBin() {
        String before = CataloguePage.getCartQuantityOnRightTopCorner(driver);
        CataloguePage.addTreeElementsToBin(driver);

        String expectedAmount = "3";
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity", expectedAmount);

        String after = CataloguePage.getCartQuantityOnRightTopCorner(driver);

        String expectedResult = "3";
        Assert.assertEquals(after, expectedResult);
    }


    @Test
    public void reduceItemsInTheCart() {
        CataloguePage.addFiveSameElements(driver);

        TablesPage tablesPage1 = new TablesPage(driver);
        String getCellBefore = tablesPage1.example1.getCell(1,0).getText();
        System.out.println(getCellBefore);

        CartPage.reduceTheAmountToOne(driver);

        String expectedSum = "14.80 €";
        Waits.explicitWaitTextToBe(driver, "CartPage.paymentDue",expectedSum);

        TablesPage tablesPage2 = new TablesPage(driver);
        String getCellAfter = tablesPage2.example1.getCell(1,0).getText();
        System.out.println(getCellAfter);

        String expectedResult = "1";
        Assert.assertEquals(getCellAfter, expectedResult);

    }

    @Test
    public void increaseItemsInTheCart() {
        CataloguePage.addOneElementToBin(driver);
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity","1");
        CataloguePage.goToCartPage(driver);

        TablesPage tablesPage3 = new TablesPage(driver);
        String getCellBefore = tablesPage3.example1.getCell(1,0).getText();
        System.out.println(getCellBefore);

        CartPage.increaseItemsInTheCartByFive(driver);

        String expectedSum = "74.00 €";
        Waits.explicitWaitTextToBe(driver, "CartPage.paymentDue",expectedSum);

        TablesPage tablesPage4 = new TablesPage(driver);
        String getCellAfter = tablesPage4.example1.getCell(1,0).getText();
        System.out.println(getCellAfter);

        String expectedResult = "5";
        Assert.assertEquals(getCellAfter, expectedResult);

    }


    @Test
    public void removingOneElementFromTheCart() {
        CataloguePage.addTreeElementsToBin(driver);

        String expectedAmount = "3";
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity",expectedAmount);
        CataloguePage.goToCartPage(driver);

        ElementsPage beforeRemove = new ElementsPage(driver);
        int allSumBefore = beforeRemove.check1.getListOfElementsInBin();
        System.out.println(allSumBefore);

        int expectedSum = 3;
        Waits.explicitWaitNumberOfElementsToBe(driver, "CartPage.listOfElements", expectedSum);

        ElementsPage myListOfElements = new ElementsPage(driver);
        myListOfElements.check1.selectElementFromListOfElementsInBin(0);

        CartPage.removeElement(driver);

        int sumListOfElements = 2;
        Waits.explicitWaitNumberOfElementsToBe(driver, "CartPage.listOfElements", sumListOfElements);

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
        Waits.explicitWaitTextToBe(driver, "CataloguePage.cartQuantity","3");
        CataloguePage.goToCartPage(driver);

        CartPage.enterValidValueInCustomerDetails(driver);

        Waits.elementToBeClickable(driver, "CartPage.btnSaveChanges");
        CartPage.clickBtnSaveChanges(driver);

        Waits.element(driver);

        CartPage.clickBtnConfirmOrder(driver);

        Waits.explicitWaitTextToBe(driver,"CartPage.successful", "Your order is successfully completed!");

        String textSuccessful = driver.findElement(LocatorHelper.getLocator("CartPage.successful")).getText();
        String expectedText = "Your order is successfully completed!";

        Assert.assertEquals(textSuccessful, expectedText);

    }

}
