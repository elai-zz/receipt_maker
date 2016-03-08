import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by estella on 2/29/16.
 */
public class ReceiptItem {

    public static final double BASIC_TAX_RATE = 0.1;
    public static final double IMPORT_TAX_RATE = 0.05;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");


    private String name;
    private double cost;
    private boolean isImport;
    private boolean isTaxExempt;
    private int quantity;
    private String unit;

    public ReceiptItem(String name, double cost, boolean isImport,
                       boolean isTaxExempt, int quantity, String unit) {
        this.name = name;
        this.cost = cost;
        this.isImport = isImport;
        this.isTaxExempt = isTaxExempt;
        this.quantity = quantity;
        this.unit = unit;
    }

    public double getTotalTax() {
        double salesTaxPercent = 1d;

        if (!this.isTaxExempt) {
            salesTaxPercent += BASIC_TAX_RATE;
        }

        if (this.isImport) {
            salesTaxPercent += IMPORT_TAX_RATE;
        }

        BigDecimal totalTaxBigDecimal = new BigDecimal(this.quantity * (this.cost * salesTaxPercent - this.cost));
        totalTaxBigDecimal = totalTaxBigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return totalTaxBigDecimal.doubleValue();
    }

    public double getCostWithTax() {
        BigDecimal totalCostBigDecimal = new BigDecimal(this.cost + getTotalTax());
        totalCostBigDecimal = totalCostBigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return totalCostBigDecimal.doubleValue();
    }

    public String prettyPrintItem() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.quantity);
        stringBuffer.append(" ");

        if (isImport){
            stringBuffer.append("imported ");
        }
        if (unit != null) {
            stringBuffer.append(this.unit);
            stringBuffer.append(" of ");
        }
        stringBuffer.append(this.name);
        stringBuffer.append(" : ");
        stringBuffer.append(decimalFormat.format(getCostWithTax()));

        return stringBuffer.toString();
    }

    public double getCost() {
        return this.cost;
    }

    public boolean isImport() {
        return this.isImport;
    }

    public boolean isTaxExempt() {
        return this.isTaxExempt;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }
}