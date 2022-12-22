package day21;

import java.util.Objects;

public abstract class AbstractMonkey {

    private final String name;

    public AbstractMonkey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getNumber();

    public boolean interactsWith(AbstractMonkey monkey) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMonkey that = (AbstractMonkey) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
