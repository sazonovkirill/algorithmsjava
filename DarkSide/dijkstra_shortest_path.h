#ifndef DIJKSTRA_SHORTEST_PATH_H
#define DIJKSTRA_SHORTEST_PATH_H

#include <list>
#include <map>
#include "directed_weighted_graph.h"

class DijkstraShortestPath {
public:
    DijkstraShortestPath(const DirectedWeightedGraph &graph, int source_vertex)
        : _graph(graph), _source_vertex(source_vertex), _distances(graph.vertices_count()), _parents(graph.vertices_count())
    {}

    int get_shortest_path_length(int dest_vertex);
    std::list<Edge *> get_shortest_path(int dest_vertex);

    int source_vertex() const { return _source_vertex; }
    const DirectedWeightedGraph &graph() const { return _graph; }

private:
    const int _source_vertex;
    const DirectedWeightedGraph &_graph;
    std::vector<int> _distances;
    std::vector<int> _parents;
};

#endif // DIJKSTRA_SHORTEST_PATH_H
