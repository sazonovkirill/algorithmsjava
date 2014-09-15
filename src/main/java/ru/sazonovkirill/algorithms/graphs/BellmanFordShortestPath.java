package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.utils.ZippedArray;

import java.util.List;

public class BellmanFordShortestPath implements SingleSourceShortestPath {
    private final Graph graph;

    public BellmanFordShortestPath(Graph graph) {
        this.graph = graph;
    }

    public int[] compute(int source) {
        ZippedArray arr = ZippedArray.arrayWithDefaultValue(1000);

        int n = graph.getVerticesCount();
        arr.set(0, 0, source);

        for (int i = 1; i < n; i++) {
            for (int v = 0; v < n; v++) {
                int oldValue = (Integer) arr.get(i - 1, v);

                int minValue = Integer.MAX_VALUE;

                List<Edge> edges = graph.getEdgesTo(v);
                for (Edge edge : edges) {
                    int x = edge.getX();
                    int w = edge.getWeight();

                    int t = (Integer) arr.get(i - 1, x) + w;
                    if (t < minValue) minValue = t;
                }

                arr.set(Math.min(oldValue, minValue), i, v);
            }
        }

        int[] result = new int[graph.getVerticesCount()];
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            result[i] = (Integer) arr.get(n - 1, i);
        }
        return result;
    }

    public int getShortestPathValue(int from, int to) {
        return compute(from)[to];
    }
}
