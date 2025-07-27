import java.lang.String;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {

    Map<String, String> attributes;
    Collection<Node> nodes;
    Collection<Edge> edges;

    public Graph() {
        this.attributes = new HashMap<String, String>();
        this.nodes = new LinkedList<Node>();
        this.edges = new LinkedList<Edge>();
    }

    public Graph(Map<String, String> attributes) {
        this.attributes = attributes;
        this.nodes = new LinkedList<Node>();
        this.edges = new LinkedList<Edge>();    
    }

    public Collection<Node> getNodes() {
        return this.nodes;
    }

    public Collection<Edge> getEdges() {
        return this.edges;
    }

    public Graph node(String name) {
        Node n = new Node(name);
        this.nodes.add(n);
        return this;
    }

    public Graph node(String name, Map<String, String> attributes) {
        Node n = new Node(name, attributes);
        this.nodes.add(n);
        return this;
    }

    public Graph edge(String start, String end) {
        Edge e = new Edge(start, end);
        this.edges.add(e);
        return this;
    }

    public Graph edge(String start, String end, Map<String, String> attributes) {
        Edge e = new Edge(start, end, attributes);
        this.edges.add(e);
        return this;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }
}
