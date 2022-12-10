package day10;

public class AddInstruction extends Instruction {

    private final int x;

    public AddInstruction(int x) {
        this.x = x;
        this.cyclesLeft = 2;
    }

    @Override
    public int execute(int register) {
        register = super.execute(register);
        if (isDone())
            return register + x;
        return register;
    }
}
