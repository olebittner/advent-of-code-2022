package day13;

public abstract class AbstractSignalPart<T> implements Comparable<AbstractSignalPart<?>> {

    private final T value;

    AbstractSignalPart(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract SignalOrderState compareToRightSignal(AbstractSignalPart<?> right);

    public boolean isDivider() {
        return false;
    }

    @Override
    public int compareTo(AbstractSignalPart<?> o) {
        SignalOrderState orderState = this.compareToRightSignal(o);
        if (orderState == SignalOrderState.CORRECT)
            return -1;
        if (orderState == SignalOrderState.INCORRECT)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
