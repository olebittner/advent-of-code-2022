package day07;

import util.Util;

import java.util.Iterator;
import java.util.List;

public class NoSpaceLeftOnDevice {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day07/NoSpaceLeftOnDevice.txt");
        System.out.println(part1(input));
        //System.out.println(part2(input));
    }

    public static long part1(List<String> input) {
        Directory fileSystem = new Directory("/");
        buildFileSystem(input.listIterator(), fileSystem);
        List<Directory> directoryList = flattenDirectories(fileSystem);
        return directoryList.stream().mapToLong(Directory::getSize).filter(value -> value <= 100_000).sum();
    }

    static boolean buildFileSystem(Iterator<String> input, Directory dir) {
        boolean isRoot = dir.getName().equals("/");
        while (input.hasNext()) {
            String[] cmd = input.next().split(" ");
            if (cmd[0].equals("$")) {
                if (cmd[1].equals("cd")) {
                    if (cmd[2].equals("/")) {
                        if (!isRoot) return true;
                    } else if (cmd[2].equals("..")) {
                        if (!isRoot) return false;
                    } else {
                        if (!dir.getChildren().containsKey(cmd[2]))
                            dir.getChildren().put(cmd[2], new Directory(cmd[2]));
                        if (buildFileSystem(input, (Directory) dir.getChildren().get(cmd[2])) && !isRoot)
                            return true;
                    }
                }
            } else if (cmd[0].equals("dir")) {
                if (!dir.getChildren().containsKey(cmd[1]))
                    dir.getChildren().put(cmd[1], new Directory(cmd[1]));
            } else {
                if (!dir.getChildren().containsKey(cmd[1]))
                    dir.getChildren().put(cmd[1], new File(cmd[1], Long.valueOf(cmd[0])));
            }
        }
        return true;
    }

    static List<Directory> flattenDirectories(Directory directory) {
        List<Directory> directories = directory.getSubDirectories();
        directory.getSubDirectories().forEach(d -> directories.addAll(flattenDirectories(d)));
        return directories;
    }

}
