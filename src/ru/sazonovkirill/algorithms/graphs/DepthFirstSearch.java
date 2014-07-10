package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.*;

public class DepthFirstSearch {
    private final Graph graph;

    private IEdgeVisitor edgeVisitor;
    private IVertexVisitor vertexVisitor;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public void search(String sourceVertexId) {
        if (sourceVertexId == null || sourceVertexId.trim().equals("")) throw new IllegalArgumentException("Valid sourceVertexId has to be specified");
        Vertex sourceVertex = graph.getVertexById(sourceVertexId);
        if (sourceVertex == null) throw new IllegalArgumentException("No vertex with id: " + sourceVertexId);

        verticesStatuses.clear();
        for (String id : graph.getVerticesIds()) {
            verticesStatuses.put(id, VertexStatus.UNEXPLORED);
        }

        Stack<Vertex> verticesToProcess = new Stack<>();
        verticesToProcess.add(sourceVertex);

        Set<Edge> visitedEdges = new HashSet<>();
        Set<Vertex> visitedVertices = new HashSet<>();

        while (verticesToProcess.size() > 0) {
            Vertex currentVertex = verticesToProcess.pop();
            verticesStatuses.put(currentVertex.getId(), VertexStatus.EXPLORING);

            visitVertex(currentVertex);

            for (Edge edge : currentVertex.getEdges()) {
                if (!visitedEdges.contains(edge)) {
                    visitEdge(edge);
                    visitedEdges.add(edge);

                    Vertex anotherVertex =  edge.getAnotherVertex(currentVertex);
                    if (!visitedVertices.contains(anotherVertex)) {
                        verticesToProcess.add(anotherVertex);
                        visitedVertices.add(anotherVertex);
                    }
                }
            }

            verticesStatuses.put(currentVertex.getId(), VertexStatus.EXPLORED);
        }
    }

    private final Map<String, VertexStatus> verticesStatuses = new HashMap<String, VertexStatus>();

    static enum VertexStatus {
        UNEXPLORED,
        EXPLORING,
        EXPLORED
    }

    private void visitVertex(Vertex v) {
        if (vertexVisitor != null) {
            vertexVisitor.visit(graph, v);
        }
    }

    private void visitEdge(Edge e) {
        if (edgeVisitor != null) {
            edgeVisitor.visit(graph, e);
        }
    }

    public void setEdgeVisitor(IEdgeVisitor edgeVisitor) {
        this.edgeVisitor = edgeVisitor;
    }

    public void setVertexVisitor(IVertexVisitor vertexVisitor) {
        this.vertexVisitor = vertexVisitor;
    }

    public static interface IVertexVisitor {
        public void visit(Graph g, Vertex v);
    }

    public static interface IEdgeVisitor {
        public void visit(Graph g, Edge e);
    }

    public static class LoggingVertexVisitor implements IVertexVisitor {
        @Override
        public void visit(Graph g, Vertex v) {
            System.out.println("Visiting vertex " + v);
        }
    }

    public static class LoggingEdgeVisitor implements IEdgeVisitor {
        @Override
        public void visit(Graph g, Edge e) {
            System.out.println("Visiting edge " + e);
        }
    }

    public static LoggingEdgeVisitor LoggingEdgeVisitor = new LoggingEdgeVisitor();
    public static LoggingVertexVisitor LoggingVertexVisitor = new LoggingVertexVisitor();
}
