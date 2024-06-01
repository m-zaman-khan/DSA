package com.company.avltrees;

public class AVLTree {
    private static class AVLNode {
        private final int value;
        private int height;
        private AVLNode leftChild;
        private AVLNode rightChild;

        private AVLNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
    }

    private AVLNode root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode node, int value) {
        if (node == null)
            return new AVLNode(value);
        if (value < node.value)
            node.leftChild = insert(node.leftChild, value);
        else if (value > node.value) {
            node.rightChild = insert(node.rightChild, value);
        }
        setHeight(node);
        return balance(node);
    }

    private void setHeight(AVLNode node) {
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }

    private AVLNode balance(AVLNode node) {
        if (isLeftHeavy(node)) {
            if (balanceFactor(node.leftChild) < 0)
                node.leftChild = leftRotation(node.leftChild);
            return rightRotation(node);
        } else if (isRightHeavy(node)) {
            if (balanceFactor(node.rightChild) > 0)
                node.rightChild = rightRotation(node.rightChild);
            return leftRotation(node);
        }
        return node;
    }

    private AVLNode leftRotation(AVLNode node) {
        var newRoot = node.rightChild;
        node.rightChild = newRoot.leftChild;
        newRoot.leftChild = node;
        setHeight(node);
        setHeight(newRoot);
        return newRoot;
    }

    private AVLNode rightRotation(AVLNode node) {
        var newRoot = node.leftChild;
        node.leftChild = newRoot.rightChild;
        newRoot.rightChild = node;
        setHeight(node);
        setHeight(newRoot);
        return newRoot;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private int balanceFactor(AVLNode node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    private int height(AVLNode node) {
        return (node == null) ? -1 : node.height;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVLNode node) {
        if (node == null)
            return true;
        if (isLeftHeavy(node) || isRightHeavy(node))
            return false;
        return isBalanced(node.leftChild) && isBalanced(node.rightChild);
    }

    public boolean isPerfect() {
        return isPerfect(root);
    }
    private boolean isPerfect(AVLNode node) {
        if (node == null)
            return true;
        if (balanceFactor(node) != 0)
            return false;
        return isPerfect(node.leftChild) && isPerfect(node.rightChild);
    }
}
