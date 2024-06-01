package com.company.heaps;

public class MinHeap {
    private final Node[] heap;
    private int size;

    public MinHeap(int initialCapacity) {
        heap = new Node[initialCapacity];
    }

    public void insert(Node node) {
        if (isFull())
            throw new IndexOutOfBoundsException("Heap is Full...");
        heap[size++] = node;
        if (size > 1)
            bubbleUp();
    }

    public Node remove() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Heap is Empty...");
        var node = heap[0];
        heap[0] = heap[--size];
        if (size > 1)
            bubbleDown();
        return node;
    }

    public void bubbleUp() {
        int index = size - 1;
        int parent = (index - 1) / 2;
        while (heap[index].getKey() < heap[parent].getKey()) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    public void bubbleDown() {
        int index = 0;
        int smaller = getSmaller(index);
        while (smaller < size && heap[index].getKey() > heap[smaller].getKey()) {
            swap(index, smaller);
            index = smaller;
            smaller = getSmaller(index);
        }
    }

    private int getSmaller(int index) {
        int left = getLeftChild(index);
        int right = getRightChild(index);
        return (right < size && heap[right].getKey() < heap[left].getKey()) ? right : left;
    }

    private static int getRightChild(int index) {
        return index * 2 + 2;
    }

    private static int getLeftChild(int index) {
        return index * 2 + 1;
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int indexA, int indexB) {
        var temp = heap[indexA];
        heap[indexA] = heap[indexB];
        heap[indexB] = temp;
    }
}
