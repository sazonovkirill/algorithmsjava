#ifndef BINARYTREENODE_H
#define BINARYTREENODE_H

#include <QString>

typedef int Key;
typedef QString Value;

typedef void (*BinaryTreeNodeVisiter)(Value *value);

class BinaryTreeNode
{
private:
    Key _key;
    Value _value;

    BinaryTreeNode *_left = NULL;
    BinaryTreeNode *_right = NULL;

public:
    BinaryTreeNode(Key key, Value value)
        : _key(key), _value(value)
    {}

    BinaryTreeNode(Key key, Value value, BinaryTreeNode *left, BinaryTreeNode *right)
        : _key(key), _value(value), _left(left), _right(right)
    {}

    Key getKey() { return _key; }

    void setValue(Value value) { _value = value; }
    Value getValue() { return _value; }

    BinaryTreeNode *getLeft() { return _left; }
    BinaryTreeNode *getRight() { return _right; }

    void setLeft(BinaryTreeNode *left) { _left = left; }
    void setRight(BinaryTreeNode *right) { _right = right; }

    void preorderTraverse(BinaryTreeNodeVisiter visitor);
    void inorderTraverse(BinaryTreeNodeVisiter visitor);
    void postorderTraverse(BinaryTreeNodeVisiter visitor);

    int getHeight();
};

#endif // BINARYTREENODE_H
