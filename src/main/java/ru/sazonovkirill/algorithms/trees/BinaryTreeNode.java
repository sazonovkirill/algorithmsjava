package ru.sazonovkirill.algorithms.trees;

public class BinaryTreeNode<K extends Comparable<K>, V> {
    private K key;
    private V value;

    private BinaryTreeNode<K, V> left;
    private BinaryTreeNode<K, V> right;
    private BinaryTreeNode<K, V> parent;

    public static <K extends Comparable<K>, V> BinaryTreeNode<K, V> createRoot(K key, V value) {
        return new BinaryTreeNode<K, V>(null, key, value);
    }

    private BinaryTreeNode(BinaryTreeNode<K, V> parent, K key, V value) {
        this.parent = parent;
        this.key = key;
        this.value = value;
    }

    private BinaryTreeNode(BinaryTreeNode<K, V> parent, K key, V value, BinaryTreeNode<K, V> left, BinaryTreeNode<K, V> right) {
        this.parent = parent;
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BinaryTreeNode<K, V> getLeft() {
        return left;
    }

    public BinaryTreeNode<K, V> getRight() {
        return right;
    }

    public BinaryTreeNode<K, V> getParent() {
        return parent;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    @Override
    public String toString() {
        return "{" + key + "," + value + "}";
    }

    public BinaryTreeNode<K, V> search(K key) {
        return search(this, key);
    }

    private BinaryTreeNode<K, V> search(BinaryTreeNode<K, V> node, K key) {
        if (node == null) return null;

        int cmp = node.getKey().compareTo(key);
        if (cmp == 0) return node;
        else if (cmp < 0) return search(node.getRight(), key);
        else return search(node.getLeft(), key);
    }

    private BinaryTreeNode<K, V> searchIterative(K key) {
        BinaryTreeNode<K, V> x = this;

        while (true) {
            if (x == null) return null;

            int cmp = x.getKey().compareTo(key);
            if (cmp == 0) return x;
            else if (cmp < 0) x = x.getRight();
            else x = x.getLeft();
        }
    }

    public BinaryTreeNode<K, V> getMinimum() {
        BinaryTreeNode<K, V> result = this;
        while (result.getLeft() != null) {
            result = result.getLeft();
        }
        return result;
    }

    public BinaryTreeNode<K, V> getMaximum() {
        BinaryTreeNode<K, V> result = this;
        while (result.getRight() != null) {
            result = result.getRight();
        }
        return result;
    }

    public BinaryTreeNode<K, V> getNext() {
        if (hasRight()) return getRight().getMinimum();
        else {
            BinaryTreeNode<K, V> x = this;
            BinaryTreeNode<K, V> y = x.getParent();
            while(y != null && x == y.getRight()) {
                x = y;
                y = y.getParent();
            }
            return y;
        }
    }

    public BinaryTreeNode<K, V> getPrevious() {
        if (hasLeft()) return getLeft().getMaximum();
        else {
            BinaryTreeNode<K, V> x = this;
            BinaryTreeNode<K, V> y = x.getParent();
            while(y != null && x == y.getLeft()) {
                x = y;
                y = y.getParent();
            }
            return y;
        }
    }

    public void insert(K key, V value) {
        insertIterative(key, value);
    }

    private void insertIterative(K key, V value) {
        BinaryTreeNode<K, V> x = this;

        while (true) {
            int cmp = x.getKey().compareTo(key);
            if (cmp == 0) {
                x.setValue(value);
                return;
            }
            else if (cmp < 0) {
                if (x.getRight() != null) {
                    x = x.getRight();
                } else {
                    x.right = new BinaryTreeNode<>(x, key, value);
                    return;
                }
            }
            else {
                if (x.getLeft() != null) {
                    x = x.getLeft();
                } else {
                    x.left =  new BinaryTreeNode<>(x, key, value);
                    return;
                }
            }
        }
    }

    public void delete(K key) {
        BinaryTreeNode<K, V> node = search(key);
        if (node == null) return;

        if (node.hasLeft() && node.hasRight()) {
            BinaryTreeNode<K, V> parent = node.getParent();
            BinaryTreeNode<K, V> successor = node.getNext();
            if (successor.getKey().equals(node.getRight().getKey())) {
                if (parent.getLeft().getKey().equals(key)) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            } else {
                node.getRight().left = successor.getRight();
                successor.right = node.getRight();
                if (parent.getLeft().getKey().equals(key)) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            }
        } else if (node.hasLeft()) {
            BinaryTreeNode<K, V> parent = node.getParent();
            if (parent.getLeft().getKey().equals(key)) {
                parent.left = node.getLeft();
            } else {
                parent.right = node.getLeft();
            }
        } else if (node.hasRight()) {
            BinaryTreeNode<K, V> parent = node.getParent();
            if (parent.getLeft().getKey().equals(key)) {
                parent.left = node.getRight();
            } else {
                parent.right = node.getRight();
            }
        } else {
            BinaryTreeNode<K, V> parent = node.getParent();
            if (parent.getLeft().getKey().equals(key)) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
    }
}
