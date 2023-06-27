package org.example.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ColumnTextParser {
    private final String separator;
    public ColumnTextParser(String separator) {
        this.separator = separator;
    }

    public List<Map<String, String>> collectAll(List<String> lines) {
        return lines.stream()
                .map(this::doMapping)
                .toList();
    }

    public Map<String, String> collectOne(String line) {
        return doMapping(line);
    }

    private Map<String, String> doMapping(String line) {
        String[] values = line.split(separator);
        final Map<String, Integer> fieldsPosition = IntStream
                .range(0, fields().length)
                .boxed()
                .collect(
                        Collectors.toMap(
                                Arrays.stream(fields()).toList()::get,
                                k -> k
                        )
                );

        return fieldsPosition
                .entrySet()
                .stream()
                .collect(HashMap::new, (m, v) -> m.put(v.getKey(), nullableColumnValue(v.getValue(), values)), HashMap::putAll);
//                        Collectors.toMap( Map.Entry::getKey,
//                                k -> nullableColumnValue(k.getValue(), values)
//                        )
//                );
    }

    private String nullableColumnValue(Integer position, String[] values) {
        try {
           return values[position];
        } catch (IndexOutOfBoundsException ignored) {
            return null;
        }
    }

    abstract protected String[] fields();

}
