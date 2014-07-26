package ru.sazonovkirill.algorithms.graphs;

import junit.framework.Assert;
import org.junit.Test;
import ru.sazonovkirill.algorithms.graphs.domain.*;

import java.util.*;

public class DijkstraShortestPathTest {
    @Test
    public void testBasicGraph() {
        Graph graph = new Graph(5);

        graph.connectDirected(0, 1, 1);
        graph.connectDirected(0, 2, 3);
        graph.connectDirected(1, 2, 1);
        graph.connectDirected(3, 1, 1);
        graph.connectDirected(2, 3, 1);
        graph.connectDirected(4, 3, 1);
        graph.connectDirected(1, 4, 1);

        DijkstraShortestPath dsp = new DijkstraShortestPath(graph);
        int sp1_3 = dsp.getShortestPathValue(0, 2);
        int sp1_4 = dsp.getShortestPathValue(0, 3);

        Assert.assertEquals(sp1_3, 2);
        Assert.assertEquals(sp1_4, 3);
    }

    @Test
    public void testDimasExample() {
        Graph graph = new Graph(4);

        graph.connectDirected(0, 1, 2);
        graph.connectDirected(1, 2, 2);
        graph.connectDirected(2, 3, 2);
        graph.connectDirected(0, 3, 5);

        DijkstraShortestPath dsp = new DijkstraShortestPath(graph);
        int sp = dsp.getShortestPathValue(0, 3);
        Assert.assertEquals(sp, 5);
    }

    @Test
    public void testCyclicGraph() {
        Graph graph = new Graph(4);

        graph.connectDirected(0, 1, 2);
        graph.connectDirected(1, 2, 2);
        graph.connectDirected(2, 3, 2);
        graph.connectDirected(3, 0, 2);

        DijkstraShortestPath dsp = new DijkstraShortestPath(graph);
        int sp = dsp.getShortestPathValue(1, 0);
        Assert.assertEquals(sp, 6);
    }
}
