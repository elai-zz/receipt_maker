import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReceiptItemTest {

    @Test
    public void testPrettyPrintNoUnit() {
        ReceiptItem receiptItem = new ReceiptItem("book", 12.49, false, false, 1, null);
        String prettyPrint = receiptItem.prettyPrintItem();
        assertEquals("1 book : 12.49", prettyPrint);
    }

    @Test
    public void testPrettyPrintNoUnit2() {
        ReceiptItem receiptItem = new ReceiptItem("music CD", 14.99, false, false, 1, null);
        String prettyPrint = receiptItem.prettyPrintItem();
        assertEquals("1 music CD : 16.49", prettyPrint);
    }


    @Test
    public void testPrettyPrintHasUnit() {
        ReceiptItem receiptItem = new ReceiptItem("perfume", 27.99, true, false, 1, "bottle");
        String prettyPrint = receiptItem.prettyPrintItem();
        assertEquals("1 imported bottle of perfume : 27.99", prettyPrint);
    }

    @Test
    public void testTaxableNonImport() {
        double itemCost = 12.49;
        ReceiptItem receiptItem = new ReceiptItem("book", itemCost, false, false, 1, null);
        double totalCostWithTax = receiptItem.getTotalTax();
        assertEquals(1.25d, totalCostWithTax, 0);
    }

}