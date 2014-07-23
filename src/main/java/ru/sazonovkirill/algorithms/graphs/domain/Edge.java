package ru.sazonovkirill.algorithms.graphs.domain;

import java.util.HashMap;
import java.util.Map;

public class Edge {
    private final int x;
    private final int y;

    private final boolean isDirected;
    private int weight = 1;

    private Object value;

    public Edge(int x, int y, int weight, boolean isDirected) {
        if(x == y) throw new IllegalArgumentException();

        this.x = x;
        this.y = y;
        this.weight = weight;
        this.isDirected = isDirected;
    }

    public Edge(int x, int y, boolean isDirected) {
        if(x == y) throw new IllegalArgumentException();

        this.x = x;
        this.y = y;
        this.weight = weight;
        this.isDirected = isDirected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getAnotherVertex(int v) {
        if (x == v) return y;
        else if (y == v) return x;
        else return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (x != edge.x) return false;
        if (y != edge.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        if (isDirected) {
            return "(" + x + "-|" + weight + "|>" + y + ")";
        } else {
            return "(" + x + "-|" + weight + "|-" + y + ")";
        }
    }
}
