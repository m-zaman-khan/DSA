package com.company.stacks;

import com.company.linkedlists.LinkedList;

public class Stack {
    protected final LinkedList stack;

    public Stack() {
        stack = new LinkedList();
    }

    // O(1)
    public void push(int value) {
        stack.addLast(value);
    }

    // O(1)
    public int pop() {
        int value = peek();
        stack.removeLast();
        return value;
    }

    // O(1)
    public int peek() {
        return stack.getKthFromEnd(1);
    }

    // O(1)
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
