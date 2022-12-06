package day06;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TuningTrouble {

    public static void main(String[] args) {
        System.out.println(findSequenceOfUniqueChars(4));
        System.out.println(findSequenceOfUniqueChars(14));
    }

    public static int findSequenceOfUniqueChars(int length) {
        try (Reader r = new BufferedReader(new InputStreamReader(new FileInputStream("inputs/day06/TuningTrouble.txt"), StandardCharsets.UTF_8))) {
            char[] store = new char[length];
            int i = 0;
            int current;
            while ((current = r.read()) != -1) {
                store[i++ % length] = (char) current;
                if (i >= length && !containsDuplicates(store))
                    return i;
            }
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
