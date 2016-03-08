import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReceiptParser {

    private static Map <String, Integer> taxableMap = new HashMap<String, Integer>();

    public static void setUpTaxableMap() {
        taxableMap.put("music CD", 1);
        taxableMap.put("perfume", 1);
    }

    public static ReceiptCalculator parseReceipt(String filePath) {

        BufferedReader reader;
        ReceiptCalculator calculator = new ReceiptCalculator();
        List<ReceiptItem> items = new ArrayList<ReceiptItem>();

        try {
            String line;
            reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                items.add(parseReceiptLine(line));
            }

            if (reader != null) {
                reader.close();
            }

            calculator.setReceiptItems(items);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return calculator;
    }

    public static ReceiptItem parseReceiptLine(String line) {
        boolean isImport = false;
        boolean isTaxExempt = true;
        String unit = null;
        String name = null;

        String[] tokens = line.split(" ");
        Stack<String> stack = new Stack<String>();
        int quantity = Integer.valueOf(tokens[0]);
        double cost = Double.valueOf(tokens[tokens.length - 1]);

        for (String token : tokens) {
            if (token.matches(".*\\d+.*")) {
                continue;
            } else if (token.equals("imported")) {
                isImport = true;
                continue;
            } else if (token.equals("at")) {
                name = prettyPrintStack(stack);
                stack.removeAllElements();
                continue;
            } else if (token.equals("of")) {
                unit = prettyPrintStack(stack);
                stack.removeAllElements();
                continue;
            }
            stack.push(token);
        }

        if (taxableMap.containsKey(name) && taxableMap.get(name) == 1) {
            isTaxExempt = false;
        }

        return new ReceiptItem(name, cost, isImport, isTaxExempt, quantity, unit);
    }

    public static String prettyPrintStack(Stack<String> stack) {
        StringJoiner joiner = new StringJoiner(" ");
        for (String s : stack ) {
            joiner.add(s);
        }
        return joiner.toString();
    }

    public static void prettyPrintReceipt(List<ReceiptItem> items, String taxes, String total) {
        for (ReceiptItem item : items) {
            System.out.println(item.prettyPrintItem());
        }
        System.out.println(String.format("Sales Taxes: %s", taxes));
        System.out.println(String.format("Total: %s", total));
    }

    public static void main(String[] args) {
        setUpTaxableMap();
        ReceiptCalculator calculator = parseReceipt(args[0]);
        prettyPrintReceipt(calculator.getReceiptItems(), calculator.calculateTotalSalesTax(), calculator.calculateTotalCost());
    }
}
