package day20;

import util.Util;

import java.util.LinkedList;
import java.util.List;

public class GrovePositioningSystem {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day20/GrovePositioningSystem.txt");

        List<Entry> in = parseInput(input);
        mix(in);
        long result = getCordSum(in);
        System.out.println(result);

        in = parseInput(input);
        in.forEach(e -> e.setValue(Math.multiplyExact(e.getValue(), 811589153)));
        for (int i = 0; i < 10; i++) {
            mix(in);
        }
        result = getCordSum(in);
        System.out.println(result);


    }

    private static List<Entry> parseInput(List<String> input) {
        List<Entry> in = new LinkedList<>();
        for (int i = 0; i < input.size(); i++) {
            in.add(new Entry(Integer.parseInt(input.get(i)), i));
        }
        return in;
    }

    private static long getCordSum(List<Entry> in) {
        int zeroI = in.indexOf(in.stream().filter(e -> e.getValue() == 0).findFirst().orElseThrow());
        int one = Math.floorMod(zeroI + 1000, in.size());
        int two = Math.floorMod(zeroI + 2000, in.size());
        int three = Math.floorMod(zeroI + 3000, in.size());
        return in.get(one).getValue() + in.get(two).getValue() + in.get(three).getValue();
    }

    private static void mix(List<Entry> in) {
        for (int i = 0; i < in.size(); i++) {
            final int finalI = i;
            Entry entry = in.stream().filter(e -> e.getOriginalPosition() == finalI).findFirst().orElseThrow();
            if (entry.getValue() == 0)
                continue;

            final int index = in.indexOf(entry);
            in.remove(index);
            long newIndex = index + entry.getValue();
            int newIndexMod = Math.floorMod(newIndex, in.size());


            if (newIndexMod == 0) {
                newIndexMod = in.size();
            }
            in.add(newIndexMod, entry);
        }
    }

}
