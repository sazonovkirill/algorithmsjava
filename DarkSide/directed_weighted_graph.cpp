#include "directed_weighted_graph.h"

void DirectedWeightedGraph::connect(int from, int to, int weight) {
    if (from == to || from < 0 || to < 0 || from >= this->vertices_count() || to >= this->vertices_count()) {

        return;
    }

    Edge *new_edge = new Edge(from, to, weight);

    // No duplicated edges
    for (auto edge = _edges.begin(); edge != _edges.end(); edge++) {
        if ((*edge)->same_as(*new_edge)) {
            return;
        }
    }

    _edges.push_back(new_edge);
    _vertices.at(from).push_back(new_edge);
}

void DirectedWeightedGraph::print_graph() {
    printf("Vertices: %d\n", vertices_count());
    printf("All edges: \n");
    for (auto i = _edges.begin(); i != _edges.end(); i++) {
        printf("\t%d -|%d|-> %d\n", (*i)->from(), (*i)->weight(), (*i)->to());
    }
    printf("Outgoing edges: \n");
    for (int i = 0; i < vertices_count(); i++) {
        std::vector<Edge *> this_vertex_edges = _vertices.at(i);
        if (this_vertex_edges.size() > 0) {
            printf("\tVertex %d\n", i);
            for (auto j = this_vertex_edges.begin(); j != this_vertex_edges.end(); j++) {
                printf("\t%d -|%d|-> %d\n", (*j)->from(), (*j)->weight(), (*j)->to());
            }
        }
    }
}

std::vector<Edge *> DirectedWeightedGraph::get_outgoing_edges(int vertex) const {
    return _vertices[vertex];
}

std::vector<int> DirectedWeightedGraph::get_adjacent_vertices(int vertex) const {
    std::vector<int> result;
    for (auto i = _vertices[vertex].begin(); i != _vertices[vertex].end(); i++) {
        result.push_back((*i)->other(vertex));
    }
    return result;
}

Edge *DirectedWeightedGraph::is_connected(int from, int to) const {
    for (auto i = _edges.begin(); i != _edges.end(); i++) {
        if ((*i)->from() == from && (*i)->to() == to) {
            return *i;
        }
    }
    return NULL;
}
