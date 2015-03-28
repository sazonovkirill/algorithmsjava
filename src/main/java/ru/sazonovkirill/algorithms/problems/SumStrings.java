package ru.sazonovkirill.algorithms.problems;

import org.apache.commons.lang3.StringUtils;

public class SumStrings {
    private static final int BASE = 10;

    public static String sum(String s1, String s2) {
        if (StringUtils.isBlank(s1)) throw new IllegalArgumentException();
        if (StringUtils.isBlank(s2)) throw new IllegalArgumentException();

        StringBuilder result = new StringBuilder();
        String rs1 = StringUtils.reverse(s1);
        String rs2 = StringUtils.reverse(s2);

        int overflow = 0;
        for (int i = 0; i < Math.max(s1.length(), s2.length()); i++) {
            int i1 = (rs1.length() > i) ? new Integer(String.valueOf(rs1.charAt(i))) : 0;
            int i2 = (rs2.length() > i) ? new Integer(String.valueOf(rs2.charAt(i))) : 0;

            int r = i1 + i2 + overflow;
            int r1 = r % BASE;
            overflow = r / BASE;

            result.append(String.valueOf(r1));
        }

        if (overflow > 0) {
            result.append(String.valueOf(overflow));
        }

        return StringUtils.reverse(result.toString());
    }
}
