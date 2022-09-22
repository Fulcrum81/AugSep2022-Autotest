package mavenizer.staticPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListOfElements {

    public WebElement listOfElementsInBin;

    public ListOfElements(WebElement listOfElementsInBin) {
        this.listOfElementsInBin = listOfElementsInBin;
    }

    public void selectElementFromListOfElementsInBin(int number) {
        List<WebElement> list = listOfElementsInBin.findElements(By.xpath("//*[@class='shortcuts']//*[@class='shortcut']"));
        list.get(number).click();
    }

    public int getListOfElementsInBin() {
        List<WebElement> list = listOfElementsInBin.findElements(By.xpath("//*[@class='shortcuts']//*[@class='shortcut']"));
        return list.size();
    };


}
