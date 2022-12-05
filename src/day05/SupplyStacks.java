package day05;

import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStacks {

    public static final String MOVE_PATTERN = "move (\\d+) from (\\d+) to (\\d+)";

    public static void main(String[] args) {
        List<String> input = Util.readInput("day05/SupplyStacks.txt");
        List<String> setup = input.subList(0, input.indexOf("")-1);
        List<String> moves = input.subList(input.indexOf("")+1, input.size());
        System.out.println(part1(setup, moves));
        System.out.println(part2(setup, moves));
    }

    public static String part1(List<String> setup, List<String> moves) {
        List<Stack<Character>> stacks = parseStackSetup(setup);
        performMoves9000(stacks, moves);
        return getTopCrates(stacks);
    }

    public static String part2(List<String> setup, List<String> moves) {
        List<Stack<Character>> stacks = parseStackSetup(setup);
        performMoves9001(stacks, moves);
        return getTopCrates(stacks);
    }

    static List<Stack<Character>> parseStackSetup(List<String> setup) {
        List<Stack<Character>> crates = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            crates.add(new Stack<>());
        }
        for (int i = setup.size()-1; i >= 0; i--) {
            for (int j = 1; j < setup.get(i).length(); j+=4) {
                if (setup.get(i).charAt(j) != ' ') {
                    crates.get((j-1)/4).push(setup.get(i).charAt(j));
                }
            }
        }
        return crates;
    }

    static void performMoves9000(List<Stack<Character>> stacks, List<String> moves) {
        Pattern r = Pattern.compile(MOVE_PATTERN);
        for (String move : moves) {
            Matcher m = r.matcher(move);
            if(m.find()) {
                for (int i = 0; i < Integer.parseInt(m.group(1)); i++) {
                    Character crate = stacks.get(Integer.parseInt(m.group(2)) - 1).pop();
                    stacks.get(Integer.parseInt(m.group(3)) - 1).push(crate);
                }
            }
        }
    }

    static void performMoves9001(List<Stack<Character>> stacks, List<String> moves) {
        Pattern r = Pattern.compile(MOVE_PATTERN);
        for (String move : moves) {
            Matcher m = r.matcher(move);
            if(m.find()) {
                Stack<Character> crane = new Stack<>();
                for (int i = 0; i < Integer.parseInt(m.group(1)); i++) {
                    Character crate = stacks.get(Integer.parseInt(m.group(2)) - 1).pop();
                    crane.push(crate);
                }
                while (!crane.isEmpty())
                    stacks.get(Integer.parseInt(m.group(3)) - 1).push(crane.pop());
            }
        }
    }

    static String getTopCrates(List<Stack<Character>> stacks) {
        StringBuilder builder = new StringBuilder();
        for (Stack<Character> stack : stacks) {
            if (stack.isEmpty())
                builder.append(" ");
            else
                builder.append(stack.pop());
        }
        return builder.toString();
    }

}
