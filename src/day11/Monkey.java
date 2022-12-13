package day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

public class Monkey {

    ArrayList<Long> items;
    LongFunction<Long> worryLevelFunction;
    LongFunction<Long> worryReduceFunction;
    private long operationCount = 0;
    private final int divCheck;
    private final int passToOne;
    private final int passToTwo;
    private int modBase;

    public Monkey(List<Long> items, LongFunction<Long> worryLevelFunction, LongFunction<Long> worryReduceFunction, int divCheck, int passToOne, int passToTwo) {
        this.items = new ArrayList<>(items);
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
        items.set(0, items.get(0) % modBase);
    }

    int calcNextMonkey() {
        return (items.get(0) % divCheck) == 0 ? passToOne : passToTwo;
    }

    public long getOperationCount() {
        return operationCount;
    }

    public void processItems(List<Monkey> monkeys) {
        while (!items.isEmpty()) {
            calcWorryLevel();
            monkeys.get(calcNextMonkey()).giveItem(items.get(0));
            items.remove(0);
        }
    }

    public void giveItem(long item) {
        items.add(item);
    }

    public void setModBase(int modBase) {
        this.modBase = modBase;
    }

    public int getDivCheck() {
        return divCheck;
    }
}
