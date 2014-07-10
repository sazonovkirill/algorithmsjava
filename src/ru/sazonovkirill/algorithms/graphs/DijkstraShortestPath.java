package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.*;

public class DijkstraShortestPath {
    private final Graph graph;

    public DijkstraShortestPath(Graph graph) {
        this.graph = graph;
    }

    private final Map<Vertex, Integer> distances = new HashMap<>();
    private final Map<Vertex, Integer> parents = new HashMap<>();

    private final Set<Vertex> unexplored = new HashSet<>();
    private final PriorityQueue<Edge> edges = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge e1, Edge e2) {
            return new Integer(e1.getWeight()).compareTo(e2.getWeight());
        }
    });

    public int getShortestPathValue(String sourceId, String destId) {
        distances.clear();
        parents.clear();
        unexplored.clear();
        edges.clear();

        Vertex sourceVertex = graph.getVertexById(sourceId);
        distances.put(sourceVertex, 0);
        parents.put(sourceVertex, null);

        unexplored.addAll(graph.getVertices());
        unexplored.remove(sourceVertex);

        edges.addAll(sourceVertex.getOutgoingEdges());

        while (unexplored.size() > 0) {
            Edge edge = edges.poll();
            Vertex x = edge.getX();
            Vertex y = edge.getY();

            if (!distances.containsKey(y) || distances.get(y) > (distances.get(x) + edge.getWeight())) {
                distances.put(y, distances.get(x) + edge.getWeight());
            }

            unexplored.remove(y);
            edges.addAll(y.getOutgoingEdges());
        }


        return distances.get(graph.getVertexById(destId));
    }
}
