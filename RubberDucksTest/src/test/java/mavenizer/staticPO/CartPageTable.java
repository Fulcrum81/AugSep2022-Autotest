package mavenizer.staticPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPageTable {
    public WebElement webTable;

    public CartPageTable(WebElement webTable) {
        this.webTable = webTable;
    }

    public int getRowCount() {
        List<WebElement> rows = webTable.findElements(By.tagName("tr"));
        System.out.println(rows.get(0));
        return rows.size();
    }


    public int getColumnCount() {
        List<WebElement> rows = webTable.findElements(By.tagName("tr"));
        WebElement headerRow = rows.get(1);
        List<WebElement> column = headerRow.findElements(By.tagName("td"));
        return column.size();
    }

    public WebElement getCell (int rowIndex, int columnIndex) {
        List<WebElement> rows = webTable.findElements(By.tagName("tr"));
        WebElement headerRow = rows.get(rowIndex);
        List<WebElement> columns = headerRow.findElements(By.tagName("td"));
        return columns.get(columnIndex);
    }

}
