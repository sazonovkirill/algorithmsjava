package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;

import java.util.*;

public class DijkstraShortestPath {
    private final Graph graph;
    private final int[] distances;
    private final int[] parents;

    public DijkstraShortestPath(Graph graph) {
        this.graph = graph;
        this.distances = new int[graph.getVerticesCount()];
        this.parents = new int[graph.getVerticesCount()];
    }

    public int getShortestPathValue(int source, int dest) {
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }

        final PriorityQueue<Integer> vertices = new PriorityQueue<>((v1, v2) -> {
            Integer v1Score = distances[v1];
            Integer v2Score = distances[v2];
            return v1Score.compareTo(v2Score);
        });

        for (int i = 0; i < graph.getVerticesCount(); i++) {
            vertices.add(i);
        }

        distances[source] = 0;
        vertices.remove(source);
        vertices.add(source);

        final Set<Integer> explored = new HashSet<>();
        while (explored.size() < graph.getVertices().size()) {
            int vertex = vertices.poll();
            explored.add(vertex);

            for (Edge outgoingEdge : graph.getEdges(vertex)) {
                int anotherVertex = outgoingEdge.getAnotherVertex(vertex);

                if (distances[vertex] + outgoingEdge.getWeight() < distances[anotherVertex]) {
                    distances[anotherVertex] =  distances[vertex] + outgoingEdge.getWeight();

                    vertices.remove(anotherVertex);
                    vertices.add(anotherVertex);
                }
            }

        }

        return distances[dest];
    }
}
