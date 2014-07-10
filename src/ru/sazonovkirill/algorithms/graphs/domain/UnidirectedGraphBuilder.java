package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.ArrayList;
import java.util.List;

public class UnidirectedGraphBuilder implements IGraphBuilder {
    private final List<Vertex> vertices = new ArrayList<Vertex>();
    private final List<Edge> edges = new ArrayList<Edge>();

    public Graph getGraph() {
        return new Graph(vertices, edges);
    }

    public void addVertex(String id) {
        vertices.add(new Vertex(id));
    }

    public void addVertices(int... ids) {
        for (int id : ids) {
            vertices.add(new Vertex(String.valueOf(id)));
        }
    }

    public void connect(String x, String y) {
        Vertex xv = getVertexById(x);
        Vertex yv = getVertexById(y);
        Edge edge = Edge.undirected(xv, yv);
        edges.add(edge);
        xv.getEdges().add(edge);
        yv.getEdges().add(edge);
    }

    public void connect(int x, int y) {
        connect(String.valueOf(x), String.valueOf(y));
    }

    private Vertex getVertexById(String id) {
        for (Vertex vertex : vertices) {
            if (vertex.getId().equals(id)) {
                return vertex;
            }
        }
        return null;
    }
}
