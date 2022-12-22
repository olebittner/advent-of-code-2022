package day21;

import java.util.Map;

public class MathMonkey extends AbstractMonkey {

    enum Operation {
        ADD, SUB, MUL, DIV;

        public static Operation fromChar(String c) {
            return switch (c) {
                case "+" -> ADD;
                case "-" -> SUB;
                case "*" -> MUL;
                case "/" -> DIV;
                default -> throw new IllegalArgumentException();
            };
        }
    }

    private final Operation operation;
    private AbstractMonkey monkey1;
    private AbstractMonkey monkey2;
    private final String monkey1name;
    private final String monkey2name;

    private Long result = null;

    public MathMonkey(String name, Operation operation, AbstractMonkey monkey1, AbstractMonkey monkey2) {
        super(name);
        this.operation = operation;
        this.monkey1 = monkey1;
        this.monkey2 = monkey2;
        this.monkey1name = this.monkey1.getName();
        this.monkey2name = this.monkey2.getName();
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

    @Override
    public long getNumber() {
        if (result == null) {
            long x = monkey1.getNumber();
            long y = monkey2.getNumber();
            result = switch (operation) {
                case ADD -> Math.addExact(x, y);
                case SUB -> Math.subtractExact(x, y);
                case DIV -> Math.divideExact(x, y);
                case MUL -> Math.multiplyExact(x, y);
            };
        }
        return result;
    }
}
