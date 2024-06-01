package com.company.heaps;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        var heap = new Heap(16);
        heap.insert(5);
        heap.insert(15);
        heap.insert(4);
        heap.insert(8);
        heap.insert(10);
        heap.insert(6);
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());

        var array = new int[]{5, 3, 8, 4, 1, 2, 15, 40, 25, 7};
        System.out.println(Heap.isMaxHeap(array));
        Heap.heapify(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Heap.isMaxHeap(array));
        System.out.println(Heap.largestKth(array, 3));

        var minHeap = new MinPriorityQueue(16);
        minHeap.enqueue("Talha", 4);
        minHeap.enqueue("Usama", 2);
        minHeap.enqueue("Father", 5);
        minHeap.enqueue("Fahad", 3);
        minHeap.enqueue("Taimoor", 1);
        System.out.println(minHeap.dequeue());
        System.out.println(minHeap.dequeue());
        System.out.println(minHeap.dequeue());
        System.out.println(minHeap.dequeue());
        System.out.println(minHeap.dequeue());
    }
}
