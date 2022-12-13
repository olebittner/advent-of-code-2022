package day13;

public abstract class AbstractSignalPart<T> {

    private final T value;

    public AbstractSignalPart(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract SignalOrderState compareToRightSignal(AbstractSignalPart<?> right);

}
