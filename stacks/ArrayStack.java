package com.company.stacks;

import java.util.Arrays;

public class ArrayStack {
    private int[] stack;
    private int count;

    public ArrayStack() {
        stack = new int[1];
        count = 0;
    }

    // O(1)
    public void push(int value) {
        if (count == stack.length)
            extendStack();
        stack[count++] = value;
    }

    // O(1)
    public int pop() {
        if (isEmpty())
            throw new IllegalStateException();
        return stack[--count];
    }

    // O(1)
    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return stack[count - 1];
    }

    // O(1)
    public boolean isEmpty() {
        return count == 0;
    }

    // O(n)
    private void extendStack() {
        var extended = new int[count * 2];
        System.arraycopy(stack, 0, extended, 0, count);
        stack = extended;
    }

    @Override
    public String toString() {
        var content = Arrays.copyOfRange(stack, 0, count);
        return Arrays.toString(content);
    }
}
