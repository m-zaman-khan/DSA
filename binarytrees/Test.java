package com.company.binarytrees;

public class Test {
    public static void main(String[] args) {
        var tree = new BinaryTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(6);
        tree.insert(4);
        tree.insert(14);
        tree.insert(8);
        tree.insert(30);
        tree.insert(24);
        tree.insert(26);

//        var tree1 = new BinaryTree();
//        tree1.insert(20);
//        tree1.insert(10);
//        tree1.insert(6);
//        tree1.insert(4);
//        tree1.insert(14);
//        tree1.insert(8);
//        tree1.insert(30);
//        tree1.insert(24);
//        tree1.insert(26);

        tree.traversalPreOrder();
        System.out.println();
        tree.traversalInOrder();
        System.out.println();
        tree.traversalPostOrder();
        System.out.println();
        tree.levelOrderTraversal();
        System.out.println();
        tree.nodesAtK(2);
        System.out.println();
        System.out.println("Height: " + tree.height());
        System.out.println("Min: " + tree.min());
        System.out.println("Max: " + tree.max());
//        System.out.println("Equals: " + tree.equals(tree1));
        System.out.println("Validity: " + tree.isValid());
        System.out.println("Size: " + tree.size());
        System.out.println("Count Leaves: " + tree.countLeaves());
        System.out.println("Contains 4: " + tree.contains(4));
        System.out.println("Contains 5: " + tree.contains(5));
        System.out.println("Are 10 and 30 Siblings: " + tree.areSiblings(10, 30));
        System.out.println("Ancestors of 26: " + tree.getAncestors(26));

    }
}
