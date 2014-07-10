package ru.sazonovkirill.algorithms.graphs;

import org.junit.Test;
import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.ExampleGraph;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.*;

public class DijkstraShortestPathTest {
    @Test
    public void testGraph2() {
        DijkstraShortestPath dsp = new DijkstraShortestPath(ExampleGraph.DIRECTED_GRAPH_2);
        System.out.println("1 -> 3 : " + dsp.getShortestPathValue("1", "3"));
        System.out.println("1 -> 4 : " + dsp.getShortestPathValue("1", "4"));
    }
}
