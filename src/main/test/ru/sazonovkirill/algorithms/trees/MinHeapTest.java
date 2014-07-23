package ru.sazonovkirill.algorithms.trees;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinHeapTest {
    @Test
    public void test_heapify_1() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(6, 1, 4, 2, 9, 6, 7, 8, 8));
        MinHeap<Integer> minHeap = MinHeap.heapify(numbers);
        Assert.assertEquals(minHeap.size(), numbers.size());

        while (minHeap.size() > 0) {
            int extracted = minHeap.extract();
            int min = Collections.min(numbers);
            Assert.assertEquals(extracted, min);
            numbers.remove(new Integer(min));
        }
    }

    @Test
    public void test_hepify_2() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(7, 3, 5, 1));
        MinHeap<Integer> minHeap = MinHeap.heapify(numbers);

        Assert.assertEquals(minHeap.size(), 4);
        Assert.assertEquals((int)minHeap.extract(), 1);
        Assert.assertEquals(minHeap.size(), 3);
        Assert.assertEquals((int)minHeap.extract(), 3);
        Assert.assertEquals(minHeap.size(), 2);
        Assert.assertEquals((int)minHeap.extract(), 5);
        Assert.assertEquals(minHeap.size(), 1);
        Assert.assertEquals((int)minHeap.extract(), 7);
        Assert.assertEquals(minHeap.size(), 0);
    }

    @Test
    public void test_insert() {
        MinHeap<Integer> minHeap = new MinHeap<>();
        minHeap.insert(7);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(1);

        Assert.assertEquals(minHeap.size(), 4);
        Assert.assertEquals((int)minHeap.extract(), 1);
        Assert.assertEquals(minHeap.size(), 3);
        Assert.assertEquals((int)minHeap.extract(), 3);
        Assert.assertEquals(minHeap.size(), 2);
        Assert.assertEquals((int)minHeap.extract(), 5);
        Assert.assertEquals(minHeap.size(), 1);
        Assert.assertEquals((int)minHeap.extract(), 7);
        Assert.assertEquals(minHeap.size(), 0);

        minHeap.insert(2);
        minHeap.insert(6);
        minHeap.insert(4);
        minHeap.insert(8);
        minHeap.insert(7);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(1);

        Assert.assertEquals(minHeap.size(), 8);
        Assert.assertEquals((int)minHeap.extract(), 1);
        Assert.assertEquals(minHeap.size(), 7);
        Assert.assertEquals((int)minHeap.extract(), 2);
        Assert.assertEquals(minHeap.size(), 6);
        Assert.assertEquals((int)minHeap.extract(), 3);
        Assert.assertEquals(minHeap.size(), 5);
        Assert.assertEquals((int)minHeap.extract(), 4);
        Assert.assertEquals(minHeap.size(), 4);
        Assert.assertEquals((int)minHeap.extract(), 5);
        Assert.assertEquals(minHeap.size(), 3);
        Assert.assertEquals((int)minHeap.extract(), 6);
        Assert.assertEquals(minHeap.size(), 2);
        Assert.assertEquals((int)minHeap.extract(), 7);
        Assert.assertEquals(minHeap.size(), 1);
        Assert.assertEquals((int)minHeap.extract(), 8);
        Assert.assertEquals(minHeap.size(), 0);
    }
}
