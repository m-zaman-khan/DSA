package com.company.queues;

import java.util.LinkedList;

public class LinkedListQueue {
    private final LinkedList<Integer> list;

    public LinkedListQueue() {
        list = new LinkedList<>();
    }

    public void enqueue(int value) {
        list.addLast(value);
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException();
        return list.removeFirst();
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException();
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
