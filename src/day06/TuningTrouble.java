package day06;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TuningTrouble {

    public static void main(String[] args) {
        System.out.println(part1());
//        System.out.println(part2(setup, moves));
    }

    public static int part1() {
        try (Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(new File("inputs/day06/TuningTrouble.txt")), StandardCharsets.UTF_8))) {
            char[] store = new char[4];
            int i = 0;
            int current;
            while ((current = r.read()) != -1) {
                store[i++%4] = (char) current;
                if (i >= 4 && !containsDuplicates(store))
                    return i;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static boolean containsDuplicates(char[] chars) {
        for (int i = 0; i < chars.length-1; i++) {
            for (int j = i+1; j < chars.length; j++) {
                if (chars[i] == chars[j])
                    return true;
            }
        }
        return false;
    }
}
