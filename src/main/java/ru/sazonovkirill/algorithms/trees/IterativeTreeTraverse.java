package ru.sazonovkirill.algorithms.trees;

import java.util.Stack;

public class IterativeTreeTraverse<K extends Comparable<K>, V> {
    private final BinaryTreeNode<K, V> root;

    public IterativeTreeTraverse(BinaryTreeNode<K, V> root) {
        this.root = root;
    }

    public void preorderTraverse(IBinaryTreeNodeVisiter<K, V> visiter) {
        BinaryTreeNode<K, V> p = root;

        while (p != null) {
            visiter.visit(p);

            BinaryTreeNode<K, V> next = null;
            if (p.hasLeft()) next = p.getLeft();
            else {
                while (p.getParent() != null &&
                       p.getParent().isRightChild(p)) {
                    p = p.getParent();
                }
                next = p.getRightSibling();
            }

            p = next;
        }
    }

    public void inorderTraverse(IBinaryTreeNodeVisiter<K, V> visiter) {
        BinaryTreeNode<K, V> start = getMostLeft(root);

        BinaryTreeNode<K, V> p = start;
        while (p != null) {
            visiter.visit(p);

            BinaryTreeNode<K, V> next;
            if (p.hasRight()) {
                next = getMostLeft(p.getRight());
            }
            else {
                while (p.getParent() != null &&
                       p.getParent().isRightChild(p)) {
                    p = p.getParent();
                }
                next = p.getParent();
            }

            p = next;
        }

    }

    public void postorderTraverse(IBinaryTreeNodeVisiter<K, V> visiter) {
        BinaryTreeNode<K, V> start = getMostLeft(root);

        BinaryTreeNode<K, V> p = start;
        while (p != null) {
            visiter.visit(p);

            BinaryTreeNode<K, V> next = null;
            if (p.getParent() != null) {
                if (p.getParent().isLeftChild(p)) {
                    if (p.getParent().hasRight()) {
                        next = getMostLeft(p.getParent().getRight());
                    } else {
                        next = p.getParent();
                    }
                } else {
                    next = p.getParent();
                }
            }

            p = next;
        }
    }

    private static <K extends Comparable<K>, V> BinaryTreeNode<K, V> getMostLeft(BinaryTreeNode<K, V> node) {
        if (node == null) return null;

        BinaryTreeNode<K, V> result = node;
        while (result.getLeft() != null) {
            result = result.getLeft();
        }
        return result;
    }
}
