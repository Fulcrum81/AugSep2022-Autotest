package mavenizer.staticPO;

import io.qameta.allure.Step;
import mavenizer.helpers.LocatorHelper;
import mavenizer.dto.OrderSummaryRecord;
import mavenizer.dto.OrderSummaryTableMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import mavenizer.helpers.Zarytski.Actions;
import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CartPage {
    public static void reduceTheAmountToOne(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).click();
        Actions.selectAll(driver,"CartPage.quantityOfElements");
        Actions.deleteValue(driver,"CartPage.quantityOfElements");
        int myValue = 1;
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).sendKeys(String.valueOf(myValue));
        driver.findElement(LocatorHelper.getLocator("CartPage.buttonUpdate")).click();

    }

    public static void increaseItemsInTheCartByFive(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).click();
        Actions.selectAll(driver,"CartPage.quantityOfElements");
        Actions.deleteValue(driver,"CartPage.quantityOfElements");
        int myValue = 5;
        driver.findElement(LocatorHelper.getLocator("CartPage.quantityOfElements")).sendKeys(String.valueOf(myValue));
        driver.findElement(LocatorHelper.getLocator("CartPage.buttonUpdate")).click();
    }

   public static void removeElement(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CartPage.buttonRemove")).click();
   }

   public static void enterValidValueInCustomerDetails(WebDriver driver) {
        int myValue = 777;
        driver.findElement(LocatorHelper.getLocator("CartPage.taxID")).sendKeys(String.valueOf(myValue));
        driver.findElement(LocatorHelper.getLocator("CartPage.company")).sendKeys("Belhard");
        driver.findElement(LocatorHelper.getLocator("CartPage.firstName")).sendKeys("Nik");
        driver.findElement(LocatorHelper.getLocator("CartPage.lastName")).sendKeys("Nikson");
        driver.findElement(LocatorHelper.getLocator("CartPage.address1")).sendKeys("st. Lesnaya 22");
        driver.findElement(LocatorHelper.getLocator("CartPage.address2")).sendKeys("st. Sokolniki 33");
        driver.findElement(LocatorHelper.getLocator("CartPage.postcode")).sendKeys("456753");
        driver.findElement(LocatorHelper.getLocator("CartPage.city")).sendKeys("Minsk");
        //driver.findElement(LocatorHelper.getLocator("CartPage.countryDropdown")).click();
        Select dropdown = new Select(driver.findElement(LocatorHelper.getLocator("CartPage.dropdown")));
        dropdown.selectByVisibleText("Belarus");
        driver.findElement(LocatorHelper.getLocator("CartPage.email")).sendKeys("test@test.com");
        driver.findElement(LocatorHelper.getLocator("CartPage.phone")).sendKeys("+375448765432");
   }

   public static void clickBtnSaveChanges(WebDriver driver) {
       driver.findElement(LocatorHelper.getLocator("CartPage.btnSaveChanges")).click();
   }
   public static void clickBtnConfirmOrder(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CartPage.btnConfirmOrder")).click();
   }


    @Step("Return links to all categories in the bin")
    public static List<WebElement> getCategoriesLinksList(WebDriver driver) {
        try {
            return driver.findElements(LocatorHelper.getLocator("CartPage.categoriesLinksList"));
        } catch (NoSuchElementException ex) {
            System.out.println("Element not found on page");
        }
        return null;
    }

    @Step("Return 'Remove button' by index in the bin")
    public static WebElement getRemoveButtonByIndex(WebDriver driver, int shortCutIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("ul>li:nth-child(");
        sb.append(shortCutIndex);
        sb.append(") button[name='remove_cart_item']");
        return driver.findElement(By.cssSelector(sb.toString()));
    }

    @Step("Return wrapper object for order summary table")
    public static OrderSummaryTableMapper getOrderSummaryTable(WebDriver driver) {
        List<WebElement> rows = driver.findElements(LocatorHelper.getLocator("CartPage.dataTableRowsWithoutHeader"));
        OrderSummaryTableMapper table = new OrderSummaryTableMapper();
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(LocatorHelper.getLocator("CartPage.dataTableColumns"));
            if (columns.size() != 1) {//No spacer
                String firstColumn = columns.get(0).getText();
                if (firstColumn.equals("Subtotal:")) {
                    table.setSubtotal(columns.get(1).getText());
                }
                if (firstColumn.equals("Payment Due:")) {
                    table.setPaymentDue(columns.get(1).getText());
                }
                if (columns.size() == 6) {
                    table.getOrderSummaryRecords().add(new OrderSummaryRecord(columns.get(0).getText(),
                            columns.get(1).getText(), columns.get(2).getText(), columns.get(3).getText(),
                            columns.get(4).getText(), columns.get(5).getText()));
                }
            }
        }
        return table;
    }

    @Step("Click increase arrow N times")
    public static void clickIncreaseArrowGivenTimes(WebDriver driver, int timesClick) {
        WebElement inputField = driver.findElement(LocatorHelper.getLocator("CartPage.inputField"));
        inputField.click(); //get focus
        for (int i = 0; i < timesClick; i++) {
            inputField.sendKeys(Keys.ARROW_UP);
        }
    }

    @Step("Click decrease arrow N times")
    public static void clickDecreaseArrowGivenTimes(WebDriver driver, int timesClick) {
        WebElement inputField = driver.findElement(LocatorHelper.getLocator("CartPage.inputField"));
        inputField.click(); //get focus
        for (int i = 0; i < timesClick; i++) {
            inputField.sendKeys(Keys.ARROW_DOWN);
        }
    }

    @Step("Update button click")
    public static void updateButtonClick(WebDriver driver) {
        driver.findElement(LocatorHelper.getLocator("CartPage.updateCartButton")).click(); //Update
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.jsReturnsValue("return jQuery.active == 0"));
    }

    @Step("Return empty bin message")
    public static String getEmptyBinMessage(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CartPage.emptyBinMessage")).getText();
    }

    @Step("Return input field value")
    public static String getInputFieldValue(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CartPage.inputField")).getAttribute("value");
    }


    public static void goToMainPage(WebDriver driver) {
        driver.get("https://litecart.stqa.ru/en/");
    }
}
