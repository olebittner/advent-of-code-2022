package day13;

public class IntegerSignalPart extends AbstractSignalPart<Integer> {

    public IntegerSignalPart(Integer value) {
        super(value);
    }

    public static IntegerSignalPart fromString(String s) {
        return new IntegerSignalPart(Integer.valueOf(s));
    }

    @Override
    public SignalOrderState compareToRightSignal(AbstractSignalPart<?> right) {
        if (right instanceof IntegerSignalPart rightInt) {
            if (this.getValue() < rightInt.getValue())
                return SignalOrderState.CORRECT;
            if (this.getValue() > rightInt.getValue())
                return SignalOrderState.INCORRECT;
            return SignalOrderState.UNKNOWN;
        }
        if (right instanceof ListSignalPart rightList) {
            ListSignalPart leftList = new ListSignalPart(this);
            return leftList.compareToRightSignal(rightList);
        }
        throw new IllegalArgumentException("Comparison not implemented");
    }
}
