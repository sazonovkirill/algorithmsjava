#-------------------------------------------------
#
# Project created by QtCreator 2014-07-15T19:03:45
#
#-------------------------------------------------

QT       += core

QT       -= gui

TARGET = DarkSide
CONFIG   += console
CONFIG   -= app_bundle

TEMPLATE = app


SOURCES += main.cpp \
    binarytreenode.cpp \
    directed_weighted_graph.cpp \
    dijkstra_shortest_path_test.cpp \
    dijkstra_shortest_path.cpp

HEADERS += \
    binarytreenode.h \
    directed_weighted_graph.h \
    nerror.h \
    dijkstra_shortest_path.h \
    dijkstra_shortest_path_test.h
