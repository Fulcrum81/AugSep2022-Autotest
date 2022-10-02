package mavenizer.staticPO;

import mavenizer.helpers.LocatorHelper;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListOfElements {

    public WebElement listOfElementsInBin;

    public ListOfElements(WebElement listOfElementsInBin) {
        this.listOfElementsInBin = listOfElementsInBin;
    }

    public void selectElementFromListOfElementsInBin(int number) {
        List<WebElement> list = listOfElementsInBin.findElements(LocatorHelper.getLocator("ElementFromListOfElementsInBin"));
        list.get(number).click();
    }

    public int getListOfElementsInBin() {
        List<WebElement> list = listOfElementsInBin.findElements(LocatorHelper.getLocator("ElementFromListOfElementsInBin"));
        return list.size();
    };


}
