package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphBuilder {
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

    public void connect(int x, int y, int weight) {
        Vertex xv = getVertexById(String.valueOf(x));
        Vertex yv = getVertexById(String.valueOf(y));
        Edge edge = Edge.directedEdge(xv, yv);
        edge.setWeight(weight);
        edges.add(edge);
        xv.getEdges().add(edge);
        yv.getEdges().add(edge);
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
