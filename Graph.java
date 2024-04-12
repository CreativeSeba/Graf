package pack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
public class Graph {
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();

    public void print_graph() {
        for (Node node : nodes) {
            System.out.println("\nNode id: " + node.id+" | Node name: "+node.name+"\nEdges: ");
            for (Edge edge : edges){
                int i = 0;
                if(node.id==edge.id1||node.id==edge.id2){
                    i++;
                    System.out.println("Edge id1: " + edge.id1+" | id2: "+ edge.id2+" | weight "+edge.weight);
                }
            }
        }
    }

    public Node add_node(String name) {
        Node node = new Node(name);
        nodes.add(node);
        return node;
    }


    public Node remove_node(int id) {
        if (nodes.size() == 0) {
            System.out.println("No nodes to remove");
        }
        ArrayList<Edge> edgesToRemove = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.id1 == id || edge.id2 == id) {
                edgesToRemove.add(edge);
            }
        }
        edges.removeAll(edgesToRemove);

        for (Node node : nodes) {
            if (node.id == id) {
                nodes.remove(node);
                return node;
            }
        }
        return null;
    }


    public Edge add_edge(int weight, int id1, int id2) {
        boolean node1Exists = false;
        boolean node2Exists = false;

        for (Node node : nodes) {
            if (node.id == id1) {
                node1Exists = true;
            }
            if (node.id == id2) {
                node2Exists = true;
            }
        }
        if (!node1Exists || !node2Exists || id1 == id2) {
            System.out.println("Error with adding edge");
        } else {
            Edge edge = new Edge(weight, id1, id2);
            edges.add(edge);
            return edge;
        }
        return null;
    }
    public Edge remove_edge(int id1, int id2){
        Edge edgeToRemove = null;

        for(Edge edge : edges){
            if((edge.id1==id1 && edge.id2==id2) || (edge.id1==id2 && edge.id2==id1)){
                edgeToRemove = edge;
                break;
            }
        }
        if(edgeToRemove != null){
            edges.remove(edgeToRemove);
            return edgeToRemove;
        }
        else {
            System.out.println("Edge not found");
        }
        return null;
    }

    public void findRoad(int sourceId, int destinationId) {
        HashMap<Integer, Integer> distance = new HashMap<>(); // Stores the shortest distance from the source to each node
        HashMap<Integer, Edge> previousEdge = new HashMap<>(); // Stores the previous edge on the shortest path to each node
        HashSet<Integer> visited = new HashSet<>(); // Stores the visited nodes

        for (Node node : nodes) {
            distance.put(node.id, Integer.MAX_VALUE);
            previousEdge.put(node.id, null);
        }

// Distance from source to itself is 0
        distance.put(sourceId, 0); // Add this line

        while (!visited.contains(destinationId)) {
            int currentId = -1;
            int minDistance = Integer.MAX_VALUE;
            // Find the node with the shortest distance that has not been visited yet
            for (Node node : nodes) {
                if (!visited.contains(node.id) && distance.get(node.id) < minDistance) {
                    minDistance = distance.get(node.id);
                    currentId = node.id;
                }
            }

            if (currentId == -1) {
                // No reachable nodes left, exit the loop
                break;
            }

            visited.add(currentId);

            // Update distances to neighbors of the current node
            for (Edge edge : edges) {
                if (edge.id1 == currentId && !visited.contains(edge.id2)) {
                    int newDistance = distance.get(currentId) + edge.weight;
                    if (newDistance < distance.get(edge.id2)) {
                        distance.put(edge.id2, newDistance);
                        previousEdge.put(edge.id2, edge);
                    }
                } else if (edge.id2 == currentId && !visited.contains(edge.id1)) {
                    int newDistance = distance.get(currentId) + edge.weight;
                    if (newDistance < distance.get(edge.id1)) {
                        distance.put(edge.id1, newDistance);
                        previousEdge.put(edge.id1, edge);
                    }
                }
            }
        }

        // Reconstruct the shortest path from source to destination
        ArrayList<Edge> shortestPath = new ArrayList<>();
        Edge currentEdge = previousEdge.get(destinationId);
        while (currentEdge != null) {
            shortestPath.add(currentEdge);
            if (currentEdge.id1 == destinationId) {
                destinationId = currentEdge.id2;
            } else {
                destinationId = currentEdge.id1;
            }
            currentEdge = previousEdge.get(destinationId);
        }
        Collections.reverse(shortestPath);

        // Print the shortest path and its length
        if (!shortestPath.isEmpty()) {
            System.out.println("\nShortest path:\n");
            int road = 0;
            for (Edge edge : shortestPath) {
                road+=edge.weight;
                System.out.println("Edge id1: " + edge.id1 + " | id2: " + edge.id2 + " | weight " + edge.weight);
            }
            System.out.println("\nShortest path length: " + road);
        } else {
            System.out.println("No path found between the nodes.");
        }
    }
}
