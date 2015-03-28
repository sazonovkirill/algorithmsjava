package ru.sazonovkirill.algorithms.longestpalyndrome;

import static ru.sazonovkirill.algorithms.utils.Utils.*;

public class LongestPalyndrome {

    public static double findCenterOfTheLongestPalyndrome_naive(String s) {
        // To be implemented
        return 0;
    }

    public static double findCenterOfTheLongestPalyndrome_suboptimal(String s) {
        // To be implemented
        return 0;
    }

    public static double findCenterOfTheLongestPalyndrome_Manachers(String s) {
        String s2 = transformString(s);

        int[] aux = new int[s2.length()];

        int C = 0; int R = 0;
        aux[0] = 0;
        for (int i = 1; i < s2.length() - 1; i++) {
            int i2 = 2*C - i;
            aux[i] = i < R ? min(aux[i2], R - i) : 0;


            while (i + aux[i] + 1 < s2.length() &&
                   i - aux[i] - 1 >= 0 &&
                   (s2.charAt(i + aux[i] + 1) == s2.charAt(i - aux[i] - 1))) {
                aux[i]++;
            }

            if (i + aux[i] > R) {
                R = i + aux[i];
                C = i;
            }
        }

        double d = C / 2; // WRONG CONVERSION!!
        return d;
    }

    private static String transformString(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 2 + 1);
        sb.append('$');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append('$');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(findCenterOfTheLongestPalyndrome_Manachers("geeksskeeg"));
    }
}
