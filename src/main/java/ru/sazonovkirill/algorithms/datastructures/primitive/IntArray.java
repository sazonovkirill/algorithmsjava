package ru.sazonovkirill.algorithms.datastructures.primitive;

public class IntArray {
    public static int DEFAULT_SIZE = 10;

    private int[] arr;
    private int size = 0;

    public IntArray()
    {
        arr = new int[DEFAULT_SIZE];
    }

    public IntArray(int capacity)
    {
        arr = new int[capacity];
    }

    public void add(int value)
    {
        arr[size] = value;
        size++;

        if (size >= arr.length * 0.75)
        {
            int[] newArr = new int[arr.length * 2];
            for (int i = 0; i < size; i++)
            {
                newArr[i] = arr[i];
            }
            this.arr = newArr;
        }
    }

    public void insert(int value, int position)
    {
        if (position > size) throw new IndexOutOfBoundsException();

        for (int i = size - 2; i >= position; i--)
        {
            arr[i + 1] = arr[i];
        }

        arr[position] = value;
    }

    public int get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        return arr[index];
    }

    public boolean contains(int value)
    {
        for (int i = 0; i < size; i++)
        {
            if (arr[i] == value)
            {
                return true;
            }
        }
        return false;
    }

    public int indexOf(int value)
    {
        for (int i = 0; i < size; i++)
        {
            if (arr[i] == value)
            {
                return i;
            }
        }
        return -1;
    }
}
