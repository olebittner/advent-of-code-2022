package day21;

public abstract class AbstractMonkey {

    private final String name;

    public AbstractMonkey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract long getNumber();
}
