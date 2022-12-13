package day12;

import java.util.ArrayList;
import java.util.List;

public class Node {

    final int height;
    List<Integer> neighbors;

    public Node(int height) {
        this.height = height;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(int nodeId) {
        neighbors.add(nodeId);
    }

    public int getHeight() {
        return height;
    }

    public List<Integer> getNeighbors() {
        return neighbors;
    }

}
