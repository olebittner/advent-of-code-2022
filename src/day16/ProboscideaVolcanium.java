package day16;

import util.Util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProboscideaVolcanium {

    public static final String INPUT_PATTERN = "Valve (\\w\\w) has flow rate=(\\d+); tunnels? leads? to valves? (.*)";
    public static final Node EXIT = new Node("><", 0);

    public static void main(String[] args) {
        List<String> input = Util.readInput("day16/ProboscideaVolcanium.txt");
        final List<String> startAt = List.of("AA");
        final int minutes = 30;
        Graph fullGraph = parseInput(input);
        Graph compressedGraph = new Graph();

        Set<Node> relevantNodes = new HashSet<>(fullGraph.getNodes().stream().filter(n -> n.getValue() > 0).toList());
        relevantNodes.add(fullGraph.getNode(startAt));
        compressedGraph.getNodes().addAll(relevantNodes);
        for (Node node : relevantNodes) {
            fullGraph.findAllShortestPath(node).entrySet().stream()
                    .filter(s -> relevantNodes.contains(fullGraph.getNode(s.getKey())))
                    .map(s -> new Edge(node.getName(), s.getKey(), s.getValue().stream().mapToLong(Edge::getDistance).sum()))
                    .forEach(compressedGraph::addEdge);
        }
        if (fullGraph.getNode(startAt).getValue() == 0)
            relevantNodes.removeIf(node -> node.getName().equals(startAt));

        Graph layeredGraph = new Graph();
        layeredGraph.addNode(EXIT);
        layeredGraph.addNode(fullGraph.getNode(startAt));
        buildLayeredGraph(layeredGraph, compressedGraph.getEdges(), relevantNodes, new HashSet<>(relevantNodes), startAt, startAt, 0, minutes);
        layeredGraph.moveEdgesToNodes();
        List<Edge> list = layeredGraph.findAllShortestPath(layeredGraph.getNode(startAt)).get(EXIT.getName());
        layeredGraph.routesToExit.sort(Comparator.comparingLong(Graph::getRouteCost));
        layeredGraph.routesToExit.forEach(r -> System.out.println(Graph.getRouteCost(r) + " " + r));
        System.out.println();
        System.out.println(list);
        list.removeIf(edge -> edge.getTo().equals(EXIT.getName()));
        long totalFlow = 0;
        long currentFlow = 0;
        int minute = 0;
        while (minute < minutes) {
            if (!list.isEmpty()) {
                Edge edge = list.get(0);
                for (int i = 0; i < edge.getDistance(); i++) {
                    minute++;
                    totalFlow += currentFlow;
                    printMinute(minute, currentFlow, totalFlow, edge.getFrom());
                    System.out.println("You move to " + edge.getTo());
                }
                minute++;
                totalFlow += currentFlow;
                printMinute(minute, currentFlow, totalFlow, edge.getFrom());
                System.out.println("You open valve: " + Arrays.toString(edge.getTo().toArray()));
                currentFlow = layeredGraph.getNode(edge.getTo()).getValue();
                list.remove(0);
            } else {
                minute++;
                totalFlow += currentFlow;
                printMinute(minute, currentFlow, totalFlow, new ArrayList<>());
            }
        }
        System.out.println(totalFlow);
    }

    public static void printMinute(int min, long currentFlow, long totalFlow, List<String> open) {
        System.out.println("Minute " + min);
        System.out.println("Valves " + Arrays.toString(open.toArray()) + " are open, releasing " + currentFlow + " pressure (" + totalFlow + ")");
    }

    public static Graph parseInput(List<String> input) {
        Graph graph = new Graph();
        Pattern r = Pattern.compile(INPUT_PATTERN);
        for (String line : input) {
            Matcher matcher = r.matcher(line);
            if (matcher.find()) {
                graph.addNode(new Node(matcher.group(1), Integer.parseInt(matcher.group(2))));
                Arrays.stream(matcher.group(3).split(", ")).map(to -> new Edge(List.of(matcher.group(1)), List.of(to), 1)).forEach(graph::addEdge);
            }
        }
        graph.moveEdgesToNodes();
        return graph;
    }

    public static Graph buildLayeredGraph(Graph graph, Set<Edge> edges, Set<Node> relevantNodes, Set<Node> allNodes, List<String> pos, List<String> posPart, int layer, int budgetLeft) {
        long costMultiplier = relevantNodes.stream().mapToLong(Node::getValue).sum();
        graph.addEdge(new Edge(pos, EXIT.getName(), Math.max(0, Math.multiplyExact(costMultiplier, budgetLeft))));
        if (relevantNodes.isEmpty() || budgetLeft <= 0) {
            return graph;
        }
        for (Node node : relevantNodes) {
            Edge baseEdge = edges.stream().filter(edge -> edge.getFrom().equals(posPart) && edge.getTo().equals(node.getName())).findFirst().orElseThrow();
            int newBudget = (int) (budgetLeft - baseEdge.getCost()) - 1;
            if (newBudget < 0)
                continue;
            List<String> nextNode = new ArrayList<>(pos);
            nextNode.addAll(node.getName());
            graph.addNode(new Node(nextNode, allNodes.stream().filter(n -> nextNode.stream().anyMatch(n2 -> n.getName().equals(List.of(n2)))).mapToInt(Node::getValue).sum()));
            graph.addEdge(new Edge(pos, nextNode, Math.multiplyExact(baseEdge.getDistance() + 1, costMultiplier), baseEdge.getDistance()));
            buildLayeredGraph(graph, edges, relevantNodes.stream().filter(n -> !n.equals(node)).collect(Collectors.toSet()), allNodes, nextNode, node.getName(), layer + 1, newBudget);
        }
        return graph;
    }

}
