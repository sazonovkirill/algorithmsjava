#ifndef DIRECTED_WEIGHTED_GRAPH_H
#define DIRECTED_WEIGHTED_GRAPH_H

#include <list>
#include <vector>

using namespace std;

class Edge {
public:
    Edge(const int from, const int to)
        : _from(from), _to(to), _weight(1)
    {}

    Edge(const int from, const int to, const int weight)
        : _from(from), _to(to), _weight(weight)
    {}

    bool same_as(const Edge &edge) const {
        return ((_from == edge._from) && (_to == edge._to));
    }

    int from() const { return _from; }
    int to() const { return _to; }
    int weight() const { return _weight; }

    int other(int v) {
        if (_from == v) return _to;
        else if (_to == v) return _from;
        else return -1;
    }

private:
    int _from = -1;
    int _to = -1;
    int _weight = 1;
};

class DirectedWeightedGraph {
public:
    DirectedWeightedGraph(const int verticesCount)
        : _vertices(verticesCount), _edges(0)
    {}

    void connect(int from, int to, int weight);
    std::vector<Edge *> get_outgoing_edges(int vertex) const;
    std::vector<int> get_adjacent_vertices(int vertex) const;
    Edge *is_connected(int from, int to) const;

    void print_graph();

    int vertices_count() const { return (int)_edges.size(); }
private:
    std::vector<std::vector<Edge *> > _vertices;
    std::vector<Edge *> _edges;

};

#endif // DIRECTED_WEIGHTED_GRAPH_H
