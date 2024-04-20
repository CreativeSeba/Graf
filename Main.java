package pack;

public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph();

        Node node0 = graph.addNode("Node 0");
        Node node1 = graph.addNode("Node 1");
        Node node2 = graph.addNode("Node 2");
        Node node3 = graph.addNode("Node 3");
        Node node4 = graph.addNode("Node 4");

        graph.addEdge(1, node0.id, node1.id);
        graph.addEdge(5, node1.id, node2.id);
        graph.addEdge(3, node0.id, node2.id);
        graph.addEdge(4, node2.id, node3.id);
        graph.addEdge(3, node0.id, node1.id);
        graph.addEdge(5, node1.id, node4.id);

        graph.printGraph();
        graph.findRoad(node3.id, node4.id);
        graph.algorytmKruskala();
    }
}
