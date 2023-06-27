package org.example.parser.costs;

import org.example.order.Dish;
import org.example.parser.ColumnTextParser;
import java.util.Map;
import java.util.Scanner;

public final class CostsAndPricesParser extends ColumnTextParser {

    private static final String[] fieldNames = {"dishName", "cost", "price"};

    public CostsAndPricesParser(String separator) {
        super(separator);
    }

    public Dish parse(Scanner scanner) {
        Map<String, String> dishLine = collectOne(scanner.nextLine());

        final String name = getFieldValue(fields()[0], dishLine);
        final Integer cost = Integer.parseInt(getFieldValue(fields()[1], dishLine));
        final Double price = Double.parseDouble(getFieldValue(fields()[2], dishLine));

        return new Dish(name, cost, price);
    }

    private String getFieldValue(String field, Map<String, String> dishLine) {
        return dishLine.get(field);
    }

    @Override
    protected String[] fields() {
        return fieldNames;
    }
}
