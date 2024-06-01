package com.company.queues;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class StackWithTwoQueues {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    private int count;
    private int top;

    public StackWithTwoQueues() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
        count = top = 0;
    }

    public void push(int value) {
        queue1.add(value);
        count++;
        top  = value;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException();
        while (queue1.size() > 1) {
            top = queue1.remove();
            queue2.add(top);
        }
        swapQueues();
        count--;
        return queue2.remove();
    }

    private void swapQueues() {
        var temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException();
        return top;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public int size() {
        return count;
    }
}
