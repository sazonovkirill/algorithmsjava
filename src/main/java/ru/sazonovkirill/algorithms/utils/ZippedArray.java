package ru.sazonovkirill.algorithms.utils;

import java.util.HashMap;
import java.util.Map;

public class ZippedArray {
    private final Object defaultValue;
    private final Map<String, Object> items = new HashMap<>();

    public ZippedArray()
    {
        this.defaultValue = null;
    }

    private ZippedArray(Object defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public static ZippedArray arrayWithDefaultValue(Object defaultValue) {
        return new ZippedArray(defaultValue);
    }

    public Object get(int... indices) {
        String key = getKey(indices);

        if (items.containsKey(key)) return items.get(key);
        else return defaultValue;
    }

    public void set(Object value, int... indices) {
        String key = getKey(indices);

        items.put(key, value);
    }

    private String getKey(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int i : indices) {
            sb.append(String.valueOf(i) + "#");
        }
        return sb.toString();
    }
}
