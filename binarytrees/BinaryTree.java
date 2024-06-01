package com.company.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private static class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        var node = new Node(value);
        if (root == null) {
            root = node;
            return;
        }
        var current = root;
        while (true) {
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            } else if (value > current.value) {
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            } else
                return;
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (value == current.value)
                return true;
            else if (value < current.value)
                current = current.leftChild;
            else
                current = current.rightChild;
        }
        return false;
    }

    public boolean remove(int value) {
        var current = root;
        if (root == null)
            return false;
        if (root.value == value) {
            var node = deepestNode(root);
            remove(node.value);
            root.value = node.value;
            return true;
        }
        while (!isLeaf(current)) {
            if (value < current.value) {
                if (current.leftChild != null) {
                    if (current.leftChild.value == value) {
                        if (isLeaf(current.leftChild)) {
                            current.leftChild = null;
                            return true;
                        } else if (isFullParent(current.leftChild)) {
                            var node = deepestNode(current.leftChild);
                            remove(node.value);
                            current.leftChild.value = node.value;
                            return true;
                        } else {
                            if (current.leftChild.rightChild == null)
                                current.leftChild = current.leftChild.leftChild;
                            else
                                current.leftChild = current.leftChild.rightChild;
                            return true;
                        }
                    } else
                        current = current.leftChild;
                } else
                    return false;
            } else if (value > current.value) {
                if (current.rightChild != null) {
                    if (current.rightChild.value == value) {
                        if (isLeaf(current.rightChild)) {
                            current.rightChild = null;
                            return true;
                        } else if (isFullParent(current.rightChild)) {
                            var node = deepestNode(current.rightChild);
                            remove(node.value);
                            current.rightChild.value = node.value;
                            return true;
                        } else {
                            if (current.rightChild.leftChild == null)
                                current.rightChild = current.rightChild.rightChild;
                            else
                                current.rightChild = current.rightChild.leftChild;
                            return true;
                        }
                    } else
                        current = current.rightChild;
                } else
                    return false;
            }
        }
        return false;
    }

    public void traversalPreOrder() {
        traversalPreOrder(root);
    }

    private void traversalPreOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.value);
        traversalPreOrder(node.leftChild);
        traversalPreOrder(node.rightChild);
    }

    public void traversalInOrder() {
        traversalInOrder(root);
    }

    private void traversalInOrder(Node node) {
        if (node == null)
            return;
        traversalInOrder(node.leftChild);
        System.out.println(node.value);
        traversalInOrder(node.rightChild);
    }

    public void traversalPostOrder() {
        traversalPostOrder(root);
    }

    private void traversalPostOrder(Node node) {
        if (node == null)
            return;
        traversalPostOrder(node.leftChild);
        traversalPostOrder(node.rightChild);
        System.out.println(node.value);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null)
            return -1;
        if (isLeaf(node))
            return 0;
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    public boolean isValid() {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValid(Node node, int min, int max) {
        return node == null
                || ((min < node.value && node.value < max)
                && isValid(node.leftChild, min, node.value)
                && isValid(node.rightChild, node.value, max));
    }

    public void nodesAtK(int k) {
        nodesAtK(root, k);
    }

    private void nodesAtK(Node node, int k) {
        if (node == null)
            return;
        if (k == 0) {
            System.out.println(node.value);
            return;
        }
        nodesAtK(node.leftChild, k - 1);
        nodesAtK(node.rightChild, k - 1);
    }

    public void levelOrderTraversal() {
        for (int i = 0; i <= height(); i++)
            nodesAtK(i);
    }

    public int min() {
        if (root == null)
            throw new IllegalStateException();
        return min(root);
    }

    private int min(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (isLeaf(node))
            return node.value;
        return Math.min(Math.min(min(node.leftChild), min(node.rightChild)), node.value);
    }

    public int minSearch(Node node) {
        if (node == null)
            throw new IllegalStateException();
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node.value;
    }

    public int max() {
        if (root == null)
            throw new IllegalStateException();
        return max(root);
    }

    private int max(Node node) {
        if (node.rightChild == null)
            return node.value;
        return max(node.rightChild);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        if (isLeaf(node))
            return 1;
        return 1 + size(node.leftChild) + size(node.rightChild);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node node) {
        if (node == null)
            return 0;
        if (isLeaf(node))
            return 1;
        return countLeaves(node.leftChild) + countLeaves(node.rightChild);
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        if (node == null)
            return false;
        if (value < node.value)
            return contains(node.leftChild, value);
        if (value > node.value)
            return contains(node.rightChild, value);
        return true;
    }

    public boolean areSiblings(int value1, int value2) {
        return areSiblings(root, value1, value2);
    }

    private boolean areSiblings(Node node, int value1, int value2) {
        if (node == null)
            return false;

        if (isFullParent(node)) {
            if ((node.leftChild.value == value1 && node.rightChild.value == value2) ||
                    (node.leftChild.value == value2 && node.rightChild.value == value1))
                return true;
        }

        if (node.leftChild != null && value1 < node.value && value2 < node.value)
            return areSiblings(node.leftChild, value1, value2);
        if (node.rightChild != null && value1 > node.value && value2 > node.value)
            return areSiblings(node.rightChild, value1, value2);
        return false;
    }

    public List<Integer> getAncestors(int value) {
        var list = new ArrayList<Integer>();
        getAncestors(root, value, list);
        return list;
    }

    private boolean getAncestors(Node node, int value, List<Integer> list) {
        if (node == null)
            return false;
        if (node.value == value)
            return true;
        list.add(node.value);
        if (value < node.value)
            return getAncestors(node.leftChild, value, list);
        return getAncestors(node.rightChild, value, list);
    }

    private boolean isFullParent(Node node) {
        return node.leftChild != null && node.rightChild != null;
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

    private Node deepestNode(Node node) {
        var minNode = node.rightChild;
        int minLevel = 0;
        while (minNode.leftChild != null) {
            minNode = minNode.leftChild;
            ++minLevel;
        }

        var maxNode = node.leftChild;
        int maxLevel = 0;
        while (maxNode.rightChild != null) {
            maxNode = maxNode.rightChild;
            ++maxLevel;
        }
        return (minLevel > maxLevel) ? minNode : maxNode;
    }

    public boolean equals(BinaryTree other) {
        if (other == null)
            return false;
        return equals(root, other.root);
    }

    private boolean equals(Node node, Node other) {
        if (node == null || other == null)
            return node == other;
        return node.value == other.value
                && equals(node.leftChild, other.leftChild)
                && equals(node.rightChild, other.rightChild);
    }
}