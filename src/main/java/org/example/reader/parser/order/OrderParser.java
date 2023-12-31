package org.example.reader.parser.order;

import org.example.OrderItem;
import org.example.Order;
import org.example.reader.parser.ColumnTextParser;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class OrderParser extends ColumnTextParser {
    private final String[] fieldNames = {"dishName", "price"};

    public OrderParser(String separator) {
        super(separator);
    }

    public Order parse(Scanner scanner) {
        final int numDishes  = Integer.parseInt(scanner.nextLine());
        final Integer numPeople = Integer.parseInt(scanner.nextLine());

        final Order order = new Order(numPeople);

        readOrderItemLineByLine(numDishes, scanner).forEach(order::addNewOrderItem);

        return order;
    }

    private Collection<OrderItem> readOrderItemLineByLine(final int numberOfDishes, final Scanner scanner) {
        return IntStream.range(0, numberOfDishes).boxed().map(dish -> {
            Map<String, String> fieldValueMap = collectOne(scanner.nextLine());
            return readOrderItem(fieldValueMap);
        }).toList();
    }

    private OrderItem readOrderItem(Map<String, String> fieldValueMap) {
        final String dishName = getDishName(fieldValueMap);
        final Double price = getDishPrice(fieldValueMap);

        return new OrderItem(dishName, price);
    }

    protected String getDishName(Map<String, String> fieldValueMap) {
        return fieldValueMap.get(fields()[0]);
    }

    abstract protected Double getDishPrice(Map<String, String> fieldValueMap);

    @Override
    protected String[] fields() {
        return fieldNames;
    }
}
