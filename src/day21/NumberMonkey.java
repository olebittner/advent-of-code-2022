package day21;

public class NumberMonkey extends AbstractMonkey {

    private final double number;

    public NumberMonkey(String name, long number) {
        super(name);
        this.number = number;
    }

    @Override
    public double getNumber() {
        return number;
    }
}
