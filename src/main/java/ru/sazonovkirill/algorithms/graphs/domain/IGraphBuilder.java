package ru.sazonovkirill.algorithms.graphs.domain;

public interface IGraphBuilder {
    public void addVertex(String id);
    public void connect(String x, String y);
}
