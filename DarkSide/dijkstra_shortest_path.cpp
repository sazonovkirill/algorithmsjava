#include "dijkstra_shortest_path.h"

#include <climits>
#include <algorithm>
#include <queue>
#include <functional>

using namespace std;

void DijkstraShortestPath::process() {
    int vertices_count = _distances.size();
    std::vector<int> vertices;
    for (int i = 0; i < vertices_count; i++) {
        _distances[i] = INT_MAX;
        _parents[i] = -1;
        vertices.push_back(i);
    }

    _distances[_source_vertex] = 0;

    auto compare = [&] (int x, int y) {
        return _distances[x] >= _distances[y];
    };

    std::make_heap(vertices.begin(), vertices.end(), compare);

    while (vertices.size() > 0) {
        int current_vertex = vertices.at(0);
        std::pop_heap(vertices.begin(), vertices.end());
        int distance_to_current_vertex = _distances[current_vertex];

        std::vector<Edge *>outgoing_edges = _graph.get_outgoing_edges(current_vertex);
        for (auto it = outgoing_edges.begin(); it != outgoing_edges.end(); it++) {
            Edge *edge = *it;
            int another_vertex = edge->other(current_vertex);
            int edge_weight = edge->weight();

            if (_distances[another_vertex] > distance_to_current_vertex + edge_weight) {
                _distances[another_vertex] = distance_to_current_vertex + edge_weight;
                _parents[another_vertex] = current_vertex;

                int position_to_delete = -1;
                for (int i = 0; i < vertices.size(); i++) {
                    if (vertices.at(i) == another_vertex) {
                        position_to_delete = i;
                    }
                }
                vertices.erase(vertices.begin() + position_to_delete);

                vertices.push_back(another_vertex);
                std::push_heap(vertices.begin(), vertices.end());
            }
        }
    }
}

