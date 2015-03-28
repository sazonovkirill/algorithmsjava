package ru.sazonovkirill.algorithms.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.join;

public class Utils {
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static int[] arrayOf(int length, int... t) {
        int[] arr = new int[length];
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = t[r.nextInt(t.length)];
        }
        return arr;
    }

    public static void print(int[] arr) {
        System.out.println("(" + StringUtils.join(arr, ',') + ")");

    }
}
