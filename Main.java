package pack;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Node node1 = graph.add_node("Node 1");
        Node node2 = graph.add_node("Node 2");
        Node node3 = graph.add_node("Node 3");
        Node node4 = graph.add_node("Node 4");

        graph.add_edge(1, node1.id, node2.id);
        graph.add_edge(2, node2.id, node3.id);
        graph.add_edge(3, node1.id, node3.id);
        graph.add_edge(4, node3.id, node4.id);

        graph.findRoad(1, 2);
    }
}
