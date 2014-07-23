package ru.sazonovkirill.algorithms.trees;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class BinaryTreeNodeTest {
    @Test
    public void testBasicOperations() {
        Random r = new Random();
        List<Integer> numbers = new ArrayList<>();
        Set<Integer> numbersSet = new HashSet<>();
        while (numbers.size() < 9) {
            Integer nextInt = r.nextInt(10);
            if (!numbersSet.contains(nextInt)) {
                numbers.add(nextInt);
                numbersSet.add(nextInt);
            }
        }

        List<Integer> sortedNumbers = new ArrayList<>();
        sortedNumbers.addAll(numbers);
        Collections.sort(sortedNumbers);

        BinaryTreeNode<Integer, Integer> tree = BinaryTreeNode.createRoot(numbers.get(0), numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            tree.insert(numbers.get(i), numbers.get(i));
        }

        int min = tree.getMinimum().getKey();
        int max = tree.getMaximum().getKey();

        int expectedMinimum = sortedNumbers.get(0);
        int expectedMaximum = sortedNumbers.get(sortedNumbers.size() - 1);

        Assert.assertEquals(expectedMinimum, min);
        Assert.assertEquals(expectedMaximum, max);

        List<Integer> numbersFromTree = new ArrayList<>();
        BinaryTreeNode<Integer, Integer> it = tree.getMinimum();
        while (it != null) {
            numbersFromTree.add(it.getKey());
            it = it.getNext();
        }

        Assert.assertArrayEquals(sortedNumbers.toArray(), numbersFromTree.toArray());
    }
}
