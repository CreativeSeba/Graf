package pack;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();

    public void printGraph() {
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

    public Node addNode(String name) {
        Node node = new Node(name);
        nodes.add(node);
        return node;
    }


    public Node removeNode(int id) {
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


    public Edge addEdge(int weight, int id1, int id2) {
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
    public Edge removeEdge(int id1, int id2){
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
    public int findRoad(int id1, int id2) {
        if (edges.isEmpty()) {
            System.out.println("Set of nodes is empty");
        } else {
            Node sourceNode = null;
            Node destinationNode = null;
            for (Node node : nodes) {
                if (node.id == id1) {
                    sourceNode = node;
                }
                if (node.id == id2) {
                    destinationNode = node;
                }
                if (sourceNode != null && destinationNode != null) {
                    break;
                }
            }

            HashMap<Node, Integer> distance = new HashMap<>();
            HashMap<Node, Node> predecessor = new HashMap<>();
            ArrayList<Node> Q = new ArrayList<>();

            for (Node node : nodes) {
                distance.put(node, Integer.MAX_VALUE);
                predecessor.put(node, null);
                Q.add(node);
            }

            distance.put(sourceNode, 0);

            while (!Q.isEmpty()) {
                Node u = null;
                int minDistance = Integer.MAX_VALUE;
                for (Node node : Q) {
                    if (distance.get(node) < minDistance) {
                        u = node;
                        minDistance = distance.get(node);
                    }
                }

                Q.remove(u);

                for (Edge edge : edges) {
                    if (edge.id1 == u.id || edge.id2 == u.id) {
                        Node v = edge.id1 == u.id ? getNodeById(edge.id2) : getNodeById(edge.id1);
                        int alt = distance.get(u) + edge.weight;
                        if (alt < distance.get(v)) {
                            distance.put(v, alt);
                            predecessor.put(v, u);
                        }
                    }
                }
            }

            int pathLength = distance.get(destinationNode);
            System.out.println("\nShortest path length from node " + id1 + " to node " + id2 + ": " + pathLength);
            return pathLength;
        }
        return 0;
    }
    private Node getNodeById(int id) {
        for (Node node : nodes) {
            if (node.id == id) {
                return node;
            }
        }
        return null;
    }
    public void algorytmKruskala(){
        ArrayList<Edge> notSortedEdges = new ArrayList<>(edges);
        ArrayList<Edge> sortedEdges = new ArrayList<>();
        ArrayList<Edge> withoutCycles = new ArrayList<>();
        int weight = Integer.MAX_VALUE;
        if(!edges.isEmpty()){
            weight = 0;
        }
        for(Edge edge : edges){
            if(edge.weight>weight){
                sortedEdges.add(edge);
                weight = edge.weight;
            }
        }
        for(Edge edge : sortedEdges){
            System.out.println(edge.weight);
        }
    }
}
