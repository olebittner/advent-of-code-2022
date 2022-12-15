package day15;

public class Sensor {

    private final int[] position;
    private final int[] beaconPosition;
    private final int distance;

    public Sensor(int[] position, int[] beaconPosition) {
        this.position = position;
        this.beaconPosition = beaconPosition;
        distance = calcDistance(position, beaconPosition);
    }

    public int[] getPosition() {
        return position;
    }

    public int[] getBeaconPosition() {
        return beaconPosition;
    }

    public int getDistance() {
        return distance;
    }

    public static int calcDistance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

    public boolean isInRange(int x, int y) {
        return calcDistance(position, new int[] {x, y}) <= distance;
    }
}
