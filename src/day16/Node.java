package day16;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Node {

    private final List<String> name;

    private final int value;

    private Set<Edge> edges = new HashSet<>();

    public Node(String name, int value) {
        this.name = List.of(name);
        this.value = value;
    }

    public Node(List<String> name, int value) {
        this.name = name;
        this.value = value;
    }

    public List<String> getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getName() + ":" + value;
    }
}
