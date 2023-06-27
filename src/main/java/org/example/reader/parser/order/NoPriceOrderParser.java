package org.example.reader.parser.order;

import org.example.Dish;
import org.example.reader.CostsAndPricesReader;
import java.util.Map;
import java.util.stream.Collectors;

public final class NoPriceOrderParser extends OrderParser {

    private final CostsAndPricesReader costsAndPricesReader;

    public NoPriceOrderParser(String separator, CostsAndPricesReader costsAndPricesReader) {
        super(separator);
        this.costsAndPricesReader = costsAndPricesReader;
    }

    @Override
    protected Double getDishPrice(Map<String, String> fieldValueMap) {
        collectDishes();
        final String name = getDishName(fieldValueMap);
        return dishes.get(name).price();
    }

    private void collectDishes() {
        if (dishes == null) {
            dishes = costsAndPricesReader.readAll()
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    Dish::name,
                                    dish -> dish
                            )
                    );
        }
    }

    private static Map<String, Dish> dishes;
}
