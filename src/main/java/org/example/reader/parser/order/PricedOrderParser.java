package org.example.reader.parser.order;

import java.util.Map;

public final class PricedOrderParser extends OrderParser {

    public PricedOrderParser(String separator) {
        super(separator);
    }

    @Override
    protected Double getDishPrice(Map<String, String> fieldValueMap) {
        return Double.parseDouble(fieldValueMap.get(fields()[1]));
    }
}
