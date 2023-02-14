package mavenizer.dto;

import mavenizer.dto.OrderSummaryRecord;

import java.util.ArrayList;
import java.util.List;

public class OrderSummaryTableMapper {
    private List<OrderSummaryRecord> orderSummaryRecords = new ArrayList<>();
    private String subtotal;
    private String paymentDue;

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(String paymentDue) {
        this.paymentDue = paymentDue;
    }

    public List<OrderSummaryRecord> getOrderSummaryRecords() {
        return orderSummaryRecords;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (OrderSummaryRecord record : orderSummaryRecords) {
            builder.append(record.getQuantity()).append(" : ").append(record.getProduct()).append(" : ").append(record.getSku())
                    .append(" : ").append(record.getUnitCost()).append(" : ").append(record.getInclTax()).append(" : ")
                    .append(record.getTotal()).append("\n");
        }
        builder.append("Subtotal : ").append(subtotal).append("\n").append("Payment Due : ").append(paymentDue);
        return builder.toString();
    }
}
