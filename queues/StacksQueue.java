package com.company.queues;

import java.util.Stack;

public class StacksQueue {
    private final Stack<Integer> backward;
    private final Stack<Integer> forward;

    public StacksQueue() {
        backward = new Stack<>();
        forward = new Stack<>();
    }

    public void enqueue(int value) {
        forward.push(value);
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException();
        moveBackward(backward, forward);
        return backward.pop();
    }

    private void moveBackward(Stack<Integer> backward, Stack<Integer> forward) {
        if (backward.isEmpty())
            while (!forward.isEmpty())
                backward.push(forward.pop());
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException();
        moveBackward(backward, forward);
        return backward.peek();
    }

    public boolean isEmpty() {
        return forward.isEmpty() && backward.isEmpty();
    }

    @Override
    public String toString() {
        return forward.toString() + backward.toString();
    }
}
