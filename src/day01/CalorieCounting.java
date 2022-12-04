package day01;

import util.Util;

import java.util.*;

public class CalorieCounting {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day01/CalorieCounting.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    private static int part1(List<String> input) {
        Iterator<String> i = input.iterator();
        int maxCalories = 0;
        int curCalories = 0;
        while (i.hasNext()) {
            String line = i.next();
            if (line.isEmpty()) {
                maxCalories = Math.max(maxCalories, curCalories);
                curCalories = 0;
                continue;
            }
            curCalories += Integer.parseInt(line);
        }
        return maxCalories;
    }

    private static int part2(List<String> input) {
        Iterator<String> i = input.iterator();
        List<Integer> elves = new ArrayList<>();
        int curCalories = 0;
        while (i.hasNext()) {
            String line = i.next();
            if (line.isEmpty()) {
                elves.add(curCalories);
                curCalories = 0;
                continue;
            }
            curCalories += Integer.parseInt(line);
        }
        return elves.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).sum();
    }

}
