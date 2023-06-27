package org.example.parser.order;

import java.util.Map;

public final class PricedOrderParser extends DefaultOrderParser {

    public PricedOrderParser(String separator) {
        super(separator);
    }

    @Override
    protected Double getDishPrice(Map<String, String> fieldValueMap) {
        return Double.parseDouble(fieldValueMap.get(fields()[1]));
    }
}
