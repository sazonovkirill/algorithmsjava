package ru.sazonovkirill.algorithms.multiplication.domain;

import ru.sazonovkirill.algorithms.multiplication.utils.StringReverse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LargeNumber {
    public static final LargeNumber ZERO = new LargeNumber(SmallNumber.ZERO);

    private final List<SmallNumber> digits = new ArrayList<SmallNumber>();

    private LargeNumber() {
    }

    public LargeNumber(String value) {
        if (value == null) throw new IllegalArgumentException();

        try {
            new BigInteger(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(value);
        }

        value = StringReverse.reverse(value);
        for (int i = 0; i < value.length(); i += SmallNumber.LENGTH) {
            int j = i + SmallNumber.LENGTH;
            if (j > value.length()) {
                j = value.length();
            }
            String digitStr = value.substring(i, j);
            digits.add(new SmallNumber(StringReverse.reverse(digitStr)));
        }
    }

    public LargeNumber(SmallNumber... digits) {
        Collections.addAll(this.digits, digits);
    }

    public LargeNumber(Iterable<SmallNumber> digits) {
        for (SmallNumber digit : digits) {
            this.digits.add(digit);
        }
    }

    // Caveats:
    // 1) Don't forget, that sum goest until there is no overflow OR there are no digits is numbers
    // 2) Don't forget to trim leading zero after summation

    public LargeNumber add(LargeNumber other) {
        LargeNumber result = new LargeNumber();

        int i = 0;
        AdditionResult<SmallNumber> t = this.getDigit(i).add(other.getDigit(i));
        result.digits.add(t.getResult());
        while ((t.getOverflow() != null && !t.getOverflow().isZero()) || (i < Math.max(this.size(), other.size()))) {
            i++;

            SmallNumber overflow = t.getOverflow();

            SmallNumber thisDigit = this.hasDigit(i) ? this.getDigit(i) : SmallNumber.ZERO;
            SmallNumber otherDigit = other.hasDigit(i) ? other.getDigit(i) : SmallNumber.ZERO;

            t = thisDigit.add(otherDigit);
            AdditionResult<SmallNumber> p = t.getResult().add(overflow);
            result.digits.add(p.getResult());
        }

        result.trim();
        return result;
    }

    public boolean hasDigit(int index) {
        return index >= 0 && index < digits.size();
    }

    public SmallNumber getDigit(int index) {
        return digits.get(index);
    }

    public int size() {
        return digits.size();
    }

    private void trim() {
        while (digits.get(digits.size() - 1).isZero()) digits.remove(digits.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            if (i == digits.size() - 1) sb.append(digits.get(i).toString());
            else sb.append(digits.get(i).asNullPrependedString());
        }
        return sb.toString();
    }

    public String asNullPrependedString() {
        StringBuilder sb = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; i--) {
            sb.append(digits.get(i).asNullPrependedString());
        }
        return sb.toString();
    }

    public String getDelimitedDigits() {
        StringBuilder sb = new StringBuilder();
        String delimiter = ", ";
        for (SmallNumber digit : digits) {
            sb.append(digit.asNullPrependedString());
            sb.append(delimiter);
        }
        sb.setLength(sb.length() - delimiter.length());
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LargeNumber("12345"));
        System.out.println(new LargeNumber("12345").asNullPrependedString());
        System.out.println(new LargeNumber("12345").getDelimitedDigits());

        System.out.println(new LargeNumber("111").add(new LargeNumber("222")).toString());
    }
}
