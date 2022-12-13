package day03;

import util.Util;

import java.util.List;

import static util.Constants.ALPHABET;

public class RucksackReorganization {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day03/RucksackReorganization.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
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

    public static int part2(List<String> input) {
        int result = 0;
        for (int i = 0; i < input.size(); i+=3) {
            Character item = findCommonItem(input.subList(i, i+3));
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
        return ALPHABET.indexOf(item) + 1;
    }

    static Character findCommonItem(List<String> backpacks) {
        String first = backpacks.get(0);
        List<String> other = backpacks.subList(1, backpacks.size());
        for (char item : first.toCharArray()) {
            if (other.stream().allMatch(s -> s.indexOf(item) >= 0))
                return item;
        }
        return null;
    }



}
