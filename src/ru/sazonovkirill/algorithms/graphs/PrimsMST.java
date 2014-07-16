package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.*;

public class PrimsMST {
    private final Graph graph;

    public PrimsMST(Graph graph) {
        this.graph = graph;
    }

    public List<Edge> getMST() {
        Set<Vertex> visitedVertices = new LinkedHashSet<>();
        visitedVertices.add(graph.getVertices().get(0));

        final PriorityQueue<Edge> edges = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return new Integer(e1.getWeight()).compareTo(e2.getWeight());
            }
        });
        edges.addAll(graph.getEdges());

        List<Edge> mst = new LinkedList<>();

        while (visitedVertices.size() < graph.getVertices().size()) {
            Edge nextEdge = null;
            Vertex nextVertex = null;
            for (Edge edge : edges) {
                if (visitedVertices.contains(edge.getX()) && !visitedVertices.contains(edge.getY())) {
                    nextEdge = edge;
                    nextVertex = edge.getY();
                } else if (visitedVertices.contains(edge.getY()) && !visitedVertices.contains(edge.getX())) {
                    nextEdge = edge;
                    nextVertex = edge.getX();
                }
            }

            mst.add(nextEdge);
            visitedVertices.add(nextVertex);
        }

        return mst;
    }
}
