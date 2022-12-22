package day20;

import java.util.Objects;

public class Entry {

    private long value;
    private final long originalPosition;

    public Entry(long value, long originalPosition) {
        this.value = value;
        this.originalPosition = originalPosition;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public long getOriginalPosition() {
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
