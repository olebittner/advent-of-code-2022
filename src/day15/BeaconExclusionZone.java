package day15;

import util.Util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeaconExclusionZone {

    public static final String INPUT_PATTERN = "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)";

    public static void main(String[] args) {
        List<String> input = Util.readInput("day15/BeaconExclusionZone.txt");
        List<Sensor> sensors = new ArrayList<>();
        List<int[]> allCords = new ArrayList<>();
        List<AbstractMap.SimpleEntry<Integer, Integer>> beacons = new ArrayList<>();
        parseInput(input, sensors, allCords, beacons);
        int maxX = allCords.stream().mapToInt(c -> c[0]).max().orElse(0);
        int minX = allCords.stream().mapToInt(c -> c[0]).min().orElse(0);

        int noBeacon = countNoBeacon(sensors, beacons, maxX, minX, 2000000);
        System.out.println(noBeacon);
        AbstractMap.SimpleEntry<Integer, Integer> beacon = findBeacon(sensors, 4000000);
        System.out.println(beacon.getKey() * 4000000L + beacon.getValue());
    }

    private static void parseInput(List<String> input, List<Sensor> sensors, List<int[]> allCords, List<AbstractMap.SimpleEntry<Integer, Integer>> beacons) {
        Pattern r = Pattern.compile(INPUT_PATTERN);
        for (String line : input) {
            Matcher matcher = r.matcher(line);
            if (matcher.find()) {
                int[] sensor = new int[]{Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))};
                int[] beacon = new int[]{Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))};
                sensors.add(new Sensor(sensor, beacon));
                allCords.add(sensor);
                allCords.add(beacon);
                beacons.add(new AbstractMap.SimpleEntry<>(beacon[0], beacon[1]));
            }
        }
    }

    private static int countNoBeacon(List<Sensor> sensors, List<AbstractMap.SimpleEntry<Integer, Integer>> beacons, int maxX, int minX, int searchY) {
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        int chunkSize = Math.max(1000, (maxX - minX) / 20);
        for (int xChunk = minX; xChunk <= maxX; xChunk += chunkSize) {
            CompletableFuture<Integer> future = countNoBeaconInChunk(sensors, beacons, searchY, chunkSize, xChunk);
            futures.add(future);
        }
        return futures.stream().mapToInt(CompletableFuture::join).sum();
    }

    private static CompletableFuture<Integer> countNoBeaconInChunk(List<Sensor> sensors, List<AbstractMap.SimpleEntry<Integer, Integer>> beacons, int searchY, int chunkSize, int finalXChunk) {
        return CompletableFuture.supplyAsync(() -> {
            int noBeacon = 0;
            for (int x = finalXChunk; x < finalXChunk + chunkSize; x++) {
                boolean beaconPossible = true;
                for (Sensor sensor : sensors) {
                    if (!beacons.contains(new AbstractMap.SimpleEntry<>(x, searchY)) && sensor.isInRange(x, searchY)) {
                        beaconPossible = false;
                        break;
                    }
                }
                if (!beaconPossible)
                    noBeacon++;
            }
            return noBeacon;
        });
    }

    private static AbstractMap.SimpleEntry<Integer, Integer> findBeacon(List<Sensor> sensors, int searchArea) {
        for (Sensor sensor : sensors) {
            System.out.println("Checking " + Arrays.toString(sensor.getPosition()));
            int x = sensor.getPosition()[0];
            int y = sensor.getPosition()[1];
            int d = sensor.getDistance() + 1;
            for (int i = 0; i <= d; i++) {
                if (isPositionValid(sensors, searchArea, x + d - i, y + i)) {
                    return new AbstractMap.SimpleEntry<>(x + d - i, y + i);
                }
                if (isPositionValid(sensors, searchArea, x - d + i, y + i)) {
                    return new AbstractMap.SimpleEntry<>(x + d - i, y + i);
                }
                if (isPositionValid(sensors, searchArea, x + d - i, y - i)) {
                    return new AbstractMap.SimpleEntry<>(x + d - i, y - i);
                }
                if (isPositionValid(sensors, searchArea, x - d + i, y - i)) {
                    return new AbstractMap.SimpleEntry<>(x + d - i, y - i);
                }
            }

        }
        return null;
    }

    private static boolean isPositionValid(List<Sensor> sensors, int searchArea, int x, int y) {
        if (0 <= x && x <= searchArea && 0 <= y && y <= searchArea) {
            boolean valid = true;
            for (Sensor s2 : sensors) {
                if (s2.isInRange(x, y)) {
                    valid = false;
                    break;
                }
            }
            return valid;
        }
        return false;
    }

}
