package day20;

import java.util.Objects;

public class Entry {

    private final int value;
    private final int originalPosition;

    public Entry(int value, int originalPosition) {
        this.value = value;
        this.originalPosition = originalPosition;
    }

    public int getValue() {
        return value;
    }

    public int getOriginalPosition() {
        return originalPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return value == entry.value && originalPosition == entry.originalPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, originalPosition);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
