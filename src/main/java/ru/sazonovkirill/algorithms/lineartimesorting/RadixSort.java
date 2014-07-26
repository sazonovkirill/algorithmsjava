package ru.sazonovkirill.algorithms.lineartimesorting;

public class RadixSort {
    public static String[] sort(String[] strs, int maxlength) {
        String[] t = strs;
        for (int i = 0; i < maxlength; i++) {
            String[] p = countSubroutine(t, i);
            t = p;

        }
        return t;
    }

    private static String[] countSubroutine(String[] arr, int digit) {
        int max = 9;
        String[] result = new String[arr.length];
        int[] tmp = new int[max];

        for (int i = 0; i < max; i++) tmp[i] = 0;
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            int j = -1;
            if (digit < s.length()) j = new Integer("" + (s.charAt(s.length() - digit - 1)));
            else j = 0;

            tmp[j]++;
        }
        for (int i = 1; i < tmp.length; i++) tmp[i] += tmp[i-1];
        for (int i = arr.length - 1; i >= 0; i--) {
            String s = arr[i];
            int j = -1;
            if (digit < s.length()) j = new Integer("" + (s.charAt(s.length() - digit - 1)));
            else j = 0;

            result[tmp[j]] = arr[i];
            tmp[j]--;
        }
        return result;
    }
}
