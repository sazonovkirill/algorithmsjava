package ru.sazonovkirill.algorithms.graphs;

import ru.sazonovkirill.algorithms.graphs.domain.Edge;

import java.util.List;

public class GraphUtils {
    public static String encodeListOfEdgesAsString(List<Edge> edges) {
        StringBuilder sb = new StringBuilder();
        for (Edge edge : edges) {
            sb.append("" + edge.getX() + "-" + edge.getY() + ":");
        }
        return sb.toString();
    }
}
