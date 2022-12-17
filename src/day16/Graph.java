package day16;

import java.util.*;
import java.util.stream.Collectors;

import static day16.ProboscideaVolcanium.EXIT;

public class Graph {
    public List<List<Edge>> routesToExit = new ArrayList<>();

    private final HashMap<List<String>, Node> nodes = new HashMap<>();
    private final HashMap<List<String>, Set<Edge>> edges = new HashMap<>();

    public Set<Node> getNodes() {
        return new HashSet<>(nodes.values());
    }

    public Set<Edge> getEdges() {
        return edges.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public HashMap<List<String>, Set<Edge>> getEdgesMap() {
        return edges;
    }

    public void addNode(Node n) {
        nodes.put(n.getName(), n);
    }

    public Node getNode(List<String> name) {
        return nodes.get(name);
    }

    public void addEdge(Edge e) {
        if (!edges.containsKey(e.getFrom()))
            edges.put(e.getFrom(), new HashSet<>());
        edges.get(e.getFrom()).add(e);
    }

    public void moveEdgesToNodes() {
        System.out.println("Moving " + edges.size() + " edges into " + nodes.size() + " nodes");
        edges.entrySet().parallelStream().forEach(s -> nodes.get(s.getKey()).setEdges(s.getValue()));
        System.out.println("Done");
    }

    public Map<List<String>, List<Edge>> findAllShortestPath(Node start) {

        Map<List<String>, List<Edge>> routes = new HashMap<>();
        Set<List<String>> closedNodes = new HashSet<>();
        Map<List<String>, Long> dist = new HashMap<>();
        routes.put(start.getName(), new ArrayList<>());
        dist.put(start.getName(), 0L);
        Queue<List<String>> openNodes = new PriorityQueue<>(10000, (o1, o2) -> Math.toIntExact(dist.get(o1) - dist.get(o2)));

        openNodes.add(start.getName());

        while (!openNodes.isEmpty()) {
            List<String> currentNode = openNodes.poll();
            for (Edge neighborEdge : findNeighbors(currentNode)) {
                List<String> neighbor = neighborEdge.getTo();
                if (!closedNodes.contains(neighbor)) {
                    List<Edge> route = routes.get(currentNode);
                    route = new ArrayList<>(route);
                    route.add(neighborEdge);
                    Long oldCost = dist.get(neighbor);
                    long newCost = getRouteCost(dist.get(currentNode), neighborEdge);
                    if (neighbor.equals(EXIT.getName())) {
                        routesToExit.add(route);
                    }
                    if (!dist.containsKey(neighbor) || oldCost > newCost) {
                        routes.put(neighbor, route);
                        dist.put(neighbor, newCost);
                        openNodes.add(neighbor);
                    }
                }
            }
            closedNodes.add(currentNode);
            if (closedNodes.size() % 1000 == 0)
                System.out.println("Pathfinding: open " + openNodes.size() + " / closed " + closedNodes.size() + " / total " + this.nodes.size());
        }
        routes.remove(start.getName());
        return routes;
    }

    public static Long getRouteCost(Long dist, Edge nextStep) {
        if (dist == null)
            return nextStep.getCost();
        return Math.addExact(dist, nextStep.getCost());
    }

    public static Long getRouteCost(List<Edge> route) {
        if (route == null)
            return null;
        if (route.isEmpty())
            return 0L;
        return route.stream().map(Edge::getCost).reduce(Math::addExact).orElseThrow();
    }

    public Set<Edge> findNeighbors(List<String> node) {
        return getNode(node).getEdges();
    }
}
