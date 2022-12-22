package day21;

public class NumberMonkey extends AbstractMonkey {

    private final long number;

    public NumberMonkey(String name, long number) {
        super(name);
        this.number = number;
    }


    @Override
    public long getNumber() {
        return number;
    }
}
