package com.company.queues;

import java.util.Arrays;

public class ArrayQueue {
    protected final int[] queue;
    protected int front;
    protected int rear;
    protected int count;

    public ArrayQueue(int capacity) {
        queue = new int[capacity];
        rear = -1;
        front = count = 0;
    }

    public void enqueue(int value) {
        if (isFull()) throw new StackOverflowError();
        ++count;
        rear = getIndex(rear + 1);
        queue[rear] = value;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException();
        int value = queue[front];
        queue[front] = 0;
        front = getIndex(front + 1);
        --count;
        return value;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException();
        return queue[front];
    }

    public boolean isFull() {
        return count == queue.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    protected int getIndex(int index) {
        if (index < 0) return queue.length + (index % queue.length);
        return index % queue.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }
}
