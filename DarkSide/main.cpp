#include <QCoreApplication>

#include <iostream>

#include "binarytreenode.h"

int test_bynaryTreeNode() {
    QString name = "name";
    BinaryTreeNode node(1, name);
    name = "name2";
    std::cout << "value: " << node.getValue().toStdString() << std::endl;
    return 0;
}

void printValue(Value *value) {
    std::cout << value->toStdString() << std::endl;
}

int test_traversals() {\
    BinaryTreeNode *root = new BinaryTreeNode(1, "1",
        new BinaryTreeNode(2, "2",
              new BinaryTreeNode(3, "3"), NULL),
        new BinaryTreeNode(4, "4",
              new BinaryTreeNode(5, "5"),
              new BinaryTreeNode(6, "6")));

    std::cout << "Height: " << QString::number(root->getHeight()).toStdString() << std::endl;

    std::cout << "Test preorder" << std::endl;
    root->preorderTraverse(printValue);
    return 0;
}

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    test_traversals();
    return a.exec();
}
