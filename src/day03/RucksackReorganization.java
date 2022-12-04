package day03;

import util.Util;

import java.util.List;

public class RucksackReorganization {

    final static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        List<String> input = Util.readInput("day03/RucksackReorganization.txt");
        System.out.println(part1(input));
        //System.out.println(part2());
    }

    public static int part1(List<String> input) {
        int result = 0;
        for (String backpack : input) {
            String[] compartments = splitIntoCompartments(backpack);
            Character item = findSeparatedItem(compartments);
            result += getPriorityForItem(item);
        }
        return result;
    }

    static String[] splitIntoCompartments(String backpack) {
        int divider = backpack.length()/2;
        return new String[] {backpack.substring(0, divider), backpack.substring(divider)};
    }

    static Character findSeparatedItem(String[] compartments) {
        for (char item : compartments[0].toCharArray()) {
            if (compartments[1].indexOf(item) >= 0)
                return item;
        }
        return null;
    }

    static int getPriorityForItem(Character item) {
        return alphabet.indexOf(item) + 1;
    }



}
