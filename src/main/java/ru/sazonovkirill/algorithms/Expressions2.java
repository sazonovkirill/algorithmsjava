package ru.sazonovkirill.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Expressions2 {
    public static class E {
        String str;
        int value;

        public E(String str, int value) {
            this.str = str;
            this.value = value;
        }
    }

    private static List<E> generate(String str) {
        List<E> result = new ArrayList<>();

        for (int i = 1; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i + 1);

            List<E> leftVariants = generate(left);
            List<E> rightVariants = generate(right);
            for (E l : leftVariants) {
                for (E r : rightVariants) {

                }
            }
        }

        result.add(new E(str, new Integer(str)));
        return result;
    }

    public static void main(String[] args) {
        /*
        List<String> result = generate("123");
        for (String s : result) System.out.println(s);
        System.out.println("Total: " + result.size());
        */
    }
}
