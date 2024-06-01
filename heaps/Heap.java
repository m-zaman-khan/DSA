package com.company.heaps;

public class Heap {
    private int[] heap;
    private int size;

    public Heap(int initialCapacity) {
        heap = new int[initialCapacity];
    }

    public void insert(int value) {
        if (isFull())
            throw new IndexOutOfBoundsException("Heap is Full...");
        heap[size++] = value;
        if (size > 1)
            bubbleUp();
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public int remove() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Heap is Empty...");
        int value = heap[0];
        heap[0] = heap[--size];
        if (size > 1)
            bubbleDown();
        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleDown() {
        int index = 0;
        int bigger = getBigger(index);
        while (bigger < size && heap[index] < heap[bigger]) {
            swap(index, bigger);
            index = bigger;
            bigger = getBigger(index);
        }
    }

    private int getBigger(int index) {
        int left = getLeftChild(index);
        int right = getRightChild(index);
        return (right < size && heap[right] > heap[left]) ? right : left;
    }

    private static int getRightChild(int index) {
        return index * 2 + 2;
    }

    private static int getLeftChild(int index) {
        return index * 2 + 1;
    }

    private void bubbleUp() {
        int index = size - 1;
        int parent = getParent(index);
        while (heap[index] > heap[parent]) {
            swap(index, parent);
            index = parent;
            parent = getParent(index);
        }
    }

    private static int getParent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int indexA, int indexB) {
        var temp = heap[indexA];
        heap[indexA] = heap[indexB];
        heap[indexB] = temp;
    }

    private static void swap(int[] array, int indexA, int indexB) {
        var temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public int max() {
        if (size == 0)
            throw new IllegalStateException();
        return heap[0];
    }

    public static void heapify(int[] array) {
        if (array.length <= 1)
            return;
        int lastParentIndex = array.length / 2 - 1;
        for (int i = lastParentIndex; i >= 0; --i)
            heapify(array, i);
    }

    private static void heapify(int[] array, int index) {
        int largerIndex = index;

        int leftIndex = getLeftChild(index);
        if (leftIndex < array.length && array[largerIndex] < array[leftIndex])
            largerIndex = leftIndex;

        int rightIndex = getRightChild(index);
        if (rightIndex < array.length && array[largerIndex] < array[rightIndex])
            largerIndex = rightIndex;

        if (largerIndex == index)
            return;

        swap(array, index, largerIndex);
        heapify(array, largerIndex);
    }

    public static boolean isMaxHeap(int[] array) {
        var isMaxHeap = true;
        if (array.length > 1) {
            int lastParentIndex = array.length / 2 - 1;
            for (int i = lastParentIndex; i >= 0; --i)
                isMaxHeap &= isMaxHeap(array, i);
        }
        return isMaxHeap;
    }

    private static boolean isMaxHeap(int[] array, int index) {
        int leftIndex = getLeftChild(index);
        int rightIndex = getRightChild(index);
        return (leftIndex >= array.length || array[index] >= array[leftIndex]) &&
                (rightIndex >= array.length || array[index] >= array[rightIndex]);
    }

    public static int largestKth(int[] array, int k) {
        var heap = new Heap(array.length);
        for (int i : array)
            heap.insert(i);
        for (int i = 0; i < k - 1; ++i)
            heap.remove();
        return heap.max();
    }

}