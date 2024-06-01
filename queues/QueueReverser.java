package com.company.queues;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class QueueReverser {
    public static void reverse(Queue<Integer> queue, int k) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> newQueue = new ArrayDeque<>();
        for (int i = 0; i < k; i++)
            stack.push(queue.remove());
        while (!stack.isEmpty())
            queue.add(stack.pop());
        for (int i = 0; i < queue.size() - k; i++)
            queue.add(queue.remove());
    }
}
