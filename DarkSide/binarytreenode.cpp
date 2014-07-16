#include "binarytreenode.h"

void BinaryTreeNode::preorderTraverse(BinaryTreeNodeVisiter visitor) {
    visitor(&_value);
    if (_left) _left->preorderTraverse(visitor);
    if (_right) _right->preorderTraverse(visitor);
}

void BinaryTreeNode::inorderTraverse(BinaryTreeNodeVisiter visitor) {
    if (_left) _left->preorderTraverse(visitor);
    visitor(&_value);
    if (_right) _right->preorderTraverse(visitor);
}

void BinaryTreeNode::postorderTraverse(BinaryTreeNodeVisiter visitor) {
    if (_left) _left->preorderTraverse(visitor);
    if (_right) _right->preorderTraverse(visitor);
    visitor(&_value);
}

int BinaryTreeNode::getHeight() {
    if (_left != NULL && _right != NULL) {
        return 1 + std::max(_left->getHeight(), _right->getHeight());
    } else if (_left != NULL) {
        return 1 + _left->getHeight();
    } else if (_right != NULL) {
        return 1 + _right->getHeight();
    } else {
        return 1;
    }
}
