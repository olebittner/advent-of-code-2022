package day11;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MonkeyInTheMiddle {

    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static BigInteger part1() {
        List<Monkey> monkeys = buildMonkeyList(w -> w.divide(BigInteger.valueOf(3)));
        letTheMonkeysPlay(monkeys, 20);
        return calcMonkeyBusinessLevel(monkeys);
    }

    public static BigInteger part2() {
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

    static BigInteger calcMonkeyBusinessLevel(List<Monkey> monkeys) {
        return monkeys.stream().map(Monkey::getOperationCount).sorted(Comparator.reverseOrder()).limit(2).map(BigInteger::valueOf).reduce(BigInteger::multiply).orElse(BigInteger.ZERO);
    }

    static List<Monkey> buildMonkeyList(Function<BigInteger, BigInteger> worryReduceFunction) {
        List<Monkey> monkeys = List.of(
                new Monkey(Arrays.asList(98, 89, 52), w -> w.multiply(BigInteger.valueOf(2)), worryReduceFunction, 5, 6, 1),
                new Monkey(Arrays.asList(57, 95, 80, 92, 57, 78), w -> w.multiply(BigInteger.valueOf(13)), worryReduceFunction, 2, 2, 6),
                new Monkey(Arrays.asList(82, 74, 97, 75, 51, 92, 83), w -> w.add(BigInteger.valueOf(5)), worryReduceFunction, 19, 7, 5),
                new Monkey(Arrays.asList(97, 88, 51, 68, 76), w -> w.add(BigInteger.valueOf(6)), worryReduceFunction, 7, 0, 4),
                new Monkey(Arrays.asList(63), w -> w.add(BigInteger.valueOf(1)), worryReduceFunction, 17, 0, 1),
                new Monkey(Arrays.asList(94, 91, 51, 63), w -> w.add(BigInteger.valueOf(4)), worryReduceFunction, 13, 4, 3),
                new Monkey(Arrays.asList(61, 54, 94, 71, 74, 68, 98, 83), w -> w.add(BigInteger.valueOf(2)), worryReduceFunction, 3, 2, 7),
                new Monkey(Arrays.asList(90, 56), w -> w.multiply(w), worryReduceFunction, 11, 3, 5)
        );
        int base = monkeys.stream().mapToInt(Monkey::getDivCheck).reduce((left, right) -> left * right).getAsInt();
        monkeys.forEach(monkey -> monkey.setModBase(BigInteger.valueOf(base)));
        return monkeys;
    }

}
