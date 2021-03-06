package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;

import java.util.*;

public class FastPrimsMST {
    private final Graph graph;

    public FastPrimsMST(Graph graph) {
        this.graph = graph;
    }

    public List<Edge> getMST() {
        final Edge[] minimumEdgesWeight = new Edge[graph.getVerticesCount()];
        final Set<Integer> visited = new HashSet<>();
        final PriorityQueue<Integer> vertices = new PriorityQueue<>((v1, v2) -> {
            Edge e1 = minimumEdgesWeight[v1];
            Edge e2 = minimumEdgesWeight[v2];
            Integer v1MinimumEdgeWeight = e1 != null ? e1.getWeight() : Integer.MAX_VALUE;
            Integer v2MinimumEdgeWeight = e2 != null ? e2.getWeight() : Integer.MAX_VALUE;
            return v1MinimumEdgeWeight.compareTo(v2MinimumEdgeWeight);
        });

        vertices.add(0);

        List<Edge> mst = new LinkedList<>();
        while (visited.size() < graph.getVerticesCount()) {
            int nextVertex = vertices.poll();
            if (nextVertex != 0) {
                Edge nextEdge = minimumEdgesWeight[nextVertex];
                mst.add(nextEdge);
            }

            visited.add(nextVertex);

            for (Edge edge : graph.getEdges(nextVertex)) {
                int anotherVertex = edge.getAnotherVertex(nextVertex);
                if (!visited.contains(anotherVertex)) {
                    if ((minimumEdgesWeight[anotherVertex] == null) || (
                        (minimumEdgesWeight[anotherVertex] != null) && (edge.getWeight() < minimumEdgesWeight[anotherVertex].getWeight()))) {
                        minimumEdgesWeight[anotherVertex] = edge;

                        vertices.remove(anotherVertex);
                        vertices.add(anotherVertex);
                    }
                }
            }
        }

        return mst;
    }
}
