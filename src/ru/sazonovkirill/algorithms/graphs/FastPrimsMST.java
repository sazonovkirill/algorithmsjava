package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.*;

public class FastPrimsMST {
    private final Graph graph;

    public FastPrimsMST(Graph graph) {
        this.graph = graph;
    }

    public List<Edge> getMST() {
        final Map<Vertex, Edge> minimumEdgesWeight = new HashMap<>();
        final PriorityQueue<Vertex> vertices = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                Edge e1 = minimumEdgesWeight.get(v1);
                Edge e2 = minimumEdgesWeight.get(v2);
                Integer v1MinimumEdgeWeight = e1 != null ? minimumEdgesWeight.get(v1).getWeight() : Integer.MAX_VALUE;
                Integer v2MinimumEdgeWeight = e2 != null ? minimumEdgesWeight.get(v2).getWeight() : Integer.MAX_VALUE;
                return v1MinimumEdgeWeight.compareTo(v2MinimumEdgeWeight);
            }
        });

        Vertex sourceVertex = graph.getVertices().get(0);

        for (Vertex vertex : graph.getVertices()) {
            if (vertex.equals(sourceVertex)) continue;

            Edge edge = sourceVertex.isConnectedTo(vertex);
            if (edge != null) {
                minimumEdgesWeight.put(vertex, edge);
            } else {
                minimumEdgesWeight.put(vertex, null);
            }
            vertices.add(vertex);
        }

        List<Edge> mst = new LinkedList<>();

        while (vertices.size() > 0) {
            Vertex nextVertex = vertices.poll();
            Edge nextEdge = minimumEdgesWeight.get(nextVertex);

            mst.add(nextEdge);

            for (Vertex vertex : nextVertex.getAdjacentVertices()) {
                Edge edge = nextVertex.isConnectedTo(vertex);
                if (edge.getWeight() < minimumEdgesWeight.get(vertex).getWeight()) {
                    minimumEdgesWeight.put(vertex, edge);
                }
            }
        }

        return mst;
    }
}
