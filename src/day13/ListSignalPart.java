package day13;

import java.util.ArrayList;
import java.util.Arrays;

public class ListSignalPart extends AbstractSignalPart<AbstractSignalPart<?>[]> {

    public ListSignalPart(AbstractSignalPart<?>[] value) {
        super(value);
    }

    public ListSignalPart(IntegerSignalPart value) {
        super(new AbstractSignalPart[]{value});
    }

    public ListSignalPart() {
        super(new AbstractSignalPart[0]);
    }

    public static ListSignalPart fromString(String s) {
        return fromSplitString(s.substring(1, s.length() - 1).split(","));
    }

    private static ListSignalPart fromSplitString(String[] split) {
        ArrayList<AbstractSignalPart<?>> list = new ArrayList();
        int i = 0;
        while (i < split.length) {
            if (split[i].equals(""))
                return new ListSignalPart();
            else if (split[i].startsWith("[")) {
                int openBrackets = 0;
                int startI = i;
                do {
                    openBrackets += split[i].chars().filter(c -> c == '[').count();
                    openBrackets -= split[i].chars().filter(c -> c == ']').count();
                    i++;
                } while (openBrackets > 0);
                split[startI] = split[startI].substring(1);
                split[i - 1] = split[i - 1].substring(0, split[i - 1].length() - 1);
                list.add(fromSplitString(Arrays.copyOfRange(split, startI, i)));
            } else {
                list.add(IntegerSignalPart.fromString(split[i]));
                i++;
            }
        }
        return new ListSignalPart(list.toArray(new AbstractSignalPart[0]));
    }

    @Override
    public SignalOrderState compareToRightSignal(AbstractSignalPart<?> right) {
        if (right instanceof ListSignalPart rightList) {
            for (int i = 0; i < Math.min(this.getValue().length, rightList.getValue().length); i++) {
                SignalOrderState state = this.getValue()[i].compareToRightSignal(rightList.getValue()[i]);
                if (state != SignalOrderState.UNKNOWN)
                    return state;
            }
            if (this.getValue().length < rightList.getValue().length)
                return SignalOrderState.CORRECT;
            if (this.getValue().length > rightList.getValue().length)
                return SignalOrderState.INCORRECT;
            return SignalOrderState.UNKNOWN;
        }
        if (right instanceof IntegerSignalPart rightInt) {
            return this.compareToRightSignal(new ListSignalPart(rightInt));
        }
        throw new IllegalArgumentException("Comparison not implemented");
    }
}
