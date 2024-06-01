package com.company.graphs;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class WeightedGraph {
    private static class Node {

        private String label;
        private final List<Edge> edges = new ArrayList<>();

        private Node(String label) {
            this.label = label;
        }

        private void addEdge(Node to, int weight) {
            if (!isAdjacent(to))
                edges.add(new Edge(this, to, weight));
        }

        private boolean isAdjacent(Node to) {
            for (Edge edge : edges)
                if (edge.to == to)
                    return true;
            return false;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        @Override
        public String toString() {
            return label;
        }

    }

    private static class Edge {

        private Node from;
        private Node to;
        private int weight;

        private Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " -> " + to + " weight " + weight;
        }
    }

    private final Map<String, Node> nodes = new HashMap<>();

    public void addNode(String label) {
        if (label == null)
            throw new IllegalStateException();
        nodes.putIfAbsent(label, new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new NoSuchElementException();

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void report() {
        for (Node node : nodes.values()) {
            var edges = node.getEdges();
            if (!edges.isEmpty())
                System.out.println(node + " has edges " + edges);
        }
    }

    private record NodeEntry(Node node, int priority) {
    }

    public int getShortestDistance(String from, String to) {
        if (from == null || to == null)
            throw new IllegalStateException();

        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new NoSuchElementException();

        Queue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.priority));
        Map<Node, Integer> distances = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        distances.put(fromNode, 0);
        queue.add(new NodeEntry(fromNode, 0));

        while (!queue.isEmpty()) {
            var current = queue.remove().node;
            visited.add(current);
            for (Edge edge : current.getEdges()) {
                if (visited.contains(edge.to))
                    continue;
                var adjacent = edge.to;
                var newDistance = distances.get(current) + edge.weight;
                var distance = distances.get(adjacent);
                if (distance == null || newDistance < distance) {
                    queue.add(new NodeEntry(adjacent, newDistance));
                    distances.put(adjacent, newDistance);
                }
            }
        }

        return distances.get(toNode);
    }

    public List<String> getShortestPath(String from, String to) {
        if (from == null || to == null)
            throw new IllegalStateException();

        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new NoSuchElementException();


        Queue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.priority));
        Map<Node, Integer> distances = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        distances.put(fromNode, 0);

        queue.add(new NodeEntry(fromNode, 0));

        while (!queue.isEmpty()) {
            var node = queue.remove().node;
            visited.add(node);
            for (Edge edge : node.getEdges()) {
                if (visited.contains(edge.to))
                    continue;
                var adjacent = edge.to;
                var distance = distances.get(node) + edge.weight;
                var prevDistance = distances.get(adjacent);
                if (prevDistance == null || distance < prevDistance) {
                    queue.add(new NodeEntry(adjacent, distance));
                    distances.put(adjacent, distance);
                    previous.put(adjacent, node);
                }
            }
        }

        return getPath(toNode, previous);
    }

    @NotNull
    private static List<String> getPath(Node toNode, Map<Node, Node> previous) {
        Stack<String> reversePath = new Stack<>();
        var current = toNode;
        while (current != null) {
            reversePath.push(current.label);
            current = previous.get(current);
        }

        List<String> path = new ArrayList<>();
        while (!reversePath.isEmpty())
            path.add(reversePath.pop());

        return path;
    }

    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();

        for (Node node : nodes.values())
            if (!visited.contains(node) && hasCycle(node, null, visited))
                return true;
        return false;
    }

    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        visited.add(node);

        for (Edge edge : node.getEdges()) {
            var adjacent = edge.to;
            if (adjacent != parent && (visited.contains(adjacent) || hasCycle(adjacent, node, visited)))
                return true;
        }
        return false;
    }

    public WeightedGraph spanningTree() {
        var tree = new WeightedGraph();
        Set<Node> visited = new HashSet<>();
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.weight));
        Node startNode = nodes.values().iterator().next();
        queue.add(new Edge(null, startNode, 0));

        while (!queue.isEmpty()) {
            var entryEdge = queue.remove();
            Node from = entryEdge.from;
            Node to = entryEdge.to;
            if (visited.contains(to))
                continue;
            tree.addNode(to.label);
            if (from != null)
                tree.addEdge(from.label, to.label, entryEdge.weight);
            visited.add(to);
            for (Edge edge : to.getEdges()) {
                var adjacent = edge.to;
                if (visited.contains(adjacent))
                    continue;
                queue.add(new Edge(to, adjacent, edge.weight));
            }
        }

        return tree.nodes.size() == nodes.size() ? tree : null;
    }
}
