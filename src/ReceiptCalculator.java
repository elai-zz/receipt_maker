import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by estella on 2/29/16.
 */
public class ReceiptCalculator {
    private List<ReceiptItem> receiptItems;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private Double totalSalesTax;
    private Double totalCost;

    public void setReceiptItems(List<ReceiptItem> items) {
        this.receiptItems = items;
    }

    public String calculateTotalSalesTax() {
        Double totalSalesTax = 0d;
        for (ReceiptItem item : this.receiptItems) {
            totalSalesTax += item.getTotalTax();
        }
        this.totalSalesTax = totalSalesTax;
        return decimalFormat.format(totalSalesTax);
    }

    public String calculateTotalCost() {
        Double totalCost = 0d;
        for (ReceiptItem item : this.receiptItems) {
            totalCost += item.getQuantity() * item.getCost();
        }
        this.totalCost = totalCost;
        return decimalFormat.format(this.totalCost + this.totalSalesTax);
    }

    public List<ReceiptItem> getReceiptItems() {
        return this.receiptItems;
    }

}
