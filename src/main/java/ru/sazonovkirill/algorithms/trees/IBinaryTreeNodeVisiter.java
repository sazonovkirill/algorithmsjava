package ru.sazonovkirill.algorithms.trees;

public interface IBinaryTreeNodeVisiter<K extends Comparable<K>, V> {
    public void visit(BinaryTreeNode<K, V> node);
}
