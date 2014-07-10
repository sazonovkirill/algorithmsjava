package ru.sazonovkirill.algorithms.graphs;

import org.junit.Test;
import ru.sazonovkirill.algorithms.graphs.domain.ExampleGraph;
import ru.sazonovkirill.algorithms.graphs.domain.Vertex;

import java.util.List;
import java.util.Map;

public class BreadthFirstSearchTest {
    @Test
    public void testGraph1() {
        BreadthFirstSearch bfs = new BreadthFirstSearch(ExampleGraph.UNIDIRECTED_GRAPH_1);
        bfs.setEdgeVisitor(BreadthFirstSearch.LoggingEdgeVisitor);
        bfs.setVertexVisitor(BreadthFirstSearch.LoggingVertexVisitor);
        bfs.search("1");

        for (int i = 0; i < bfs.getLayersCount(); i++) {
            System.out.print("Layer " + i + ": ");
            for (Vertex vertex : bfs.getLayer(i)) {
                System.out.print(vertex.getId() + " ");
            }
            System.out.println();
        }

        for (String vertexId : ExampleGraph.UNIDIRECTED_GRAPH_1.getVerticesIds()) {
            System.out.println("Distance to " + vertexId + " is " + bfs.getDistanceToVertex(vertexId));
        }
    }
}
