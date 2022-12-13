package day11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.LongFunction;

public class MonkeyInTheMiddle {

    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static long part1() {
        List<Monkey> monkeys = buildMonkeyList(w -> (long) (w / 3d));
        letTheMonkeysPlay(monkeys, 20);
        return calcMonkeyBusinessLevel(monkeys);
    }

    public static long part2() {
        List<Monkey> monkeys = buildMonkeyList(w -> w);
        letTheMonkeysPlay(monkeys, 10000);
        return calcMonkeyBusinessLevel(monkeys);
    }

    static void letTheMonkeysPlay(List<Monkey> monkeys, int roundsToPlay) {
        for (int i = 0; i < roundsToPlay; i++) {
            for (int j = 0; j < monkeys.size(); j++) {
                monkeys.get(j).processItems(monkeys);
            }
            if (i % 500 == 0)
                System.out.println("Round " + i);
        }
    }

    static long calcMonkeyBusinessLevel(List<Monkey> monkeys) {
        return monkeys.stream().map(Monkey::getOperationCount).sorted(Comparator.reverseOrder()).limit(2).mapToLong(Long::longValue).reduce((left, right) -> left * right).orElse(-1);
    }

    static List<Monkey> buildMonkeyList(LongFunction<Long> worryReduceFunction) {
        List<Monkey> monkeys = List.of(
                new Monkey(Arrays.asList(98L, 89L, 52L), w -> w * 2, worryReduceFunction, 5, 6, 1),
                new Monkey(Arrays.asList(57L, 95L, 80L, 92L, 57L, 78L), w -> w * 13, worryReduceFunction, 2, 2, 6),
                new Monkey(Arrays.asList(82L, 74L, 97L, 75L, 51L, 92L, 83L), w -> w + 5, worryReduceFunction, 19, 7, 5),
                new Monkey(Arrays.asList(97L, 88L, 51L, 68L, 76L), w -> w + 6, worryReduceFunction, 7, 0, 4),
                new Monkey(Arrays.asList(63L), w -> w + 1, worryReduceFunction, 17, 0, 1),
                new Monkey(Arrays.asList(94L, 91L, 51L, 63L), w -> w + 4, worryReduceFunction, 13, 4, 3),
                new Monkey(Arrays.asList(61L, 54L, 94L, 71L, 74L, 68L, 98L, 83L), w -> w + 2, worryReduceFunction, 3, 2, 7),
                new Monkey(Arrays.asList(90L, 56L), w -> w * w, worryReduceFunction, 11, 3, 5)
        );
        int base = monkeys.stream().mapToInt(Monkey::getDivCheck).reduce((left, right) -> left * right).getAsInt();
        monkeys.forEach(monkey -> monkey.setModBase(base));
        return monkeys;
    }

}
