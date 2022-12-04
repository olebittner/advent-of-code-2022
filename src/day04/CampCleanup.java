package day04;

import util.Util;

import java.util.Arrays;
import java.util.List;

public class CampCleanup {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day04/CampCleanup.txt");
        System.out.println(part1(input));
        //System.out.println(part2(input));
    }

    public static int part1(List<String> input) {
        return (int) input.stream().map(CampCleanup::parseBoundaries).filter(CampCleanup::isFullyContained).count();
    }

    static int[] parseBoundaries(String pair) {
        return Arrays.stream(pair.split("[,-]")).mapToInt(Integer::parseInt).toArray();
    }

    static boolean isFullyContained(int[] boundaries) {
        return (boundaries[0] <= boundaries[2] && boundaries[1] >= boundaries[3]) ||
                (boundaries[2] <= boundaries[0] && boundaries[3] >= boundaries[1]);
    }



}
