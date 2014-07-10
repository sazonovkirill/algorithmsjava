package ru.sazonovkirill.algorithms.multiplication;

import ru.sazonovkirill.algorithms.multiplication.domain.AdditionResult;
import ru.sazonovkirill.algorithms.multiplication.domain.LargeNumber;
import ru.sazonovkirill.algorithms.multiplication.domain.MultiplicationResult;
import ru.sazonovkirill.algorithms.multiplication.domain.SmallNumber;

import java.util.ArrayList;
import java.util.List;

// Steps:
// - Tell generic algorithm
// - Implement addition for small numbers
// - Implement multiplication for small numbers
// - Implement addition for large numbers
// - Implement generic algorithm

public class NaiveMultiplication {
    private final LargeNumber x;
    private final LargeNumber y;

    public NaiveMultiplication(LargeNumber x, LargeNumber y) {
        this.x = x;
        this.y = y;
    }

    public LargeNumber getProduct() {
        LargeNumber result = LargeNumber.ZERO;

        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < y.size(); j++) {
                MultiplicationResult<SmallNumber> subProduct = x.getDigit(i).multiplyBy(y.getDigit(j));
                List<SmallNumber> digits = new ArrayList<SmallNumber>();
                for (int k = 0; k < i + j; k++) {
                    digits.add(SmallNumber.ZERO);
                }
                digits.add(subProduct.getResult());
                digits.add(subProduct.getOverflow());

                result = result.add(new LargeNumber(digits));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new NaiveMultiplication(new LargeNumber("1225"), new LargeNumber("7112")).getProduct());
    }
}
