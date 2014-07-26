package ru.sazonovkirill.algorithms.lineartimesorting;

public class CountSort {
    public static int[] sort(int[] arr, int max) {
        int[] result = new int[arr.length];
        int[] tmp = new int[max];

        for (int i = 0; i < max; i++) tmp[i] = 0;
        for (int i = 0; i < arr.length; i++) tmp[arr[i]]++;
        for (int i = 1; i < tmp.length; i++) tmp[i] += tmp[i-1];
        for (int i = arr.length - 1; i >= 0; i--) {
            result[tmp[arr[i]]] = arr[i];
            tmp[arr[i]]--;
        }
        return result;
    }
}
