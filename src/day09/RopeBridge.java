package day09;

import util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RopeBridge {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day09/RopeBridge.txt");
        System.out.println(part1(input, false));
        System.out.println(part2(input, 10, false));
    }

    public static int part1(List<String> input, boolean viz) {
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
                if (viz)
                    visualize(new int[][]{head, tail});
            }
        }
        return positions.size();
    }

    public static int part2(List<String> input, int amount, boolean viz) {
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
                if (viz)
                    visualize(knots);
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

    static void visualize(int[][] knots) {
        int upperEnd = Arrays.stream(knots).flatMapToInt(Arrays::stream).max().orElse(Integer.MAX_VALUE);
        int lowerEnd = Arrays.stream(knots).flatMapToInt(Arrays::stream).min().orElse(Integer.MIN_VALUE);
        String separator = new String(new char[upperEnd - lowerEnd + 3]).replace('\0', '-');
        System.out.println(separator);
        for (int x = lowerEnd; x <= upperEnd; x++) {
            System.out.print('|');
            Y_LOOP:
            for (int y = lowerEnd; y <= upperEnd; y++) {
                for (int i = 0; i < knots.length; i++) {
                    if (x == knots[i][0] && y == knots[i][1]) {
                        System.out.print(i);
                        continue Y_LOOP;
                    }
                }
                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(separator);
    }

}
