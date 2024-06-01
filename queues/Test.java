package com.company.queues;

import java.util.*;

public class Test {
    public static void main(String[] args) {
//        Queue<Integer> queue = new ArrayDeque<>();
//        queue.add(10);
//        queue.add(20);
//        queue.add(30);
//        System.out.println(queue);
//        reverse(queue);
//        System.out.println(queue);
//        int capacity = 8;
//        var queue = new PriorityQueue(capacity);
//        queue.enqueue(5);
//        queue.enqueue(3);
//        queue.enqueue(6);
//        queue.dequeue();
//        queue.enqueue(1);
//        queue.enqueue(1);
//        queue.enqueue(4);
//        queue.enqueue(4);
//        queue.enqueue(4);
//        queue.enqueue(10);
//        queue.dequeue();
//        queue.enqueue(1);
//
//        System.out.println(queue);

//        var queue = new ArrayDeque<Integer>(){{add(10);add(20);add(30);add(40);add(50);}};
//        System.out.println(queue);
//        QueueReverser.reverse(queue, 3);
//        System.out.println(queue);

        var stack = new StackWithTwoQueues();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.pop());
        System.out.println();
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }

    public static void reverse(Queue<Integer> queue) {
        var stack = new Stack<Integer>();
        while (!queue.isEmpty())
            stack.push(queue.remove());
        while (!stack.isEmpty())
            queue.add(stack.pop());
    }
}
