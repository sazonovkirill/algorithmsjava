package ru.sazonovkirill.algorithms.graphs;

import org.junit.Test;
import ru.sazonovkirill.algorithms.graphs.domain.*;

import java.util.*;

public class KruskalMSTTest {
    @Test
    public void testBasicCase() {
        Graph graph = new Graph(4);
        graph.addEdge(new Edge(1, 2, 1, false));
        graph.addEdge(new Edge(1, 3, 3, false));
        graph.addEdge(new Edge(2, 3, 1, false));
        graph.addEdge(new Edge(3, 4, 3, false));

        List<Edge> mst = new KruskalMST(graph).getMST();
    }
}
