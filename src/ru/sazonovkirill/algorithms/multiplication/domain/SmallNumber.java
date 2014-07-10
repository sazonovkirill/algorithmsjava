package ru.sazonovkirill.algorithms.multiplication.domain;

public class SmallNumber {
    public static final int LENGTH = 2;
    public static final int MAXIMUM = (int)Math.pow(10, LENGTH);

    public static final SmallNumber ZERO = new SmallNumber(0);

    private final int value;

    public SmallNumber(int value) {
        if (value < 0 || value > MAXIMUM) {
            throw new IllegalArgumentException(String.valueOf(value));
        }
        this.value = value;
    }

    public SmallNumber(String s) {
        this(new Integer(s));
    }

    public boolean isZero() {
        return value == 0;
    }

    public int getValue() {
        return value;
    }

    public MultiplicationResult<SmallNumber> multiplyBy(SmallNumber anotherNumber) {
        int result = this.value * anotherNumber.value;
        if (result < MAXIMUM) {
            return new MultiplicationResult<SmallNumber>(new SmallNumber(result), new SmallNumber(0));
        } else {
            int overflow = result / MAXIMUM;
            result = result % MAXIMUM;
            return new MultiplicationResult<SmallNumber>(new SmallNumber(result), new SmallNumber(overflow));
        }
    }

    public AdditionResult<SmallNumber> add(SmallNumber anotherNumber) {
        int result = this.value + anotherNumber.value;
        if (result < MAXIMUM) {
            return new AdditionResult<SmallNumber>(new SmallNumber(result), new SmallNumber(0));
        } else {
            int overflow = result / MAXIMUM;
            result = result % MAXIMUM;
            return new AdditionResult<SmallNumber>(new SmallNumber(result), new SmallNumber(overflow));
        }
    }

    public static void main(String[] args) {
        System.out.println(new SmallNumber(5).add(new SmallNumber(5)));
        System.out.println(new SmallNumber(4).add(new SmallNumber(3)));
        System.out.println(new SmallNumber(9).add(new SmallNumber(9)));

        System.out.println(new SmallNumber(5).multiplyBy(new SmallNumber(5)));
        System.out.println(new SmallNumber(4).multiplyBy(new SmallNumber(3)));
        System.out.println(new SmallNumber(9).multiplyBy(new SmallNumber(9)));

        System.out.println(new SmallNumber(5).multiplyBy(new SmallNumber(5)).getResult().asNullPrependedString());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public String asNullPrependedString() {
        String s = String.valueOf(value);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < LENGTH - s.length(); i++) {
            result.append("0");
        }
        result.append(s);
        return result.toString();
    }
}
