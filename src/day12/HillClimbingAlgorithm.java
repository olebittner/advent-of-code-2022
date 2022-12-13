package day12;

import util.Constants;
import util.Util;

import java.util.*;

public class HillClimbingAlgorithm {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day12/HillClimbingAlgorithm.txt");
        int width = input.get(0).length();
        int height = input.size();
        int start = -1;
        int exit = -1;
        Node[] nodes = new Node[width * height];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int i = y + x * width;
                if (input.get(x).charAt(y) == 'S')
                    start = i;
                if (input.get(x).charAt(y) == 'E')
                    exit = i;
                if (nodes[i] == null)
                    nodes[i] = new Node(getHeight(input.get(x).charAt(y)));
                // RIGHT
                if (y < width - 1) {
                    if (nodes[i + 1] == null)
                        nodes[i + 1] = new Node(getHeight(input.get(x).charAt(y + 1)));
                    if (nodes[i + 1].getHeight() <= nodes[i].getHeight() + 1)
                        nodes[i].addNeighbor(i + 1);
                }
                // UP
                if (x > 0 && nodes[i - width].getHeight() <= nodes[i].getHeight() + 1)
                    nodes[i].addNeighbor(i - width);
                // LEFT
                if (y > 0 && nodes[i - 1].getHeight() <= nodes[i].getHeight() + 1)
                    nodes[i].addNeighbor(i - 1);
                // DOWN
                if (x < height - 1) {
                    if (nodes[i + width] == null)
                        nodes[i + width] = new Node(getHeight(input.get(x + 1).charAt(y)));
                    if (nodes[i + width].getHeight() <= nodes[i].getHeight() + 1)
                        nodes[i].addNeighbor(i + width);
                }
            }
        }
        List<Integer> shortestPath = findWithAStar(nodes, start, exit, width);
        print(nodes, (ArrayList<Integer>) shortestPath);
        System.out.println(shortestPath.size() - 1);
//        System.out.println(part2());
    }

    static int getHeight(char c) {
        return switch (c) {
            case 'S' -> getHeight('a');
            case 'E' -> getHeight('z');
            default -> Constants.ALPHABET_LOWER.indexOf(c);
        };
    }

    static List<Integer> findWithAStar(Node[] nodes, int start, int exit, int width) {
        Queue<RoutePart> openQueue = new PriorityQueue<>();
        Map<Integer, RoutePart> allParts = new HashMap<>();
        RoutePart s = new RoutePart(start, null, 0d, calcDist(start, exit, width));
        openQueue.add(s);
        allParts.put(start, s);
        while (!openQueue.isEmpty()) {
            RoutePart next = openQueue.poll();
            if (next.getCurrentNode() == exit) {
                List<Integer> route = new ArrayList<>();
                RoutePart current = next;
                do {
                    route.add(0, current.getCurrentNode());
                    current = allParts.get(current.getPreviousNode());
                } while (current != null);
                return route;
            }
            nodes[next.getCurrentNode()].getNeighbors().forEach(neighbor -> {
                RoutePart nextPart = allParts.getOrDefault(neighbor, new RoutePart(neighbor));
                allParts.put(neighbor, nextPart);

                double newScore = next.getRouteScore() + 1;
                if (newScore < nextPart.getRouteScore()) {
                    nextPart.setPreviousNode(next.getCurrentNode());
                    nextPart.setRouteScore(newScore);
                    nextPart.setEstimatedScore(newScore + calcDist(neighbor, exit, width));
                    openQueue.add(nextPart);
                }
            });
        }
        return null;
    }

    static double calcDist(int from, int to, int width) {
        int x = Math.abs(Math.floorDiv(to, width) - Math.floorDiv(from, width));
        int y = Math.abs(to % width - from % width);
        return Math.abs(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }

    static void print(Node[] nodes, ArrayList<Integer> currentPath) {
        System.out.println("----------------------------------------------------------------------");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            String c = String.valueOf(Constants.ALPHABET_LOWER.charAt(nodes[i].getHeight()));
            if (currentPath.get(currentPath.size() - 1) == i) {
                builder.append("◈");
            } else if (currentPath.contains(i)) {
                builder.append("●");
            } else {
                builder.append(c);
            }
            if (i > 0 && (i + 1) % 132 == 0) {
                builder.append("\r\n");
            }
        }
        System.out.println(builder);
    }

}
