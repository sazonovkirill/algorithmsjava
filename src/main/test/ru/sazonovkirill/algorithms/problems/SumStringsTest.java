package ru.sazonovkirill.algorithms.problems;

import org.junit.Assert;
import org.junit.Test;

public class SumStringsTest {
    @Test(expected = IllegalArgumentException.class)
    public void test_sum_null_111_expectException() {
        String s = SumStrings.sum(null, "111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_sum_111_null_expectException() {
        String s = SumStrings.sum("111", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_sum_null_null_expectException() {
        String s = SumStrings.sum(null, null);
    }

    @Test
    public void test_sum_0_0_expect_0() {
        String s = SumStrings.sum("0", "0");
        Assert.assertEquals(s, "0");
    }

    @Test
    public void test_sum_0_1_expect_1() {
        String s = SumStrings.sum("0", "1");
        Assert.assertEquals(s, "1");
    }

    @Test
    public void test_sum_1_0_expect_1() {
        String s = SumStrings.sum("1", "0");
        Assert.assertEquals(s, "1");
    }

    @Test
    public void test_sum_1_1_expect_2() {
        String s = SumStrings.sum("1", "1");
        Assert.assertEquals(s, "2");
    }

    @Test
    public void test_sum_123_4_expect_127() {
        String s = SumStrings.sum("123", "4");
        Assert.assertEquals(s, "127");
    }

    @Test
    public void test_sum_1_234_expect_235() {
        String s = SumStrings.sum("1", "234");
        Assert.assertEquals(s, "235");
    }

    @Test
    public void test_sum_9_9_expect_18() {
        String s = SumStrings.sum("9", "9");
        Assert.assertEquals(s, "18");
    }

    @Test
    public void test_sum_999_1_expect_1000() {
        String s = SumStrings.sum("999", "1");
        Assert.assertEquals(s, "1000");
    }

    @Test
    public void test_sum_1_999_expect_1000() {
        String s = SumStrings.sum("999", "1");
        Assert.assertEquals(s, "1000");
    }
}
