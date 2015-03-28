package ru.sazonovkirill.algorithms.lcs;

public class LongestCommonSequence {
    private final String a;
    private final String b;
    private String result = "";

    public LongestCommonSequence(String a, String b) {
        this.a = a;
        this.b = b;
    }

    int _lcs(int i, int j) {
        if (i < 0 || j < 0) return 0;

        System.out.println(a.substring(0, i+1) + " | " + b.substring(0, j+1));

        if (a.charAt(i) == b.charAt(j)) {
            return 1 + _lcs(i - 1, j - 1);
        } else {
            int v1 = _lcs(i - 1, j);
            int v2 = _lcs(i, j - 1);
            return Math.max(v1, v2);
        }
    }

    int lcs() {
        return _lcs(a.length() - 1, b.length() - 1);
    }

    public static void main(String[] args) {
        LongestCommonSequence lcs = new LongestCommonSequence("abcd", "abxd");
        System.out.println(String.valueOf(lcs.lcs()) + ": " + lcs.result);
    }
}
