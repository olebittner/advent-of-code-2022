package day09;

import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RopeBridge {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day09/RopeBridge.txt");
        System.out.println(part1(input));
        System.out.println(part2(input, 10));
    }

    public static int part1(List<String> input) {
        int[] head = {0, 0};
        int[] tail = {0, 0};
        Set<List<Integer>> positions = new HashSet<>();
        for (String move : input) {
            String[] split = move.split(" ");
            int distance = Integer.parseInt(split[1]);
            for (int i = 0; i < distance; i++) {
                moveHead(head, split[0]);
                moveTail(head, tail);
                positions.add(List.of(tail[0], tail[1]));
            }
        }
        return positions.size();
    }

    public static int part2(List<String> input, int amount) {
        int[][] knots = new int[amount][2];
        Set<List<Integer>> positions = new HashSet<>();
        for (String move : input) {
            String[] split = move.split(" ");
            int distance = Integer.parseInt(split[1]);
            for (int i = 0; i < distance; i++) {
                moveHead(knots[0], split[0]);
                for (int k = 1; k < amount; k++) {
                    moveTail(knots[k - 1], knots[k]);
                }
                positions.add(List.of(knots[amount - 1][0], knots[amount - 1][1]));
            }
        }
        return positions.size();
    }

    private static void moveHead(int[] head, String direction) {
        switch (direction) {
            case "R" -> head[0]++;
            case "L" -> head[0]--;
            case "U" -> head[1]++;
            case "D" -> head[1]--;
        }
    }

    static int calcDistance(int[] head, int[] tail) {
        return Math.max(Math.abs(head[0] - tail[0]), Math.abs(head[1] - tail[1]));
    }

    static void moveTail(int[] head, int[] tail) {
        int xDist = head[0] - tail[0];
        int yDist = head[1] - tail[1];
        int totalDist = Math.abs(xDist) + Math.abs(yDist);
        if (Math.abs(xDist) > 1 || totalDist > 2)
            tail[0] = tail[0] + (xDist > 0 ? 1 : -1);
        if (Math.abs(yDist) > 1 || totalDist > 2)
            tail[1] = tail[1] + (yDist > 0 ? 1 : -1);
    }

    static void visualize(int[] head, int[] tail) {
        int halfSize = 15;
        System.out.println("---------------------------------------------------");
        for (int x = -halfSize; x < halfSize; x++) {
            for (int y = -halfSize; y < halfSize; y++) {
                if (x == head[0] && y == head[1])
                    System.out.print('H');
                else if (x == tail[0] && y == tail[1])
                    System.out.print('T');
                else
                    System.out.print('.');
            }
            System.out.println();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
