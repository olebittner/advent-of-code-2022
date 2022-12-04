package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CalorieCounting {

    public static void main(String[] args) {
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
            System.out.println(maxCalories);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
