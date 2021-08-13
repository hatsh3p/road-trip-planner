package graph;

import java.util.*;

public class Solver<V> {
    private final HashMap<V,DijkstraRow<V>> table;
    private final Graph<V,Edge<V>> graph;
    private final V source;
    private final V target;

    public Solver(Graph<V,Edge<V>> graph, V source, V target) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.table = new HashMap<>();
    }

    public List<V> getShortestPath() {
        List<V> path = new ArrayList<>();
        dijkstrasAlgorithm();
        V current = target;
        while (current != source) {
            path.add(0, current);
            current = table.get(current).getPath();
        }
        path.add(0, source);
        return path;
    }

    public int getDistance() {
        return table.get(target).getCost();
    }

    private void dijkstrasAlgorithm() {
        // Initialize table with source vertex = 0 and all other vertices set to default
        table.put(source, new DijkstraRow<>(source, 0));
        List<V> vertices = graph.getAllVertices();
        for (V v : vertices) {
            if (!v.equals(source)) {
                table.put(v, new DijkstraRow<>(v));
            }
        }

        // For each vertex (starting with the lowest cost vertex)
                //  For each adjacent vertex
                        // If there is a lower cost path to the adjacent vertex, update the cost and the path
        for (V v : vertices) {
            V vertex = getLowestCostUnknownVertex();
            if (vertex != null) {
                DijkstraRow<V> row = table.get(vertex);
                int cost = table.get(vertex).getCost();
                row.setKnown(true);
                List<Edge<V>> adjacent = graph.getAdjacentVertices(vertex);
                for (Edge<V> edge : adjacent) {
                    V adjacentVertex = getAdjacentVertex(edge, vertex);
                    int weight = edge.getWeight();
                    int adjacentCost = table.get(adjacentVertex).getCost();
                    if (adjacentCost > cost + weight) {
                        table.get(adjacentVertex).setCost(cost + weight); // update cost
                        table.get(adjacentVertex).setPath(vertex); // update path
                    }
                }
            }
        }
    }

    private V getAdjacentVertex(Edge<V> edge, V vertex){
        if (vertex.equals(edge.getVertex1())) {
            return edge.getVertex2();
        } else {
            return edge.getVertex1();
        }
    }

    private V getLowestCostUnknownVertex() {
        V lowestVertex = null;
        int lowestCostSoFar = Integer.MAX_VALUE;
        for (Map.Entry v: table.entrySet()) {
            V vertex = (V) v.getKey();
            DijkstraRow<V> row = (DijkstraRow<V>) v.getValue();
            int cost = row.getCost();
            boolean known = row.isKnown();
            if (vertex != null && !known) {
                if (cost < lowestCostSoFar) {
                    lowestCostSoFar = cost;
                    lowestVertex = vertex;
                }
            }
        }
        return lowestVertex;
    }
}
