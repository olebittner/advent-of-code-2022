package day10;

public abstract class Instruction {

    protected int cyclesLeft = 1;

    public int execute(int register) {
        cyclesLeft--;
        return register;
    }

    public boolean isDone() {
        return cyclesLeft <= 0;
    }

}
