package com.company.graphs;

import java.util.*;

public class Graph {
    private static class Node {
        private final String label;
        private final List<Node> adjacentList = new ArrayList<>();

        private Node(String label) {
            this.label = label;
        }

        public void addAdjacent(Node to) {
            if (!adjacentList.contains(to))
                adjacentList.add(to);
        }

        public void removeAdjacent(Node to) {
            adjacentList.remove(to);
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private final Map<String, Node> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        if (label == null)
            throw new IllegalStateException();

        adjacencyList.putIfAbsent(label, new Node(label));
    }

    public void removeNode(String label) {
        if (label == null)
            throw new IllegalStateException();

        if (!adjacencyList.containsKey(label))
            return;

        var tempNode = adjacencyList.get(label);

        for (Node node : adjacencyList.values())
            node.removeAdjacent(tempNode);

        adjacencyList.remove(label);
    }

    public void addEdge(String from, String to) {
        if (from == null || to == null)
            throw new IllegalStateException();

        var fromNode = adjacencyList.get(from);
        var toNode = adjacencyList.get(to);

        if (fromNode == null || toNode == null)
            throw new NoSuchElementException();

        fromNode.addAdjacent(toNode);
    }

    public void removeEdge(String from, String to) {
        if (from == null || to == null)
            throw new IllegalStateException();

        var fromNode = adjacencyList.get(from);
        var toNode = adjacencyList.get(to);

        if (fromNode == null || toNode == null)
            return;

        fromNode.removeAdjacent(toNode);
    }

    public void traverseDepthFirst(String root) {
        Set<Node> set = new HashSet<>();
        if (!adjacencyList.containsKey(root))
            throw new NoSuchElementException();
        var rootNode = adjacencyList.get(root);
        traverseDepthFirst(rootNode, set);
    }

    private void traverseDepthFirst(Node node, Set<Node> set) {
        if (set.contains(node))
            return;
        set.add(node);
        System.out.println(node);
        for (Node adjacent : node.adjacentList)
            traverseDepthFirst(adjacent, set);
    }

    public void traverseDepthFirstIter(String root) {
        Set<Node> set = new HashSet<>();
        if (!adjacencyList.containsKey(root))
            throw new NoSuchElementException();
        Stack<Node> stack = new Stack<>();
        Node current = adjacencyList.get(root);
        stack.add(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (set.contains(current))
                continue;
            set.add(current);
            System.out.println(current);
            stack.addAll(current.adjacentList);
        }
    }

    public void traverseBreadthFirstIter(String root) {
        Set<Node> set = new HashSet<>();
        if (!adjacencyList.containsKey(root))
            throw new NoSuchElementException();
        Queue<Node> queue = new ArrayDeque<>();
        Node current = adjacencyList.get(root);
        queue.add(current);
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (set.contains(current))
                continue;
            set.add(current);
            System.out.println(current);
            queue.addAll(current.adjacentList);
        }
    }

    public List<String> topologicalSort() {
        Set<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        List<String> sorted = new ArrayList<>();

        for (Node node : adjacencyList.values())
            topologicalSort(node, stack, set);

        while (!stack.isEmpty())
            sorted.add(stack.pop().label);

        return sorted;
    }

    private void topologicalSort(Node node, Stack<Node> stack, Set<Node> set) {
        if (set.contains(node))
            return;
        set.add(node);
        for (Node adjacent : node.adjacentList) {
            topologicalSort(adjacent, stack, set);
        }
        stack.add(node);
    }

    public boolean hasCycle() {
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        for (Node node : adjacencyList.values()) {
            if (hasCycle(node, visiting, visited))
                return true;
        }
        return false;
    }

    private boolean hasCycle(Node node, Set<Node> visiting, Set<Node> visited) {
        visiting.add(node);

        for (var adjacent : node.adjacentList)
            if (!visited.contains(adjacent) && (visiting.contains(adjacent) || hasCycle(adjacent, visiting, visited)))
                return true;

        visiting.remove(node);
        visited.add(node);

        return false;
    }

    public void report() {
        for (Node node : adjacencyList.values()) {
            if (!node.adjacentList.isEmpty())
                System.out.println(node + " is connected to " + node.adjacentList);
        }
    }
}
