package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.*;

public class Graph {
    private final Vertex[] vertices;
    private final List<Edge> edges = new ArrayList<>();

    public Graph(int verticesCount) {
        vertices = new Vertex[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            vertices[i] = new Vertex(i);
        }
    }

    public Vertex getVertex(int vertex) {
        if (vertex < 0 || vertex >= vertices.length) throw new IllegalArgumentException();
        return vertices[vertex];
    }

    public void addEdge(Edge edge) {
        if (edge == null) throw new IllegalArgumentException();

        if (edge.getX() < 0 || edge.getX() >= vertices.length ||
            edge.getY() < 0 || edge.getY() >= vertices.length) {
            throw new IllegalArgumentException("Wrong edge parameters");
        }

        for (Edge e : edges) {
            if (e.equals(edge)) {
                throw new IllegalArgumentException("Edge with such parameters already exists");
            }
        }

        edges.add(edge);

        if (edge.isDirected()) {
            vertices[edge.getX()].getEdges().add(edge);
        } else {
            vertices[edge.getX()].getEdges().add(edge);
            vertices[edge.getY()].getEdges().add(edge);
        }
    }

    public List<Edge> getEdgesFrom(int vertex) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getX() == vertex) {
                result.add(edge);
            }
        }
        return result;
    }

    public List<Edge> getEdgesTo(int vertex) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getY() == vertex) {
                result.add(edge);
            }
        }
        return result;
    }

    public void connectDirected(int x, int y, int weight) {
        addEdge(new Edge(x, y, weight, true));
    }

    public void connectUnDirected(int x, int y, int weight) {
        addEdge(new Edge(x, y, weight, false));
    }

    public List<Vertex> getVertices() {
        return Arrays.asList(vertices);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getEdges(int vertex) {
        return vertices[vertex].getEdges();
    }

    public int getVerticesCount() {
        return vertices.length;
    }
}
