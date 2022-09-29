import mavenizer.staticPO.CartPage;
import mavenizer.staticPO.CataloguePage;
import mavenizer.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BinTest extends TestBase {
    @Test
    public void checkForBinlsEmptyOnStartupTest() {
        Assert.assertEquals(CataloguePage.getCartQuantityOnRightTopCorner(driver), "0");
        CataloguePage.goToCartPage(driver);
        Assert.assertEquals(CartPage.getEmptyBinMessage(driver), "There are no items in your cart.");
    }
}


            


