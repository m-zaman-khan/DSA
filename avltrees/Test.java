package com.company.avltrees;

public class Test {
    public static void main(String[] args) {
        var tree = new AVLTree();
        tree.insert(12);
        tree.insert(3);
        tree.insert(9);
        tree.insert(5);
        tree.insert(6);
        tree.insert(2);
        tree.insert(4);
        System.out.println(tree.isBalanced());
        System.out.println(tree.isPerfect());
    }
}