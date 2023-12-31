package org.example;

import org.example.reader.CostsAndPricesReader;
import org.example.reader.OrderReader;
import org.example.reader.parser.order.NoPriceOrderParser;
import org.example.reader.parser.order.OrderParser;
import org.example.reader.parser.order.PricedOrderParser;
import java.util.Collection;

class Main {
    public static void main(String[] args) {
        readOnly();
    }

    private static void readOnly() {
        readOneOrder();
        readAllOrders();
    }

    private static void readOneOrder() {
        final String ordersSourceDirectory = "inputs";
        final String orderName = "Order1";

        System.out.println("Reading order '" + orderName + "' from '" + ordersSourceDirectory + "' directory!");

        final OrderParser pricedParser = new PricedOrderParser(" ");

        final OrderReader reader = new OrderReader(ordersSourceDirectory, pricedParser);

        final Order pricedOrder = reader.readOne(orderName);

        pricedOrder.printOrder();
    }

    private static void readAllOrders() {
        final String ordersSourceDirectory = "orders";

        System.out.println("Reading all orders from '" + ordersSourceDirectory + "' directory!");

        final CostsAndPricesReader costsAndPricesReader = new CostsAndPricesReader("CostsAndPrices");
        final OrderParser pricelessParser = new NoPriceOrderParser(" ", costsAndPricesReader);

        final OrderReader reader = new OrderReader(ordersSourceDirectory, pricelessParser);

        final Collection<Order> orders = reader.readAll();

        orders.forEach(Order::printOrder);
    }
}