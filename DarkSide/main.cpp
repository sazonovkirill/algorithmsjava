#include <QCoreApplication>
#include <iostream>

#include "directed_weighted_graph.h"

//int test_graph_creation() {
//    DirectedWeightedGraph graph(4);
//    graph.connect1(0, 1, 2);
//    graph.connect1(1, 2, 2);
//    graph.connect1(2, 3, 2);
//    graph.connect1(0, 3, 5);
//    graph.print_graph1();
//    return 0;
//}

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    DirectedWeightedGraph *graph = new DirectedWeightedGraph(2);
    graph->print_graph();
    graph->connect(1, 1, 1);

    return a.exec();
}
