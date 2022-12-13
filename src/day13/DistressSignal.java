package day13;

import util.Util;

import java.util.ArrayList;
import java.util.List;

public class DistressSignal {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day13/DistressSignal.txt");
        List<ListSignalPart> signals = parseInputs(input);
        List<SignalPair> signalPairs = buildSignalPairs(signals);
        System.out.println(identifyCorrectSignals(signalPairs));
        System.out.println(calcDecoderKey(signals));
    }

    public static int calcDecoderKey(List<ListSignalPart> signals) {
        signals.add(ListSignalPart.fromString("[[2]]"));
        signals.add(ListSignalPart.fromString("[[6]]"));
        signals = signals.stream().sorted().toList();
        int key = 1;
        for (int i = 0; i < signals.size(); i++) {
            if (signals.get(i).isDivider()) {
                key = key * (i + 1);
            }
        }
        return key;
    }

    public static int identifyCorrectSignals(List<SignalPair> signalPairs) {
        int checkSum = 0;
        for (int i = 0; i < signalPairs.size(); i++) {
            if (signalPairs.get(i).isInCorrectOrder())
                checkSum += i + 1;
        }
        return checkSum;
    }

    static List<ListSignalPart> parseInputs(List<String> input) {
        List<ListSignalPart> list = new ArrayList<>();
        for (String i : input) {
            if (!i.isEmpty())
                list.add(ListSignalPart.fromString(i));
        }

        return list;
    }

    static List<SignalPair> buildSignalPairs(List<ListSignalPart> signals) {
        List<SignalPair> list = new ArrayList<>();
        for (int i = 0; i < signals.size(); i += 2) {
            list.add(new SignalPair(signals.get(i), signals.get(i + 1)));
        }
        return list;
    }

}
