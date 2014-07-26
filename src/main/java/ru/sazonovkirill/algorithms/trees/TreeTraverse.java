package ru.sazonovkirill.algorithms.trees;

public class TreeTraverse<K extends Comparable<K>, V> {
    private final BinaryTreeNode<K, V> root;

    public TreeTraverse(BinaryTreeNode<K, V> root) {
        this.root = root;
    }

    public void preorderTraverse(IBinaryTreeNodeVisiter<K, V> visiter) {
        preorder(root, visiter);
    }

    public void inorderTraverse(IBinaryTreeNodeVisiter<K, V> visiter) {
        inorder(root, visiter);
    }

    public void postorderTraverse(IBinaryTreeNodeVisiter<K, V> visiter) {
        postorder(root, visiter);
    }

    private void preorder(BinaryTreeNode<K, V> node, IBinaryTreeNodeVisiter<K, V> visiter) {
        if (node == null) return;

        visiter.visit(node);
        preorder(node.getLeft(), visiter);
        preorder(node.getRight(), visiter);
    }

    private void inorder(BinaryTreeNode<K, V> node, IBinaryTreeNodeVisiter<K, V> visiter) {
        if (node == null) return;

        inorder(node.getLeft(), visiter);
        visiter.visit(node);
        inorder(node.getRight(), visiter);
    }

    private void postorder(BinaryTreeNode<K, V> node, IBinaryTreeNodeVisiter<K, V> visiter) {
        if (node == null) return;

        postorder(node.getLeft(), visiter);
        postorder(node.getRight(), visiter);
        visiter.visit(node);
    }
}
