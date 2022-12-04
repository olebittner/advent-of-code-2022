package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Util {

    private Util() {}

    public static List<String> readInput(String input) {
        try (Stream<String> lines = Files.lines(Paths.get("inputs/"+input))) {
            return lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
