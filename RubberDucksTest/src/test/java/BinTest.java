import mavenizer.staticPO.CataloguePage;
import mavenizer.staticPO.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BinTest extends TestBase {
    @Test
    public void cartAmountTestNoMatterOfCurrency() {
        Assert.assertEquals(CataloguePage.getCartAmountOnRightTopCorner(driver).replace("$", "").
                replace("€", "").trim(), "0");
    }

}
