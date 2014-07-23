#include <QCoreApplication>
#include <iostream>

#include "dijkstra_shortest_path_test.h"

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    test_graph_creation();
    return a.exec();
}
