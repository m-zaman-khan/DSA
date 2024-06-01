package com.company.queues;

public class PriorityQueue extends ArrayQueue{

    public PriorityQueue(int capacity) {
        super(capacity);
    }

    @Override
    public void enqueue(int value) {
        if (isFull()) throw new StackOverflowError();
        if (isEmpty()) {
            super.enqueue(value);
            return;
        }
        queue[shiftingValue(value)] = value;
        rear = getIndex(rear + 1);
        count++;
    }

    private int shiftingValue(int value) {
        int index = rear;
        for (int track = 0; track < count; track++, index = getIndex(index - 1)) {
            if (queue[index] > value)
                queue[getIndex(index + 1)] = queue[index];
            else
                break;
        }
        return getIndex(index + 1);
    }
}