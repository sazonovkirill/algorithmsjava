package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.*;

public class BreadthFirstSearch {
    private final Graph graph;

    private IEdgeVisitor edgeVisitor;
    private IVertexVisitor vertexVisitor;

    private final List<List<Vertex>> layers = new LinkedList<>();
    private final Map<String, Integer> distances = new HashMap<>();

    public BreadthFirstSearch(Graph graph) {
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

        layers.clear();
        distances.clear();

        Queue<Vertex> q1 = new LinkedList<Vertex>();
        Queue<Vertex> q2 = new LinkedList<Vertex>();
        Queue<Vertex> currentQueue = q1;
        Queue<Vertex> anotherQueue = q2;
        currentQueue.add(sourceVertex);

        Set<Edge> visitedEdges = new HashSet<Edge>();
        Set<Vertex> visitedVertices = new HashSet<Vertex>();

        int layerNumber = 0;

        while (!(q1.isEmpty() && q2.isEmpty())) {
            if (currentQueue.size() == 0) {
                currentQueue = q1.size() > 0 ? q1 : q2;
                anotherQueue = q1.size() == 0 ? q1 : q2;
                layerNumber++;
            }

            Vertex currentVertex = currentQueue.poll();
            verticesStatuses.put(currentVertex.getId(), VertexStatus.EXPLORING);

            visitVertex(currentVertex);

            if (layerNumber >= layers.size()) layers.add(new ArrayList<>());
            layers.get(layerNumber).add(currentVertex);

            distances.put(currentVertex.getId(), layerNumber);

            for (Edge edge : currentVertex.getEdges()) {
                if (!visitedEdges.contains(edge)) {
                    visitEdge(edge);
                    visitedEdges.add(edge);

                    Vertex anotherVertex =  edge.getAnotherVertex(currentVertex);
                    if (!visitedVertices.contains(anotherVertex)) {
                        anotherQueue.add(anotherVertex);
                        visitedVertices.add(anotherVertex);
                    }
                }
            }

            verticesStatuses.put(currentVertex.getId(), VertexStatus.EXPLORED);
        }
    }

    public int getLayersCount() {
        return layers.size();
    }

    public List<Vertex> getLayer(int index) {
        return layers.get(index);
    }

    public int getDistanceToVertex(String vertexId) {
        return distances.get(vertexId);
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
