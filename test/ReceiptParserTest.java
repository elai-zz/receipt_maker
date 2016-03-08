import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by estella on 3/3/16.
 */
public class ReceiptParserTest {

    ReceiptParser parser = new ReceiptParser();

    @Test
    public void testParser() {
        ReceiptItem item = parser.parseReceiptLine("1 book at 12.49");
        assertEquals(1, item.getQuantity());
        assertEquals(12.49, item.getCost(), 0);
    }

    @Test
    public void testParser1() {
        ReceiptItem item = parser.parseReceiptLine("1 music CD at 14.99");
        assertEquals(1, item.getQuantity());
        assertEquals(14.99, item.getCost(), 0);
        assertEquals("music CD",item.getName());
        assertEquals(false, item.isTaxExempt());
    }

    @Test
    public void testParser2() {
        ReceiptItem item = parser.parseReceiptLine("1 imported box of chocolates at 10.00");
        assertEquals(1, item.getQuantity());
        assertEquals(10.00, item.getCost(), 0);
        assertEquals("chocolates", item.getName());
        assertEquals(true, item.isImport());
        assertEquals(true, item.isTaxExempt());
    }

    @Test
    public void testParser3() {
        ReceiptItem item = parser.parseReceiptLine("1 music CD at 14.99");
        assertEquals(1, item.getQuantity());
        assertEquals("music CD", item.getName());
        assertEquals(14.99, item.getCost(), 0);
        assertEquals(16.49, item.getCostWithTax(), 0);
    }
}
