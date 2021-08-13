package graph;

public class DijkstraRow<T> {
    private final T vertex;
    private boolean known;
    private T path;
    private int cost;

    public DijkstraRow(T vertex) {
        this.vertex = vertex;
        this.known = false;
        this.path = null;
        this.cost = Integer.MAX_VALUE;
    }

    public DijkstraRow(T vertex, int cost) {
        this.vertex = vertex;
        this.known = false;
        this.path = null;
        this.cost = cost;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public T getPath() {
        return path;
    }

    public void setPath(T path) {
        this.path = path;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "DijkstraRow{" +
                "vertex=" + vertex +
                ", known=" + known +
                ", path=" + path +
                ", cost=" + cost +
                '}';
    }
}
