package day10;

import util.Util;

import java.util.List;

public class CathodeRayTube {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day10/CathodeRayTube.txt");
        System.out.println(part1(input));
        //System.out.println(part2(input));
    }

    public static int part1(List<String> input) {
        int cycle = 0;
        int signalStrenght = 0;
        int X = 1;
        for (String inst : input) {
            Instruction instruction = parseInstruction(inst);
            do {
                cycle++;
                if ((cycle - 20) % 40 == 0)
                    signalStrenght += X * cycle;
                X = instruction.execute(X);
            } while (!instruction.isDone());
        }
        return signalStrenght;
    }

    private static Instruction parseInstruction(String inst) {
        String[] cmd = inst.split(" ");
        Instruction instruction = null;
        if (cmd[0].equals("noop"))
            instruction = new NoopInstruction();
        else if (cmd[0].equals("addx")) {
            instruction = new AddInstruction(Integer.parseInt(cmd[1]));
        }
        return instruction;
    }

}
