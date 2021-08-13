package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// resource used: https://www.baeldung.com/java-graphs

public class Graph<V, E> {
    private final Map<V, List<E>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addVertex(V vertex) {
        adjacencyMap.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(V vertex1, V vertex2, int weight) {
        addVertex(vertex1);
        addVertex(vertex2);
        adjacencyMap.get(vertex1).add((E) new Edge<>(vertex1, vertex2, weight));
        adjacencyMap.get(vertex2).add((E) new Edge<>(vertex2, vertex1, weight));
    }

    public List<E> getAdjacentVertices(V vertex) {
        return adjacencyMap.get(vertex);
    }

    public List<V> getAllVertices() {
        List<V> vertices = new ArrayList<>();
        for (Map.Entry<V, List<E>> city: adjacencyMap.entrySet()) {
            vertices.add(city.getKey());
        }
        return vertices;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacencyMap=" + adjacencyMap +
                '}';
    }
}
