package day21;

import java.util.Arrays;

public enum Operation {

    ADD('+'), SUB('-'), MUL('*'), DIV('/');

    private final char c;

    Operation(char c) {
        this.c = c;
    }

    public static Operation fromChar(String c) {
        return Arrays.stream(Operation.values()).filter(o -> o.c == c.charAt(0)).findFirst().orElseThrow();
    }

    public Operation getOpposite() {
        return switch (this) {
            case ADD -> SUB;
            case SUB -> ADD;
            case MUL -> DIV;
            case DIV -> MUL;
        };
    }

}
