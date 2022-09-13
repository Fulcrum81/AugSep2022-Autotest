import mavenizer.TestBase;
import mavenizer.staticPO.CataloguePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static mavenizer.helpers.Ducks.*;

public class BinTest extends TestBase {
    @Test
    public void testTenDucksAddExample() throws InterruptedException {
        CataloguePage.addDucksToCart(driver, BLUE, "10");
        Assert.assertEquals(CataloguePage.getCartQuantityOnRightTopCorner(driver).strip(), "10");
    }

}
