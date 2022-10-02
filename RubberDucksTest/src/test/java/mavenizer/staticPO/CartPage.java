package mavenizer.staticPO;

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


}
