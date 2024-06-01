package com.company.heaps;

public class MinPriorityQueue {
    private final MinHeap heap;

    public MinPriorityQueue(int initialCapacity) {
        heap = new MinHeap(initialCapacity);
    }

    public void enqueue(String value, int priority) {
        heap.insert(new Node(priority, value));
    }

    public String dequeue() {
        return heap.remove().getValue();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
