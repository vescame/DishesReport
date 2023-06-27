package org.example.parser.order;

import org.example.order.Dish;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public final class NoPriceOrderParser extends DefaultOrderParser {

    private final Map<String, Dish> dishesCostsAndPrices;

    public NoPriceOrderParser(String separator, Collection<Dish> dishes) {
        super(separator);
        this.dishesCostsAndPrices = associateDishes(dishes);
    }

    @Override
    protected Double getDishPrice(Map<String, String> fieldValueMap) {
        final String name = getDishName(fieldValueMap);
        return dishesCostsAndPrices.get(name).price();
    }

    private Map<String, Dish> associateDishes(Collection<Dish> dishes) {
        return dishes.stream().collect(
                Collectors.toMap(
                        Dish::name,
                        dish -> dish
                )
        );
    }
}
