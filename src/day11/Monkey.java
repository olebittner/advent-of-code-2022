package day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Monkey {

    ArrayList<BigInteger> items;
    Function<BigInteger, BigInteger> worryLevelFunction;
    Function<BigInteger, BigInteger> worryReduceFunction;
    private int operationCount = 0;
    private final int divCheck;
    private final int passToOne;
    private final int passToTwo;
    private BigInteger modBase = null;

    public Monkey(List<Integer> items, Function<BigInteger, BigInteger> worryLevelFunction, Function<BigInteger, BigInteger> worryReduceFunction, int divCheck, int passToOne, int passToTwo) {
        this.items = new ArrayList<>(items.stream().map(BigInteger::valueOf).toList());
        this.worryLevelFunction = worryLevelFunction;
        this.divCheck = divCheck;
        this.passToOne = passToOne;
        this.passToTwo = passToTwo;
        this.worryReduceFunction = worryReduceFunction;
    }

    void calcWorryLevel() {
        operationCount = Math.addExact(operationCount, 1);
        items.set(0, worryLevelFunction.apply(items.get(0)));
        items.set(0, worryReduceFunction.apply(items.get(0)));
        items.set(0, items.get(0).mod(modBase));
    }

    int calcNextMonkey() {
        return items.get(0).mod(BigInteger.valueOf(divCheck)).equals(BigInteger.ZERO) ? passToOne : passToTwo;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public void processItems(List<Monkey> monkeys) {
        while (!items.isEmpty()) {
            calcWorryLevel();
            monkeys.get(calcNextMonkey()).giveItem(items.get(0));
            items.remove(0);
        }
    }

    public void giveItem(BigInteger item) {
        items.add(item);
    }

    public void setModBase(BigInteger modBase) {
        this.modBase = modBase;
    }

    public int getDivCheck() {
        return divCheck;
    }
}
