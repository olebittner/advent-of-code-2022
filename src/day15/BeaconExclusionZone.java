package day15;

import util.Util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeaconExclusionZone {

    public static final String INPUT_PATTERN = "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)";

    public static void main(String[] args) {
        List<String> input = Util.readInput("day15/BeaconExclusionZone.txt");
        List<Sensor> sensors = new ArrayList<>();
        List<int[]> allCords = new ArrayList<>();
        List<AbstractMap.SimpleEntry<Integer, Integer>> beacons = new ArrayList<>();
        Pattern r = Pattern.compile(INPUT_PATTERN);
        for (String line : input) {
            Matcher matcher = r.matcher(line);
            if (matcher.find()) {
                int[] sensor = new int[] {Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))};
                int[] beacon = new int[] {Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))};
                sensors.add(new Sensor(sensor, beacon));
                allCords.add(sensor);
                allCords.add(beacon);
                beacons.add(new AbstractMap.SimpleEntry<>(beacon[0], beacon[1]));
            }
        }
        int maxX = allCords.stream().mapToInt(c -> c[0]).max().orElse(0);
        int minX = allCords.stream().mapToInt(c -> c[0]).min().orElse(0);
        int maxY = allCords.stream().mapToInt(c -> c[1]).max().orElse(0);
        int minY = allCords.stream().mapToInt(c -> c[1]).min().orElse(0);
        int width = maxX - minX;
        int height = maxY - minY;

        int noBeacon = 0;
        int y = 2000000;
        for (int x = minX-10; x <= maxX+10; x++) {
            boolean beaconPossible = true;
            for (Sensor sensor : sensors) {
                if (!beacons.contains(new AbstractMap.SimpleEntry<>(x, y)) && sensor.isInRange(x, y)) {
                    beaconPossible = false;
                    break;
                }
            }
            if (!beaconPossible)
                noBeacon++;
        }
        System.out.println(noBeacon);
    }

}
