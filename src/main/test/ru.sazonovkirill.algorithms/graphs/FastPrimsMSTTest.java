package ru.sazonovkirill.algorithms.graphs;

import org.junit.Test;
import ru.sazonovkirill.algorithms.graphs.domain.Edge;
import ru.sazonovkirill.algorithms.graphs.domain.Graph;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FastPrimsMSTTest {
    @Test
    public void testCase_1() {
        Graph graph = new Graph(4);
        graph.connectUnDirected(0, 1, 2);
        graph.connectUnDirected(1, 2, 2);
        graph.connectUnDirected(2, 3, 2);
        graph.connectUnDirected(0, 3, 5);

        FastPrimsMST fastPrimsMST = new FastPrimsMST(graph);
        List<Edge> mst = fastPrimsMST.getMST();

        String actualMst = GraphUtils.encodeListOfEdgesAsString(mst);
        String expectedMst = "0-1:1-2:2-3:";

        assertEquals(expectedMst, actualMst);
    }

    @Test
    public void testCase_2() {
        Graph graph = new Graph(7);
        graph.connectUnDirected(0, 1, 1);
        graph.connectUnDirected(0, 2, 1);
        graph.connectUnDirected(0, 3, 4);

        graph.connectUnDirected(1, 2, 3);
        graph.connectUnDirected(1, 3, 2);
        graph.connectUnDirected(1, 4, 2);

        graph.connectUnDirected(2, 3, 1);
        graph.connectUnDirected(2, 5, 2);

        graph.connectUnDirected(3, 4, 1);
        graph.connectUnDirected(3, 5, 2);
        graph.connectUnDirected(3, 6, 4);

        graph.connectUnDirected(4, 5, 3);
        graph.connectUnDirected(4, 6, 1);

        graph.connectUnDirected(5, 6, 1);

        FastPrimsMST fastPrimsMST = new FastPrimsMST(graph);
        List<Edge> mst = fastPrimsMST.getMST();

        String actualMst = GraphUtils.encodeListOfEdgesAsString(mst);
        String expectedMst = "0-1:0-2:2-3:3-4:4-6:5-6:";

        assertEquals(expectedMst, actualMst);
    }

    @Test
    public void testGraphOfSize2() {
        Graph graph = new Graph(2);
        graph.connectUnDirected(0, 1, 1);

        FastPrimsMST fastPrimsMST = new FastPrimsMST(graph);
        List<Edge> mst = fastPrimsMST.getMST();
        assertNotNull(mst);
        assertEquals(1, mst.size());

        String actualMst = GraphUtils.encodeListOfEdgesAsString(mst);
        String expectedMst = "0-1:";

        assertEquals(expectedMst, actualMst);
    }

    @Test
    public void testGraphOfSize3() {
        Graph graph = new Graph(3);
        graph.connectUnDirected(0, 1, 1);
        graph.connectUnDirected(1, 2, 1);
        graph.connectUnDirected(2, 0, 1);

        FastPrimsMST fastPrimsMST = new FastPrimsMST(graph);
        List<Edge> mst = fastPrimsMST.getMST();
        assertNotNull(mst);
        assertEquals(2, mst.size());

        String actualMst = GraphUtils.encodeListOfEdgesAsString(mst);
        String expectedMst = "0-1:2-0:";

        assertEquals(expectedMst, actualMst);
    }

    @Test
    public void testEmptyGraph() {
        Graph graph = new Graph(0);

        FastPrimsMST fastPrimsMST = new FastPrimsMST(graph);
        List<Edge> mst = fastPrimsMST.getMST();
        assertNotNull(mst);
        assertEquals(0, mst.size());
    }

    @Test
    public void testGraphOfSize1() {
        Graph graph = new Graph(1);

        FastPrimsMST fastPrimsMST = new FastPrimsMST(graph);
        List<Edge> mst = fastPrimsMST.getMST();
        assertNotNull(mst);
        assertEquals(0, mst.size());
    }

    @Test
    public void testDirectedGraph() {
        /*
        In some cases Prims MST can work for directed graphs too, but usually not
         */

        Graph graph = new Graph(3);
        graph.connectDirected(0, 1, 1);
        graph.connectDirected(2, 1, 1);
        graph.connectDirected(0, 2, 2);

        FastPrimsMST fastPrimsMST = new FastPrimsMST(graph);
        List<Edge> mst = fastPrimsMST.getMST();
        assertNotNull(mst);
        assertEquals(2, mst.size());

        String actualMst = GraphUtils.encodeListOfEdgesAsString(mst);
        String expectedMst = "0-1:0-2:";

        assertEquals(expectedMst, actualMst);
    }

}
