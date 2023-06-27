package org.example.reader;

import org.example.order.Order;
import org.example.parser.order.DefaultOrderParser;
import java.util.Collection;

public final class OrderReader extends DefaultFileReader {

    private final String sourceDirectory;
    private final DefaultOrderParser orderParser;

    public OrderReader(String sourceDirectory, DefaultOrderParser orderParser) {
        this.sourceDirectory = sourceDirectory;
        this.orderParser = orderParser;
    }

    public Collection<Order> readAll() {
        return listFiles(sourceDirectory)
                .stream()
                .map(file -> readOne(file.getName()))
                .toList();
    }

    public Order readOne(String fileName) {
        final String path = sourceDirectory + "/" + fileName;
        return orderParser.parse(loadResource(path));
    }
}
