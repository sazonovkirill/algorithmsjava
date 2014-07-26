package ru.sazonovkirill.algorithms.lineartimesorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static Integer[] sort(int[] array) {
        List<List<Integer>> t = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            t.add(i, new ArrayList<>());
        }

        for (int i = 0; i < array.length; i++) {
            int n = array[i];
            int bucket = n / 10;
            t.get(bucket).add(n);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Collections.sort(t.get(i));
            result.addAll(t.get(i));
        }

        Integer[] resultArray = new Integer[result.size()];
        result.toArray(resultArray);
        return resultArray;
    }
}
