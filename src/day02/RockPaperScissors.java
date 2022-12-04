package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RockPaperScissors {

    enum Shape {ROCK, PAPER, SCISSOR}

    enum Result {LOSE, DRAW, WIN}

    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() {
        try (Scanner s = new Scanner(new File("inputs/day02/RockPaperScissors.txt"))) {
            int total = 0;
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                Shape[] shapes = parseShapes(line);
                Result result = getMatchResult(shapes[0], shapes[1]);
                total += calcPoints(result, shapes[1]);
            }
            return total;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int part2() {
        try (Scanner s = new Scanner(new File("inputs/day02/RockPaperScissors.txt"))) {
            int total = 0;
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                Shape[] shapes = parseShapes(line);
                Result desiredResult = parseResult(line[1]);
                Shape selectedShape = getShapeForResult(shapes[0], desiredResult);
                total += calcPoints(desiredResult, selectedShape);
            }
            return total;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Shape[] parseShapes(String[] strings) {
        Shape[] shapes = new Shape[strings.length];
        for (int i = 0; i < strings.length; i++) {
            Shape s = switch (strings[i]) {
                case "A", "X" -> Shape.ROCK;
                case "B", "Y" -> Shape.PAPER;
                default -> Shape.SCISSOR;
            };
            shapes[i] = s;
        }
        return shapes;
    }

    static Result parseResult(String s) {
        return switch (s) {
            case "X" -> Result.LOSE;
            case "Y" -> Result.DRAW;
            default -> Result.WIN;
        };
    }

    static Result getMatchResult(Shape opponent, Shape player) {
        if (opponent == player)
            return Result.DRAW;
        if (player.ordinal() == 0 && opponent.ordinal() == Shape.values().length-1)
            return Result.WIN;
        if (player.ordinal() == Shape.values().length-1 && opponent.ordinal() == 0)
            return Result.LOSE;
        return player.ordinal() > opponent.ordinal() ? Result.WIN : Result.LOSE;
    }

    static int calcPoints(Result result, Shape shape) {
        return (shape.ordinal()+1) + (result.ordinal()*3);
    }

    static Shape getShapeForResult(Shape opponent, Result desiredResult) {
        if (desiredResult == Result.DRAW)
            return opponent;
        int offset = desiredResult == Result.WIN ? 1 : -1;
        int target = opponent.ordinal() + offset;
        if (target < 0)
            target += Shape.values().length;
        if (target >= Shape.values().length)
            target -= Shape.values().length;
        return Shape.values()[target];
    }



}
