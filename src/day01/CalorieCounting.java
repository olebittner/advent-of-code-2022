package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CalorieCounting {

    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    private static int part1() {
        try (Scanner s = new Scanner(new File("inputs/day01/CalorieCounting.txt"))) {
            int maxCalories = 0;
            int curCalories = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.isEmpty()) {
                    maxCalories = Math.max(maxCalories, curCalories);
                    curCalories = 0;
                    continue;
                }
                curCalories += Integer.parseInt(line);
            }
            return maxCalories;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int part2() {
        try (Scanner s = new Scanner(new File("inputs/day01/CalorieCounting.txt"))) {
            List<Integer> elves = new ArrayList<>();
            int curCalories = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.isEmpty()) {
                    elves.add(curCalories);
                    curCalories = 0;
                    continue;
                }
                curCalories += Integer.parseInt(line);
            }
            return elves.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).sum();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
