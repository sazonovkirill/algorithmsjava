package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import sun.security.provider.certpath.Vertex;

import java.util.*;

public class PrimsMST {
    private final Graph graph;

    public PrimsMST(Graph graph) {
        this.graph = graph;
    }

    public List<Edge> getMST() {
        Set<Integer> visitedVertices = new LinkedHashSet<>();
        visitedVertices.add(0);

        List<Edge> mst = new LinkedList<>();
        PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2) -> new Integer(e1.getWeight()).compareTo(e2.getWeight()));
        while (visitedVertices.size() < graph.getVertices().size()) {
            edges.clear();
            for (Edge edge : graph.getEdges()) {
                if ((visitedVertices.contains(edge.getX()) && !visitedVertices.contains(edge.getY())) ||
                    (visitedVertices.contains(edge.getY()) && !visitedVertices.contains(edge.getX()))) {
                    edges.add(edge);
                }
            }

            Edge nextEdge = edges.poll();
            Integer nextVertex = null;
            if (visitedVertices.contains(nextEdge.getX())) nextVertex = nextEdge.getY();
            else nextVertex = nextEdge.getX();

            mst.add(nextEdge);
            visitedVertices.add(nextVertex);
        }

        return mst;
    }
}
