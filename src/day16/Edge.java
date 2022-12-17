package day16;

import java.util.List;
import java.util.Objects;

public class Edge {

    private final List<String> from;
    private final List<String> to;
    private final long cost;
    private final long distance;

    public Edge(List<String> from, List<String> to, long cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.distance = cost;
    }

    public Edge(List<String> from, List<String> to, long cost, long distance) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.distance = distance;
    }

    public List<String> getFrom() {
        return from;
    }

    public List<String> getTo() {
        return to;
    }

    public long getCost() {
        return cost;
    }

    public long getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return from.equals(edge.from) && to.equals(edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return from + " -> " + to + ": " + cost + "; " + distance;
    }
}
