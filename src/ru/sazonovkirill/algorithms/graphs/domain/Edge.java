package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.HashMap;
import java.util.Map;

public class Edge {
    private static final String WEIGHT_ATTRIBUTE = "__weight";

    private final Vertex x;
    private final Vertex y;

    private final boolean isDirected;

    private Object value;
    private final Map<String, Object> attributes = new HashMap<String, Object>();

    private Edge(Vertex x, Vertex y, boolean isDirected) {
        if (x.equals(y)) throw new IllegalArgumentException();

        this.x = x;
        this.y = y;
        this.isDirected = isDirected;
    }

    public static Edge directedEdge(Vertex from, Vertex to) {
        return new Edge(from, to, true);
    }

    public static Edge undirected(Vertex x, Vertex y) {
        return new Edge(x, y, false);
    }

    public Vertex getX() {
        return x;
    }

    public Vertex getY() {
        return y;
    }

    public boolean isDirected() {
        return isDirected;
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

    public int getWeight() {
        return (int) getAttribute(WEIGHT_ATTRIBUTE);
    }

    public void setWeight(int weight) {
        setAttribute(WEIGHT_ATTRIBUTE, weight);
    }

    public Vertex getAnotherVertex(Vertex v) {
        if (x.equals(v)) return y;
        else if (y.equals(v)) return x;
        else return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (isDirected != edge.isDirected) return false;
        if (!x.equals(edge.x)) return false;
        if (!y.equals(edge.y)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        result = 31 * result + (isDirected ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        if (isDirected) {
            return "(" + x + "->" + y + ")";
        } else {
            return "(" + x + "-" + y + ")";
        }

    }
}
