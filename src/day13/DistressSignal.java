package day13;

import util.Util;

import java.util.ArrayList;
import java.util.List;

public class DistressSignal {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day13/DistressSignal.txt");
        List<SignalPair> signalPairs = parseInputs(input);
        System.out.println(identifyCorrectSignals(signalPairs));
//        System.out.println(part2(setup));
    }

    public static int identifyCorrectSignals(List<SignalPair> signalPairs) {
        int checkSum = 0;
        for (int i = 0; i < signalPairs.size(); i++) {
            if (signalPairs.get(i).isInCorrectOrder())
                checkSum += i + 1;
        }
        return checkSum;
    }

    static List<SignalPair> parseInputs(List<String> input) {
        List<SignalPair> list = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 3) {
            list.add(new SignalPair(input.get(i), input.get(i + 1)));
        }
        return list;
    }

}
