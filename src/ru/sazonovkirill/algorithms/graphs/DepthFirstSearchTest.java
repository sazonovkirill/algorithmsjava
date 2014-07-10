package ru.sazonovkirill.algorithms.graphs;

import org.junit.Test;
import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.ExampleGraph;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.*;

public class DepthFirstSearchTest {
    @Test
    public void testGraph1() {
        DepthFirstSearch dfs = new DepthFirstSearch(ExampleGraph.UNIDIRECTED_GRAPH_1);
        dfs.setEdgeVisitor(DepthFirstSearch.LoggingEdgeVisitor);
        dfs.setVertexVisitor(DepthFirstSearch.LoggingVertexVisitor);
        dfs.search("1");
    }
}
