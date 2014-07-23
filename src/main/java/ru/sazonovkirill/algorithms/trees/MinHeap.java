package ru.sazonovkirill.algorithms.trees;

import java.util.ArrayList;
import java.util.List;

public class MinHeap <T extends Comparable> {
    private final List<T> items = new ArrayList<>();

    public void insert(T item) {
        items.add(item);
        bubbleUp(last());
    }

    public T extract() {
        T item = items.get(0);
        T last = items.get(last());
        items.set(0, last);
        items.remove(last());
        bubbleDown(0);

        return item;
    }

    public int size() {
        return items.size();
    }

    public static <T extends Comparable> MinHeap<T> heapify(List<T> items) {
        MinHeap minHeap = new MinHeap();
        minHeap.items.addAll(items);
        for (int i = items.size() - 1; i >= 0; i--) {
            minHeap.bubbleDown(i);
        }
        return minHeap;
    }

    private int last() {
        return items.size() - 1;
    }

    private void swap(int x, int y) {
        T X = items.get(x);
        T Y = items.get(y);
        items.set(x, Y);
        items.set(y, X);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChildIndex(int i) {
        int idx = i * 2 + 1;
        if (idx < items.size()) return idx;
        else return -1;
    }

    private int rightChildIndex(int i) {
        int idx = i * 2 + 2;
        if (idx < items.size()) return idx;
        else return -1;
    }

    private void bubbleUp(int i) {
        int current = i;
        int parent = parent(i);

        while (items.get(current).compareTo(items.get(parent)) < 0 && current != parent) {
            swap(current, parent);
            current = parent;
            parent = parent(current);
        }
    }

    private void bubbleDown(int i) {
        int current = i;
        int left = leftChildIndex(current);
        int right = rightChildIndex(current);

        while (true) {
            if (left != -1 && right != -1) {
                if (items.get(left).compareTo(items.get(right)) < 0) { // left is min
                    if (items.get(current).compareTo(left) > 0) { // current is smallest
                        swap(current, left);
                        current = left;
                    } else {
                        break;
                    }
                } else {
                    if (items.get(current).compareTo(items.get(right)) > 0) { // current is smallest
                        swap(current, right);
                        current = right;
                    } else {
                        break;
                    }
                }
            } else if (left != -1) {
                if (items.get(current).compareTo(items.get(left)) > 0) {
                    swap(current, left);
                    current = left;
                } else {
                    break;
                }
            } else if (right != -1) {
                if (items.get(current).compareTo(items.get(right)) > 0) {
                    swap(current, right);
                    current = right;
                } else {
                    break;
                }
            } else {
                break;
            }

            left = leftChildIndex(current);
            right = rightChildIndex(current);
        }
    }
}
