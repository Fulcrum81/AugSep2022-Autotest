package mavenizer.staticPO;

import mavenizer.helpers.LocatorHelper;
import mavenizer.helpers.OrderSummaryRecord;
import mavenizer.helpers.OrderSummaryTableMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    public static List<WebElement> getCategoriesLiList(WebDriver driver) {
        try {
            return driver.findElements(LocatorHelper.getLocator("CartPage.categoriesLinksList"));
        } catch (NoSuchElementException ex) {
            System.out.println("Element not found on page");
        }
        return null;
    }

    public static WebElement getRemoveButtonByIndex(WebDriver driver, int shortCutIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("ul>li:nth-child(");
        sb.append(shortCutIndex);
        sb.append(") button[name='remove_cart_item']");
        return driver.findElement(By.cssSelector(sb.toString()));
    }

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

    public static String getEmptyBinMessage(WebDriver driver) {
        return driver.findElement(LocatorHelper.getLocator("CartPage.emptyBinMessage")).getText();
    }

    public static void goToMainPage(WebDriver driver) {
        driver.get("https://litecart.stqa.ru/en/");
    }
}
