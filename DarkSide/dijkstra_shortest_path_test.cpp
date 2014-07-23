#include <stdio.h>
#include <iostream>

#include "dijkstra_shortest_path_test.h"
#include "dijkstra_shortest_path.h"
#include "directed_weighted_graph.h"

using namespace std;

int test_graph_creation() {
    DirectedWeightedGraph graph(4);
    graph.connect(0, 1, 2);
    graph.connect(1, 2, 2);
    graph.connect(2, 3, 2);
    graph.connect(0, 3, 5);
    graph.print_graph();

    DijkstraShortestPath dsp(graph, 0);
    int l = dsp.get_shortest_path_length(3);
    std::cout << "From 0 to 3: " << l;
    return 0;
}
