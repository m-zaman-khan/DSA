package com.company.stacks;

import java.util.LinkedList;

public class MinStack extends Stack {
    private final LinkedList<Integer> minimum;

    public MinStack() {
        minimum = new LinkedList<>();
    }

    @Override
    public void push(int value) {
        if (isEmpty() || value < minimum.getFirst())
            minimum.addFirst(value);
        super.push(value);
    }

    @Override
    public int pop() {
        var top = super.pop();
        minimum.remove(top);
        return top;
    }

    public int min() {
        return minimum.getFirst();
    }
}