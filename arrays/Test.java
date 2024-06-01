package com.company.arrays;

public class Test {

    public static void main(String[] args) {
	    var items = new Array(3);
        items.insert(10);
        items.insert(20);
        items.insert(30);
        items.insert(40);
        items.insertAt(2, 50);
        items.removeAt(1);
        items.print();
        items.reverse();
        items.print();
    }
}
