package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;

import java.util.*;

public class DepthFirstSearch {
    private final Graph graph;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public void search(int source) {
        Stack<Integer> verticesToProcess = new Stack<>();
        verticesToProcess.add(source);

        Set<Edge> visitedEdges = new HashSet<>();
        Set<Integer> visitedVertices = new HashSet<>();

        while (verticesToProcess.size() > 0) {
            Integer currentVertex = verticesToProcess.pop();

            visitVertex(currentVertex);

            for (Edge edge : graph.getEdges(currentVertex)) {
                if (!visitedEdges.contains(edge)) {
                    visitEdge(edge);
                    visitedEdges.add(edge);

                    Integer anotherVertex =  edge.getAnotherVertex(currentVertex);
                    if (!visitedVertices.contains(anotherVertex)) {
                        verticesToProcess.add(anotherVertex);
                        visitedVertices.add(anotherVertex);
                    }
                }
            }
        }
    }

    private void visitVertex(int v) {
        System.out.println("Visiting vertex " + v);
    }

    private void visitEdge(Edge e) {
        System.out.println("Visiting edge " + e);
    }
}
