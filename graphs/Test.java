package com.company.graphs;

public class Test {
    public static void main(String[] args) {
        var weighted = new WeightedGraph();
        weighted.addNode("A");
        weighted.addNode("B");
        weighted.addNode("C");
        weighted.addNode("D");
        weighted.addNode("E");
        weighted.addEdge("A", "B",3);
        weighted.addEdge("A", "C",4);
        weighted.addEdge("A", "D",2);
        weighted.addEdge("B", "D",6);
        weighted.addEdge("C", "D",1);
        weighted.addEdge("B", "E",1);
        weighted.addEdge("D", "E",5);
        weighted.report();
        System.out.println(weighted.getShortestDistance("A", "E"));
        System.out.println(weighted.getShortestPath("A", "E"));
        System.out.println(weighted.getShortestDistance("C", "E"));
        System.out.println(weighted.getShortestPath("C", "E"));
        System.out.println(weighted.hasCycle());
        weighted.spanningTree().report();

//        var graph = new Graph();
//        graph.addNode("A");
//        graph.addNode("B");
//        graph.addNode("C");
//        graph.addNode("D");
//        graph.addEdge("A", "B");
//        graph.addEdge("A", "C");
//        graph.addEdge("B", "C");
//        graph.addEdge("D", "A");
//        System.out.println(graph.hasCycle());
    }
}
