package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private final Map<Integer, Integer> parent = new HashMap<>();

    public void connect(int v1, int v2) {
        int root1 = findRoot(v1);
        int root2 = findRoot(v2);

        parent.put(root2, root1);
    }

    public boolean isConnected(int v1, int v2) {
        int root1 = findRoot(v1);
        int root2 = findRoot(v2);
        return root1 == root2;
    }

    private int findRoot(int v1) {
        if (!parent.containsKey(v1)) parent.put(v1, v1);

        int result = parent.get(v1);
        while (result != v1) {
            v1 = result;
            result = parent.get(v1);
        }
        return result;
    }


}
