package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex {
    private final String id;
    private Object value;

    private final List<Edge> edges = new ArrayList<Edge>();

    private final Map<String, Object> attributes = new HashMap<String, Object>();

    public Vertex(String id) {
        this.id = id;
    }

    public Vertex(String id, Object value) {
        if (id == null) throw new IllegalArgumentException();

        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getOutgoingEdges() {
        List<Edge> result = new ArrayList<>();

        for (Edge edge : edges) {
            if (edge.getX().equals(this)) {
                result.add(edge);
            }
        }

        return result;
    }

    public List<Vertex> getAdjacentVertices() {
        List<Vertex> result = new ArrayList<>();

        for (Edge edge : edges) {
            edge.getAnotherVertex(this);
        }

        return result;
    }

    public Edge isConnectedTo(Vertex anotherVertex) {
        for (Edge edge : edges) {
            if (edge.getAnotherVertex(this).equals(anotherVertex)) {
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

        if (!id.equals(vertex.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "(" + id + ")";
    }
}
