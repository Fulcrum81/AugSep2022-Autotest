package mavenizer.dto;

public class OrderSummaryRecord {
    private final String quantity;
    private final String product;
    private final String sku;
    private final String unitCost;
    private final String inclTax;
    private final String total;

    public OrderSummaryRecord(String quantity, String product, String sku, String unitCost, String inclTax, String total) {
        this.quantity = quantity;
        this.product = product;
        this.sku = sku;
        this.unitCost = unitCost;
        this.inclTax = inclTax;
        this.total = total;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public String getSku() {
        return sku;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public String getInclTax() {
        return inclTax;
    }

    public String getTotal() {
        return total;
    }
}
