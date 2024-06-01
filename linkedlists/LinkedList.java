package com.company.linkedlists;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList {
    private static class Node {
        public int value;
        public Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(int value) {
            this(value, null);
        }
    }

    private Node first;
    private Node last;
    private int size = 0;

    // O(1)
    public void addFirst(int value) {
        if (isEmpty())
            first = last = new Node(value);
        else
            first = new Node(value, first);
        ++size;
    }

    // O(1)
    public void addLast(int value) {
        if (isEmpty())
            first = last = new Node(value);
        else {
            last.next = new Node(value);
            last = last.next;
        }
        ++size;
    }

    // O(1)
    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first == last) {
            first = last = null;
        } else {
            var temp = first;
            first = first.next;
            temp.next = null;
        }
        --size;
    }

    // O(n)
    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first == last) {
            first = last = null;
        } else {
            last = getPrevious(last);
            last.next = null;
        }
        --size;
    }

    private Node getPrevious(Node node) {
        for (var current = first; current != null; current = current.next)
            if (current.next == node)
                return current;
        return null;
    }

    // O(n)
    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    // O(n)
    public int indexOf(int value) {
        int index = 0;
        for (var current = first; current != null; current = current.next, index++)
            if (current.value == value)
                return index;
        return -1;
    }

    // O(n)
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var current = first; current != null; current = current.next)
            builder.append(current.value).append(", ");
        return builder.toString();
    }

    public boolean isEmpty() {
        return first == null;
    }

    // O(1)
    public int size() {
        return size;
    }

    // O(n)
    public int[] toArray() {
        var array = new int[size];
        int index = 0;
        for (var current = first; current != null; current = current.next)
            array[index++] = current.value;
        return array;
    }

    // O(n)
    public void reverse() {
        if (isEmpty()) return;

        Node previous = null;
        var current = first;
        while (current != null) {
            var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        last = first;
        first = previous;
    }

    // O(n)
    public int get(int index) {
        return getNode(index).value;
    }

    // O(n)
    public int getKthFromEnd(int kIndex) {
        if (kIndex <= 0)
            throw new IllegalArgumentException();
        if (kIndex == 1)
            return last.value;
        if (kIndex == 2)
            return getPrevious(last).value;

        var pointA = first;
        var pointB = first;

        for (int i = 0; i < kIndex; i++) {
            if (pointB == null)
                throw new IllegalStateException();
            pointB = pointB.next;
        }

        while (pointB != null) {
            pointA = pointA.next;
            pointB = pointB.next;
        }

        return pointA.value;
    }

    // O(n)
    public Node getNode(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException();

        var current = first;
        for (int i = 0; i < size; i++) {
            if (i == index)
                return current;
            current = current.next;
        }
        return null;
    }

    // O(n)
    public void printMiddle() {
        if (isEmpty())
            return;

        var pointA = first;
        var pointB = first;
        var isEvenPosition = true;

        while (pointB.next != null) {
            isEvenPosition = !isEvenPosition;
            if (isEvenPosition)
                pointA = pointA.next;
            pointB = pointB.next;
        }

        System.out.print("Middle: " + pointA.value);
        if (!isEvenPosition)
            System.out.print(", " + pointA.next.value);
        System.out.println();
    }

    //O(n)
    public boolean hasLoop() {
        if (isEmpty())
            throw new IllegalStateException();

        var slow = first;
        var fast = first;
        var isEvenPosition = true;

        while (slow != null && fast != null) {
            isEvenPosition = !isEvenPosition;
            if (isEvenPosition)
                slow = slow.next;
            fast = fast.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // O(n)
    public void forEach(Consumer<Node> consumer) {
        for (var current = first; current != null; current = current.next)
            consumer.accept(current);
    }
}
