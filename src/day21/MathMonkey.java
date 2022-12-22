package day21;

import java.util.Map;

public class MathMonkey extends AbstractMonkey {

    private final Operation operation;
    private AbstractMonkey monkey1;
    private AbstractMonkey monkey2;
    private final String monkey1name;
    private final String monkey2name;

    public MathMonkey(String name, Operation operation, AbstractMonkey monkey1, AbstractMonkey monkey2) {
        super(name);
        this.operation = operation;
        this.monkey1 = monkey1;
        this.monkey2 = monkey2;
        this.monkey1name = this.monkey1.getName();
        this.monkey2name = this.monkey2.getName();
    }

    public Operation getOperation() {
        return operation;
    }

    public MathMonkey(String name, Operation operation, String monkey1name, String monkey2name) {
        super(name);
        this.operation = operation;
        this.monkey1name = monkey1name;
        this.monkey2name = monkey2name;
    }

    public void linkMonkeys(Map<String, AbstractMonkey> monkeyMap) {
        this.monkey1 = monkeyMap.get(monkey1name);
        this.monkey2 = monkeyMap.get(monkey2name);
    }

    public AbstractMonkey getMonkey1() {
        return monkey1;
    }

    public AbstractMonkey getMonkey2() {
        return monkey2;
    }

    public AbstractMonkey getOtherMonkey(AbstractMonkey monkey) {
        if (monkey1.equals(monkey))
            return monkey2;
        return monkey1;
    }

    public static double calc(Operation o, double x, double y) {
        return switch (o) {
            case ADD -> x + y;
            case SUB -> x - y;
            case DIV -> x / y;
            case MUL -> x * y;
        };
    }

    @Override
    public double getNumber() {
        double x = monkey1.getNumber();
        double y = monkey2.getNumber();
        return calc(operation, x, y);
    }

    @Override
    public boolean interactsWith(AbstractMonkey monkey) {
        return monkey1.equals(monkey) || monkey2.equals(monkey);
    }
}
