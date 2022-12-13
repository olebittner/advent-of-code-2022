package day13;

public class SignalPair {

    private final ListSignalPart left;
    private final ListSignalPart right;

    public SignalPair(ListSignalPart left, ListSignalPart right) {
        this.left = left;
        this.right = right;
    }

    public SignalPair(String left, String right) {
        this.left = ListSignalPart.fromString(left);
        this.right = ListSignalPart.fromString(right);
    }

    public boolean isInCorrectOrder() {
        return left.compareToRightSignal(right).equals(SignalOrderState.CORRECT);
    }
}
