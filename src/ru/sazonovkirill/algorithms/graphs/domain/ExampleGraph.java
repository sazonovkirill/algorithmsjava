package ru.sazonovkirill.algorithms.graphs.domain;

public class ExampleGraph {
    public static final Graph UNIDIRECTED_GRAPH_1 = buildUnidirectedGraph1();

    public static final Graph DIRECTED_GRAPH_2 = buildDirectedGraph2();

    private static Graph buildUnidirectedGraph1() {
        UnidirectedGraphBuilder builder = new UnidirectedGraphBuilder();

        builder.addVertices(1, 2, 3, 4, 5, 6, 7);
        builder.connect(1, 2);
        builder.connect(1, 3);
        builder.connect(2, 4);
        builder.connect(3, 4);
        builder.connect(4, 5);
        builder.connect(4, 6);
        builder.connect(5, 7);
        builder.connect(6, 7);

        return builder.getGraph();
    }

    private static Graph buildDirectedGraph2() {
        DirectedGraphBuilder builder = new DirectedGraphBuilder();

        builder.addVertices(1, 2, 3, 4, 5);
        builder.connect(1, 2, 1);
        builder.connect(1, 3, 3);
        builder.connect(2, 3, 1);
        builder.connect(4, 2, 1);
        builder.connect(3, 4, 1);
        builder.connect(5, 4, 1);
        builder.connect(2, 5, 1);

        return builder.getGraph();
    }
}
