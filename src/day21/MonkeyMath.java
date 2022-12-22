package day21;

import util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyMath {

    public static final String MONKEY_PATTERN_NUMBER = "(\\w+): (\\d+)";
    public static final String MONKEY_PATTERN_MATH = "(\\w+): (\\w+) ([+\\-*\\/]) (\\w+)";

    public static void main(String[] args) {
        List<String> input = Util.readInput("day21/MonkeyMath.txt");
        Map<String, AbstractMonkey> monkeyMap = parseInput(input);
        System.out.println((long) monkeyMap.get("root").getNumber());

        Stack<MathMonkey> monkeyStack = new Stack<>();
        AbstractMonkey current = monkeyMap.get("humn");
        while (!current.getName().equals("root")) {
            AbstractMonkey finalCurrent = current;
            MathMonkey next = (MathMonkey) monkeyMap.values().stream().filter(m -> m.interactsWith(finalCurrent)).findFirst().orElseThrow();
            monkeyStack.push(next);
            current = next;
        }

        MathMonkey cMath = monkeyStack.pop();
        double number = cMath.getOtherMonkey(monkeyStack.peek()).getNumber();
        while (!monkeyStack.isEmpty()) {
            cMath = monkeyStack.pop();
            AbstractMonkey next;
            if (monkeyStack.isEmpty())
                next = new NumberMonkey("humn", 0);
            else
                next = monkeyStack.peek();
            if ((cMath.getOperation() == Operation.SUB || cMath.getOperation() == Operation.DIV)) {
                number = MathMonkey.calc(!cMath.getMonkey1().equals(next) ? cMath.getOperation() : cMath.getOperation().getOpposite(), cMath.getOtherMonkey(next).getNumber(), number);
            } else
                number = MathMonkey.calc(cMath.getOperation().getOpposite(), number, cMath.getOtherMonkey(next).getNumber());
        }
        System.out.println((long) number);
    }

    public static Map<String, AbstractMonkey> parseInput(List<String> input) {
        Map<String, AbstractMonkey> monkeys = new HashMap<>();
        Pattern numberRegex = Pattern.compile(MONKEY_PATTERN_NUMBER);
        Pattern mathRegex = Pattern.compile(MONKEY_PATTERN_MATH);

        for (String line : input) {
            Matcher matcher = numberRegex.matcher(line);
            if (matcher.find()) {
                monkeys.put(matcher.group(1), new NumberMonkey(matcher.group(1), Long.parseLong(matcher.group(2))));
            } else {
                matcher = mathRegex.matcher(line);
                if (matcher.find()) {
                    monkeys.put(matcher.group(1), new MathMonkey(matcher.group(1), Operation.fromChar(matcher.group(3)), matcher.group(2), matcher.group(4)));
                }
            }
        }

        monkeys.values().stream().filter(MathMonkey.class::isInstance).forEach(m -> ((MathMonkey) m).linkMonkeys(monkeys));

        return monkeys;
    }

}
