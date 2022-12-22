package day21;

import util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyMath {

    public static final String MONKEY_PATTERN_NUMBER = "(\\w+): (\\d+)";
    public static final String MONKEY_PATTERN_MATH = "(\\w+): (\\w+) ([+\\-*\\/]) (\\w+)";

    public static void main(String[] args) {
        List<String> input = Util.readInput("day21/MonkeyMath.txt");
        Map<String, AbstractMonkey> monkeyMap = parseInput(input);
        System.out.println(monkeyMap.get("root").getNumber());
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
                    monkeys.put(matcher.group(1), new MathMonkey(matcher.group(1), MathMonkey.Operation.fromChar(matcher.group(3)), matcher.group(2), matcher.group(4)));
                }
            }
        }

        monkeys.values().stream().filter(MathMonkey.class::isInstance).forEach(m -> ((MathMonkey) m).linkMonkeys(monkeys));

        return monkeys;
    }

}
