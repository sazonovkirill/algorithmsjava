package ru.sazonovkirill.algorithms;

import static ru.sazonovkirill.algorithms.utils.Utils.arrayOf;
import static ru.sazonovkirill.algorithms.utils.Utils.print;
import static ru.sazonovkirill.algorithms.utils.Utils.swap;

public class DutchFlag {
    public static void groupInFour(int[] arr, int G1, int G2, int G3, int G4) {
        int g1 = 0, g2 = 0, g3 = 0;
        int g4 = arr.length - 1;

        while (g3 <= g4) {
            if (arr[g3] == G1) {
                swap(arr, g3, g1);
                g1++;
                swap(arr, g3, g2);
                g2++;
                g3++;
            } else if (arr[g3] == G2) {
                swap(arr, g3, g2);
                g2++;
                g3++;
            } else if (arr[g3] == G3) {
                g3++;
            } else {
                swap(arr, g3, g4);
                g4--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = arrayOf(10, 1, 2, 3, 4);
        print(arr);
        groupInFour(arr, 2, 3, 1, 4);
        print(arr);

    }
}
