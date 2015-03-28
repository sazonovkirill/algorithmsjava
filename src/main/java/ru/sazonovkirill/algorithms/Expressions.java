package ru.sazonovkirill.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Expressions {
    public static List<String> generate(String arr) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        generate(arr, 0, current, result);
        return result;
    }

    private static void generate(String arr, int start, StringBuilder current, List<String> resultHolder) {
        if (start >= arr.length()) {
            resultHolder.add(current.toString());
        }

        for (int i = start + 1; i <= arr.length(); i++) {
            String left = arr.substring(start, i);
            current.append(left);

            if (i < arr.length()) {
                current.append("+");
                generate(arr, i, current, resultHolder);
                current.setLength(current.length() - 1);

                current.append("*");
                generate(arr, i, current, resultHolder);
                current.setLength(current.length() - 1);
            } else {
                generate(arr, i + 1, current, resultHolder);
            }

            current.setLength(current.length() - left.length());
        }
    }

    public static void main(String[] args) {
        List<String> result = generate("123");
        for (String s : result) System.out.println(s);
        System.out.println("Total: " + result.size());
    }
}
