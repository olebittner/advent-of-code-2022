package day18;

import util.Cords3d;
import util.Util;

import java.util.*;

public class BoilingBoulders {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day18/BoilingBoulders.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
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

    public static int part2(List<String> input) {
        Cords3d min = new Cords3d(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
        Cords3d max = new Cords3d(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
        HashSet<Cords3d> cubes = new HashSet<>();
        for (String line : input) {
            int[] ints = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            cubes.add(new Cords3d(ints[0], ints[1], ints[2]));
            min.setX(Math.min(ints[0]-1, min.getX()));
            min.setY(Math.min(ints[1]-1, min.getY()));
            min.setZ(Math.min(ints[2]-1, min.getZ()));
            max.setX(Math.max(ints[0]+1, max.getX()));
            max.setY(Math.max(ints[1]+1, max.getY()));
            max.setZ(Math.max(ints[2]+1, max.getZ()));
        }

        Queue<Cords3d> openCords = new LinkedList<>();
        Set<Cords3d> closedCords = new HashSet<>();
        openCords.add(min);
        int surface = 0;

        while (!openCords.isEmpty()) {
            Cords3d current = openCords.poll();
            List<Cords3d> adjacentList = Arrays.stream(current.getAdjacentCords()).filter(c -> c.isBetween(min, max)).toList();
            for (Cords3d adjacent : adjacentList) {
                if (cubes.contains(adjacent))
                    surface++;
                else if (!openCords.contains(adjacent) && !closedCords.contains(adjacent))
                    openCords.add(adjacent);
            }
            closedCords.add(current);
        }

        return surface;
    }

}
