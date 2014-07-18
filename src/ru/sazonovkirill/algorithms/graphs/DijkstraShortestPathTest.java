package ru.sazonovkirill.algorithms.graphs;

import junit.framework.Assert;
import org.junit.Test;
import ru.sazonovkirill.algorithms.graphs.domain.*;

import java.util.*;

public class DijkstraShortestPathTest {
    @Test
    public void testBasicGraph() {
        Graph graph = new Graph(5);

        builder.addVertices(1, 2, 3, 4, 5);
        builder.connect(1, 2, 1);
        builder.connect(1, 3, 3);
        builder.connect(2, 3, 1);
        builder.connect(4, 2, 1);
        builder.connect(3, 4, 1);
        builder.connect(5, 4, 1);
        builder.connect(2, 5, 1);

        DijkstraShortestPath dsp = new DijkstraShortestPath(builder.getGraph());
        int sp1_3 = dsp.getShortestPathValue("1", "3");
        int sp1_4 = dsp.getShortestPathValue("1", "4");

        Assert.assertEquals(sp1_3, 2);
        Assert.assertEquals(sp1_4, 3);
    }

    @Test
    public void testDimasExample() {
        DirectedGraphBuilder builder = new DirectedGraphBuilder();
        builder.addVertices(1, 2, 3, 4);
        builder.connect(1, 2, 2);
        builder.connect(2, 3, 2);
        builder.connect(3, 4, 2);
        builder.connect(1, 4, 5);

        DijkstraShortestPath dsp = new DijkstraShortestPath(builder.getGraph());
        int sp = dsp.getShortestPathValue("1", "4");
        Assert.assertEquals(sp, 5);
    }

    @Test
    public void testCyclicGraph() {
        DirectedGraphBuilder builder = new DirectedGraphBuilder();
        builder.addVertices(1, 2, 3, 4);
        builder.connect(1, 2, 2);
        builder.connect(2, 3, 2);
        builder.connect(3, 4, 2);
        builder.connect(4, 1, 2);

        DijkstraShortestPath dsp = new DijkstraShortestPath(builder.getGraph());
        int sp = dsp.getShortestPathValue("2", "1");
        Assert.assertEquals(sp, 6);
    }
}
