package com.company.linkedlists;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        var list = new LinkedList();

        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);


        System.out.println("Has Loop: " + list.hasLoop());

        System.out.println(list);
        list.printMiddle();
        System.out.println("Kth 4nd: " + list.getKthFromEnd(4));

        list.reverse();

        System.out.println(list);
        list.printMiddle();
        System.out.println("Kth 3nd: " + list.getKthFromEnd(3));

        System.out.println("Size of list: " + list.size());
        System.out.println("Contains 50: " + list.contains(50));
        System.out.println("Index of 40: " + list.indexOf(40));

        list.removeFirst();
        list.removeLast();
        list.removeFirst();
        System.out.println(list);

        System.out.println("Size of list: " + list.size());
        System.out.println(Arrays.toString(list.toArray()));

    }
}
