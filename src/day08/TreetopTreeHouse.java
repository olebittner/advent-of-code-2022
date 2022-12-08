package day08;

import util.Util;

import java.util.Arrays;
import java.util.List;

public class TreetopTreeHouse {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day08/TreetopTreeHouse.txt");
        short[][] forest = buildForest(input);
        System.out.println(part1(forest));
        System.out.println(part2(forest));
    }

    public static short[][] buildForest(List<String> input) {
        short[][] forest = new short[input.size()][input.get(0).length()];
        for (int x = 0; x < forest.length; x++) {
            for (int y = 0; y < forest[0].length; y++) {
                forest[x][y] = Short.parseShort(String.valueOf(input.get(x).charAt(y)));
            }
        }
        return forest;
    }

    public static int part1(short[][] forest) {
        int validPositionCount = forest.length * 2 + forest[0].length * 2 - 4;
        for (int x = 1; x < forest.length - 1; x++) {
            for (int y = 1; y < forest[0].length - 1; y++) {
                if (isTreeVisible(x, y, forest)) validPositionCount++;
            }
        }
        return validPositionCount;
    }

    public static long part2(short[][] forest) {
        long highestScenicScore = 0;
        for (int x = 1; x < forest.length - 1; x++) {
            for (int y = 1; y < forest[0].length - 1; y++) {
                highestScenicScore = Math.max(highestScenicScore, calcScenicScore(x, y, forest));
            }
        }
        return highestScenicScore;
    }

    static boolean isTreeVisible(int x, int y, short[][] forest) {
        short height = forest[x][y];
        for (int ix = 0; ix < forest.length; ix++) {
            if (ix == x)
                return true;
            if (forest[ix][y] >= height) {
                if (ix < x) {
                    ix = x;
                    continue;
                } else
                    break;
            }
            if (ix == forest.length - 1)
                return true;
        }
        for (int iy = 0; iy < forest[0].length; iy++) {
            if (iy == y)
                return true;
            if (forest[x][iy] >= height) {
                if (iy < y) {
                    iy = y;
                    continue;
                } else
                    break;
            }
            if (iy == forest[0].length - 1)
                return true;
        }
        return false;
    }

    static long calcScenicScore(int x, int y, short[][] forest) {
        short height = forest[x][y];
        int[] view = new int[]{-1, -1, -1, -1};
        for (int i = 1; i < forest.length; i++) {
            if (view[0] < 0 && (x + i + 1 >= forest.length || forest[x + i][y] >= height)) {
                view[0] = i;
            }
            if (view[1] < 0 && (x - i - 1 < 0 || forest[x - i][y] >= height)) {
                view[1] = i;
            }
            if (view[2] < 0 && (y + i + 1 >= forest[x].length || forest[x][y + i] >= height)) {
                view[2] = i;
            }
            if (view[3] < 0 && (y - i - 1 < 0 || forest[x][y - i] >= height)) {
                view[3] = i;
            }
            if (Arrays.stream(view).allMatch(v -> v >= 0)) break;
        }
        return Arrays.stream(view).reduce((left, right) -> left * right).getAsInt();
    }


}
