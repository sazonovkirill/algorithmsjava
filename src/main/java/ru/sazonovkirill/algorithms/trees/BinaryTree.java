package ru.sazonovkirill.algorithms.trees;

public class BinaryTree<K extends Comparable<K>, V> {
    private final BinaryTreeNode<K, V> root;

    public BinaryTree(BinaryTreeNode<K, V> root) {
        if (root == null) throw new IllegalArgumentException();

        this.root = root;
    }




}
