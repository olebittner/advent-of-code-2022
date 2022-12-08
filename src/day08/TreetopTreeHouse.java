package day08;

import util.Util;

import java.util.List;

public class TreetopTreeHouse {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day08/TreetopTreeHouse.txt");
        short[][] forest = buildForest(input);
        System.out.println(part1(forest));
        //System.out.println(part2(input));
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
                if (!validTreeHousePosition(x, y, forest)) validPositionCount++;
            }
        }
        return validPositionCount;
    }

    static boolean validTreeHousePosition(int x, int y, short[][] forest) {
        short height = forest[x][y];
        for (int ix = 0; ix < forest.length; ix++) {
            if (ix == x)
                return false;
            if (forest[ix][y] >= height) {
                if (ix < x) {
                    ix = x;
                    continue;
                } else
                    break;
            }
            if (ix == forest.length - 1)
                return false;
        }
        for (int iy = 0; iy < forest[0].length; iy++) {
            if (iy == y)
                return false;
            if (forest[x][iy] >= height) {
                if (iy < y) {
                    iy = y;
                    continue;
                } else
                    break;
            }
            if (iy == forest[0].length - 1)
                return false;
        }
        return true;
    }


}
