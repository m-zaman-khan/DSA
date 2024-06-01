package com.company.stacks;

import java.util.Arrays;

public class TwoStackArray {
    private int[] stack;
    private int count1;
    private int count2;
    private int secondStart;

    public TwoStackArray() {
        stack = new int[2];
        count1 = count2 = 0;
        secondStart = 1;
    }

    public void push1(int value) {
        if (isFull1())
            extendStack(1);
        stack[count1++] = value;
    }

    public void push2(int value) {
        if (isFull2())
            extendStack(2);
        stack[secondStart + count2++] = value;
    }

    public int pop1() {
        if (isEmpty1())
            throw new IllegalStateException();
        return stack[--count1];
    }

    public int pop2() {
        if (isEmpty2())
            throw new IllegalStateException();
        return stack[secondStart + (--count2)];
    }

    public boolean isEmpty1() {
        return count1 == 0;
    }

    public boolean isEmpty2() {
        return count2 == 0;
    }

    public boolean isFull1() {
        return count1 == secondStart;
    }

    public boolean isFull2() {
        return count2 == stack.length - secondStart;
    }

    private void extendStack(int stackNo) {
        int size = stack.length;
        if (stackNo == 1) {
            size += count1;
            secondStart = count1 * 2;
        } else
            size += count2;

        var extended = new int[size];
        for (int i = 0; i < stack.length; i++)
            extended[(stackNo == 1 && i >= count1) ? i + count1 : i] = stack[i];
        stack = extended;
    }

    @Override
    public String toString() {
        var content1 = Arrays.copyOfRange(stack, 0, count1);
        var content2 = Arrays.copyOfRange(stack, secondStart, secondStart + count2);
        return Arrays.toString(content1) + " - " + Arrays.toString(content2);
    }
}
