package day18;

import util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BoilingBoulders {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day18/BoilingBoulders.txt");
        System.out.println(part1(input));
        //System.out.println(part2(input));
    }

    public static int part1(List<String> input) {
        HashMap<Cords3d, Integer> cubes = new HashMap<>();
        for (String line : input) {
            int[] ints = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            cubes.put(new Cords3d(ints[0], ints[1], ints[2]), 6);
        }

        for (Cords3d cube : cubes.keySet()) {
            cubes.put(cube, 6 - (int) Arrays.stream(cube.getAdjacentCords()).filter(cubes::containsKey).count());
        }

        return cubes.values().stream().mapToInt(Integer::intValue).sum();
    }

}
