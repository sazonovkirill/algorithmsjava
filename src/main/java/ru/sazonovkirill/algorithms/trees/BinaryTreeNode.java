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

    public BinaryTreeNode(K key, V value, BinaryTreeNode<K, V> left, BinaryTreeNode<K, V> right) {
        this.parent = null;
        this.key = key;
        this.value = value;

        setLeft(left);
        setRight(right);
    }

    public BinaryTreeNode(K key, BinaryTreeNode<K, V> left, BinaryTreeNode<K, V> right) {
        this.parent = null;
        this.key = key;

        setLeft(left);
        setRight(right);
    }

    public BinaryTreeNode(K key) {
        this.parent = null;
        this.key = key;
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

    private void setLeft(BinaryTreeNode<K, V> left) {
        this.left = left;
        if (left != null) left.parent = this;
    }

    private void setRight(BinaryTreeNode<K, V> right) {
        this.right = right;
        if (right != null) right.parent = this;
    }

    public boolean isLeftChild(BinaryTreeNode<K, V> node) {
        return node.equals(left);
    }

    public boolean isRightChild(BinaryTreeNode<K, V> node) {
        return node.equals(right);
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

    public boolean hasSibling() {
        return getSibling() != null;
    }

    public BinaryTreeNode<K, V> getSibling() {
        if (getParent() == null) return null;
        else {
            if (getParent().isRightChild(this)) return getParent().getLeft();
            else return getParent().getRight();
        }
    }

    public boolean hasRightSibling() {
        return getRightSibling() != null;
    }

    public BinaryTreeNode<K, V> getRightSibling() {
        if (getParent() == null) return null;
        else {
            if (getParent().isLeftChild(this)) return getParent().getRight();
            else return null;
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
                    x.setRight(new BinaryTreeNode<>(x, key, value));
                    return;
                }
            }
            else {
                if (x.getLeft() != null) {
                    x = x.getLeft();
                } else {
                    x.setLeft(new BinaryTreeNode<>(x, key, value));
                    return;
                }
            }
        }
    }

    private void transplant(BinaryTreeNode<K, V> target) {
        if (hasLeft() || hasRight()) {
            throw new IllegalStateException("Cannot move node with key " + getKey() + ", " +
                    "because it has leftChild: " + hasLeft() + " or rightChild: " + hasRight());
        }

        if (target.hasLeft()) setLeft(target.getLeft());
        if (target.hasRight()) setRight(target.getRight());

        moveTo(target);
    }

    private void moveTo(BinaryTreeNode<K, V> target) {
        BinaryTreeNode<K, V> parent = target.parent;
        if (parent != null) {
            if (parent.isLeftChild(target)) {
                parent.setLeft(this);
            } else {
                parent.setRight(this);
            }
        }
    }

    public void delete(K key) {
        BinaryTreeNode<K, V> node = search(key);
        BinaryTreeNode<K, V> parent = node.getParent();

        if (node == null) return;

        if (node.hasLeft() && node.hasRight()) {
            BinaryTreeNode<K, V> successor = node.getNext();
            if (node.getRight().equals(successor)) {
                successor.moveTo(node);
            } else {
                successor.getParent().setLeft(successor.getRight());
                successor.setRight(null);
                successor.transplant(node);
            }
        } else if (node.hasLeft()) {
            node.getLeft().moveTo(node);
        } else if (node.hasRight()) {
            node.getRight().moveTo(node);
        } else {
            if (parent.isLeftChild(this)) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryTreeNode that = (BinaryTreeNode) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
