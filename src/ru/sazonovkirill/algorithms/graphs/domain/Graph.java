package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public Vertex getVertexById(String vertexId) {
        for (Vertex vertex : vertices) {
            if (vertex.getId().equals(vertexId)) {
                return vertex;
            }
        }

        return null;
    }

    public List<String> getVerticesIds() {
        List<String> result = new ArrayList<String>();

        for (Vertex vertex : vertices) {
            result.add(vertex.getId());
        }

        return result;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
