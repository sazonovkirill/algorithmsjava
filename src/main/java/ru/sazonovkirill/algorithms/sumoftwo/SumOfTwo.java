package ru.sazonovkirill.algorithms.sumoftwo;

import java.util.*;

// 1) Numbers are NOT distinct
public class SumOfTwo {

    //
    //  Just find out if there is at least one pair
    //

    // Time: O(n^2) Space: O(1)
    public static boolean findIfThereIsAPair_naive(List<Stock> stocks, int sum) {
        for (int i = 0; i < stocks.size(); i++) {
            Stock iS = stocks.get(i);
            for (int j = i + 1; j < stocks.size(); j++) {
                Stock jS = stocks.get(j);

                if (iS.getValue() + jS.getValue() == sum) {
                    return true;
                }
            }
        }
        return false;
    }

    // Time: O(nlogn) (dominated by sort, otherwise O(n)) Space: O(1)
    public static boolean findIfThereIsAPair_sorting(List<Stock> stocks, int target) {
        Collections.sort(stocks, (s1, s2) -> { return s1.getValue().compareTo(s2.getValue()); });

        int lo = 0; int hi = stocks.size() - 1;
        while (lo < hi) {
            int sum = stocks.get(lo).getValue() + stocks.get(hi).getValue();
            if (sum == target) {
                return true;
            } else if (sum > target) {
                hi--;
            } else {
                lo++;
            }
        }

        return false;
    }

    // Works only if numbers are distinct
    // Time: O(nlong) Space: O(1)
    public static boolean findIfThereIsAPair_binarySearch(List<Stock> stocks, int target) {
        Collections.sort(stocks, (s1, s2) -> { return s1.getValue().compareTo(s2.getValue()); });

        for (int i = 0; i < stocks.size(); i++) {
            int current = stocks.get(i).getValue();
            int another = target - current;
            int idx = Collections.binarySearch(stocks, new Stock(null, another), (s1, s2) -> { return s1.getValue().compareTo(s2.getValue()); });
            if (idx >= 0 && idx != i) {
                return true;
            }
        }

        return false;
    }

    // Time: O(n) Space: O(n)
    public static boolean findIfThereIsAPart_hashmap(List<Stock> stocks, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stocks.size(); i++) {
            Integer value = stocks.get(i).getValue();
            if (!map.containsKey(value)) {
                map.put(value, 0);
            }
            Integer t = map.get(value);
            t += 1;
            map.put(value, t);
        }

        for (int i = 0; i < stocks.size(); i++) {
            int current = stocks.get(i).getValue();
            int another = target - current;

            if (map.get(another) >= (current == another ? 2 : 1)) {
                return true;
            }
        }

        return false;
    }

    //
    // Output all pairs
    //

    // Time: O(n^2) Space: O(1)
    public static void outputAllPairs_naive(List<Stock> stocks, int target) {
        for (int i = 0; i < stocks.size(); i++) {
            Stock si = stocks.get(i);
            for (int j = i + 1; j < stocks.size(); j++) {
                Stock sj = stocks.get(j);

                if (si.getValue() + sj.getValue() == target) {
                    System.out.println(si.getSymbol() + sj.getSymbol());
                }
            }
        }
    }

    // Time: O(n^2) Space: O(1)
    public static void outputAllPairs_sorted(List<Stock> stocks, int target) {
        Collections.sort(stocks, (s1, s2) -> { return s1.getValue().compareTo(s2.getValue()); });

        int lo = 0; int hi = stocks.size() - 1;
        while (lo < hi) {
            int sum = stocks.get(lo).getValue() + stocks.get(hi).getValue();
            if (sum == target) {
                System.out.println(stocks.get(lo).getSymbol() + stocks.get(hi).getSymbol());

                int lo2 = lo + 1;
                int hi2 = hi - 1;
                while (stocks.get(lo2).getValue().intValue() == stocks.get(lo).getValue().intValue() && lo2 < hi) {
                    System.out.println(stocks.get(lo2).getSymbol() + stocks.get(hi).getSymbol());
                    lo2++;
                }

                while (stocks.get(hi2).getValue().intValue() == stocks.get(hi).getValue().intValue() && hi2 > lo) {
                    System.out.println(stocks.get(lo).getSymbol() + stocks.get(hi2).getSymbol());
                    hi2--;
                }

                lo++;
                hi--;
            } else if (sum > target) {
                hi--;
            } else {
                lo++;
            }
        }
    }

    // Outputs "in any order"
    // Time: O(n^2) Space: O(n)
    public static void outputAllPairs_hashmap(List<Stock> stocks, int target) {
        Map<Integer, List<Stock>> map = new HashMap<>();
        for (int i = 0; i < stocks.size(); i++) {
            Stock si = stocks.get(i);
            if (!map.containsKey(si.getValue())) {
                map.put(si.getValue(), new ArrayList<>());
            }
            map.get(si.getValue()).add(si);
        }

        for (int i = 0; i < stocks.size(); i++) {
            Stock si = stocks.get(i);
            int another = target - si.getValue();
            List<Stock> ss = map.get(another);
            for (Stock s : ss) {
                if (!s.getSymbol().equals(si.getSymbol())) {
                    System.out.println(si.getSymbol() + s.getSymbol());
                }
            }
        }
    }

    public static class Stock {
        private final String symbol;
        private final Integer value;

        public Stock(String symbol, Integer value) {
            this.symbol = symbol;
            this.value = value;
        }

        public String getSymbol() {
            return symbol;
        }

        public Integer getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("A", 1));
        stocks.add(new Stock("B", 1));
        stocks.add(new Stock("C", 1));
        stocks.add(new Stock("D", 1));

        outputAllPairs_naive(stocks, 2);
        System.out.println();
        outputAllPairs_sorted(stocks, 2);
        System.out.println();
        outputAllPairs_hashmap(stocks, 2);
    }
}
