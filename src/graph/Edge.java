package graph;

import java.util.Objects;

public class Edge<T> {
    private final T vertex1;
    private final T vertex2;
    private final int weight;

    public Edge(T vertex1, T vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public T getVertex1() {
        return vertex1;
    }

    public T getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return weight == edge.weight && Objects.equals(vertex1, edge.vertex1) && Objects.equals(vertex2, edge.vertex2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex1, vertex2, weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", weight=" + weight +
                '}';
    }
}
