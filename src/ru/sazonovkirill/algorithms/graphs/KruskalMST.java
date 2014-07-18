package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.DisjointSet;
import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;

import java.util.*;

public class KruskalMST {
    private final Graph graph;
    public KruskalMST(Graph graph) {
        this.graph = graph;
    }

    public List<Edge> getMST() {
        PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2) -> new Integer(e1.getWeight()).compareTo(e2.getWeight()));
        edges.addAll(graph.getEdges());

        List<Edge> mst = new ArrayList<>();
        DisjointSet ds = new DisjointSet();
        Set<Integer> explored = new HashSet<>();
        while (explored.size() < graph.getVertices().size()) {
            Edge e = edges.poll();
            if (!ds.isConnected(e.getX(), e.getY())) {
                ds.connect(e.getX(), e.getY());
                explored.add(e.getX());
                explored.add(e.getY());
                mst.add(e);
            }
        }
        return mst;
    }
}
