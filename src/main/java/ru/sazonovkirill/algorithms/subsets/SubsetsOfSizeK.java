package ru.sazonovkirill.algorithms.subsets;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SubsetsOfSizeK {
    public static List<String> allSubsetsOfSizeK(List<String> items, int k) {
        List<String> result = new ArrayList<>();
        List<String> currentSubset = new ArrayList<>();
        allSubsetsOfSizeK(items, k, 0, currentSubset, result);
        return result;
    }

    private static void allSubsetsOfSizeK(List<String> items, int k, int startIndex, List<String> currentSubset, List<String> resultHolder) {
        if (currentSubset.size() == k) {
            resultHolder.add(StringUtils.join(currentSubset, ","));
            return;
        }

        for (int i = startIndex; i < items.size(); i++) {
            currentSubset.add(items.get(i));
            allSubsetsOfSizeK(items, k, i + 1, currentSubset, resultHolder);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        items.add("F");

        List<String> result = allSubsetsOfSizeK(items, 2);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
