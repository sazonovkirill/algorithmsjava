package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Vertex {
    private final int id;
    private Object value;

    private final List<Edge> edges = new ArrayList<Edge>();

    public Vertex(int id) {
        this.id = id;
    }

    public Vertex(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getAdjacentVertices() {
        List<Vertex> result = new ArrayList<>();

        for (Edge edge : edges) {
            edge.getAnotherVertex(id);
        }

        return result;
    }

    public Edge isConnectedTo(Vertex anotherVertex) {
        for (Edge edge : edges) {
            if (edge.getAnotherVertex(id) == anotherVertex.id) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (id != vertex.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "(" + id + ")";
    }
}
