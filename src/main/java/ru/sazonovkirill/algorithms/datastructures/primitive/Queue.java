package ru.sazonovkirill.algorithms.datastructures.primitive;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Queue<T> {
    private final List<T> list = new LinkedList<>();

    public void add(T t) {
        list.add(t);
    }

    public void addAll(Collection<? extends T> all) {
        list.addAll(all);
    }

    public T first() {
        return list.get(0);
    }

    public T extractFirst() {
        T t = list.get(0);
        list.remove(0);
        return t;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
