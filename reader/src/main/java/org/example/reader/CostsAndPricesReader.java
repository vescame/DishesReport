package org.example.reader;

import org.example.order.Dish;
import org.example.parser.costs.CostsAndPricesParser;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

public final class CostsAndPricesReader extends DefaultFileReader {

    private final String fileName;
    private final CostsAndPricesParser parser = new CostsAndPricesParser(" ");

    public CostsAndPricesReader(String fileName) {
        this.fileName = fileName;
    }

    public Collection<Dish> readAll() {
        Scanner dishesFile = loadResource(fileName);

        jumpHeader(dishesFile);

        Collection<Dish> dishes = new HashSet<>();

        while (dishesFile.hasNext()) {
           dishes.add(parser.parse(dishesFile));
        }

        return dishes;
    }

    private void jumpHeader(Scanner scanner) {
        scanner.nextLine();
    }
}
